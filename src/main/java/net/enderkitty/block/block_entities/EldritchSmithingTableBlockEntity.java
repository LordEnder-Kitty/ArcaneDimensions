package net.enderkitty.block.block_entities;

import net.enderkitty.block.ArcaneDimsBlockEntities;
import net.enderkitty.recipe.EldritchSmithingRecipe;
import net.enderkitty.screen.EldritchSmithingTableScreenHandler;
import net.enderkitty.util.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EldritchSmithingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);
    private final RecipeManager.MatchGetter<SidedInventory, EldritchSmithingRecipe> matchGetter;
    
    private static final int OUTPUT_SLOT = 0;
    private static final int FUEL_SLOT = 6;
    
    protected final PropertyDelegate propertyDelegate;
    int burnTime;
    int fuelTime;
    int craftTime;
    int maxCraftTime;
    
    public EldritchSmithingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ArcaneDimsBlockEntities.ELDRITCH_SMITHING_TABLE_BLOCK_ENTITY, pos, state);
        this.matchGetter = RecipeManager.createCachedMatchGetter(EldritchSmithingRecipe.Type.INSTANCE);
        
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> EldritchSmithingTableBlockEntity.this.craftTime;
                    case 1 -> EldritchSmithingTableBlockEntity.this.maxCraftTime;
                    case 2 -> EldritchSmithingTableBlockEntity.this.burnTime;
                    case 3 -> EldritchSmithingTableBlockEntity.this.fuelTime;
                    default -> 0;
                };
            }
            
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> EldritchSmithingTableBlockEntity.this.craftTime = value;
                    case 1 -> EldritchSmithingTableBlockEntity.this.maxCraftTime = value;
                    case 2 -> EldritchSmithingTableBlockEntity.this.burnTime = value;
                    case 3 -> EldritchSmithingTableBlockEntity.this.fuelTime = value;
                }
            }
            
            @Override
            public int size() { return 4; }
        };
    }
    
    @Override
    public Text getDisplayName() {
        return Text.literal("Eldritch Smithing Table");
    }
    
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    
    private boolean isBurning() {
        return burnTime > 0;
    }
    
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("CraftTime", craftTime);
        nbt.putInt("MaxCraftTime", maxCraftTime);
        nbt.putInt("BurnTime", burnTime);
    }
    
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        craftTime = nbt.getInt("CraftTime");
        maxCraftTime = nbt.getInt("MaxCraftTime");
        burnTime = nbt.getInt("BurnTime");
        this.fuelTime = this.getFuelTime(this.inventory.get(1));
    }
    
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EldritchSmithingTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
    
    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == OUTPUT_SLOT) return false;
        if (slot == FUEL_SLOT) {
            ItemStack fuelStack = inventory.get(FUEL_SLOT);
            return canUseAsFuel(stack) || stack.isOf(Items.BUCKET) && !fuelStack.isOf(Items.BUCKET);
        }
        return true;
    }
    
    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) return 0;
        Item item = fuel.getItem();
        return AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(item, 0);
    }
    public static boolean canUseAsFuel(ItemStack stack) {
        return AbstractFurnaceBlockEntity.createFuelTimeMap().containsKey(stack.getItem());
    }
    
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
    
    
    public void tick(World world, BlockPos pos, BlockState state, EldritchSmithingTableBlockEntity blockEntity) {
        Optional<RecipeEntry<EldritchSmithingRecipe>> recipe = matchGetter.getFirstMatch(blockEntity, getWorld());
        
        if (isBurning()) --burnTime;
        
        ItemStack fuelStack = inventory.get(FUEL_SLOT);
        
        if (isBurning() || !fuelStack.isEmpty()) {
            if (hasRecipe(recipe)) {
                if (!isBurning()) {
                    fuelTime = burnTime = getFuelTime(fuelStack);
                    if (isBurning()) {
                        markDirty(world, pos, state);
                        if (!fuelStack.isEmpty()) {
                            Item item = fuelStack.getItem();
                            fuelStack.decrement(1);
                            if (fuelStack.isEmpty()) {
                                Item itemRemainder = item.getRecipeRemainder();
                                inventory.set(FUEL_SLOT, itemRemainder == null ? ItemStack.EMPTY : new ItemStack(itemRemainder));
                            }
                        }
                    }
                }
            }
        }
        
        craft(world, pos, state, blockEntity);
    }
    
    public void craft(World world, BlockPos pos, BlockState state, EldritchSmithingTableBlockEntity blockEntity) {
        Optional<RecipeEntry<EldritchSmithingRecipe>> recipe = matchGetter.getFirstMatch(blockEntity, getWorld());
        
        if (isBurning()) {
            if (this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount()) {
                if (hasRecipe(recipe)) {
                    craftTime++;
                    markDirty(world, pos, state);
                    maxCraftTime = recipe.map(recipe1 -> recipe1.value().getCraftingTime()).orElse(200);
                    if (craftTime >= maxCraftTime) {
                        craftItem(recipe);
                        resetProgress();
                    }
                } else {
                    resetProgress();
                }
            } else {
                resetProgress();
            }
        } else if (craftTime > 0) {
            craftTime--;
        }
    }
    
    private void resetProgress() { craftTime = 0; }
    
    private void craftItem(Optional<RecipeEntry<EldritchSmithingRecipe>> recipe) {
        clearInputs();
        setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
    }
    
    private void clearInputs() {
        clearInputs(1);
    }
    private void clearInputs(int count) {
        removeStack(1, count);
        removeStack(2, count);
        removeStack(3, count);
        removeStack(4, count);
        removeStack(5, count);
    }
    
    private boolean hasRecipe(Optional<RecipeEntry<EldritchSmithingRecipe>> recipe) {
        return recipe.isPresent() && this.getStack(OUTPUT_SLOT).getCount() + 
                recipe.get().value().getResult(null).getCount() <= getStack(OUTPUT_SLOT).getMaxCount() &&
                (this.getStack(OUTPUT_SLOT).getItem() == recipe.get().value().getResult(null).getItem() || this.getStack(OUTPUT_SLOT).isEmpty());
    }
}

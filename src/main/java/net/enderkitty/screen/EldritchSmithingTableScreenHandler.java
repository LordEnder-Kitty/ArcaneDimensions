package net.enderkitty.screen;

import net.enderkitty.block.block_entities.EldritchSmithingTableBlockEntity;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;

public class EldritchSmithingTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final EldritchSmithingTableBlockEntity blockEntity;
    
    public EldritchSmithingTableScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()), new ArrayPropertyDelegate(7));
    }
    
    public EldritchSmithingTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ArcaneDimsScreenHandlers.ELDRITCH_SMITHING_TABLE_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 7);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = ((EldritchSmithingTableBlockEntity) blockEntity);
        
        this.addSlot(new Slot(inventory, 1, 62, 13)); // Top
        this.addSlot(new Slot(inventory, 2, 40, 35)); // Left
        this.addSlot(new Slot(inventory, 3, 62, 35)); // Center
        this.addSlot(new Slot(inventory, 4, 84, 35)); // Right
        this.addSlot(new Slot(inventory, 5, 62, 57)); // Bottom
        
        this.addSlot(new Slot(inventory, 0, 143, 13) { // Output
            @Override public boolean canInsert(ItemStack stack) { return false; }
        });
        this.addSlot(new Slot(inventory, 6, 143, 57) { // Fuel
            @Override public boolean canInsert(ItemStack stack) { return canUseAsFuel(stack); }
            private static boolean canUseAsFuel(ItemStack stack) { return AbstractFurnaceBlockEntity.createFuelTimeMap().containsKey(stack.getItem()); }
        });
        
        // Player's inventory
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
        // Player's hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        
        addProperties(propertyDelegate);
    }
    
    public boolean isBurning() {
        return propertyDelegate.get(2) > 0;
    }
    
    public float getCraftProgress() {
        int i = this.propertyDelegate.get(0);
        int j = this.propertyDelegate.get(1);
        if (j == 0 || i == 0) {
            return 0.0f;
        }
        return MathHelper.clamp((float)i / (float)j, 0.0f, 1.0f);
    }
    public float getFuelProgress() {
        int i = this.propertyDelegate.get(3);
        if (i == 0) i = 200;
        return MathHelper.clamp((float)this.propertyDelegate.get(2) / (float)i, 0.0f, 1.0f);
    }
    
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        
        return newStack;
    }
    
    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}

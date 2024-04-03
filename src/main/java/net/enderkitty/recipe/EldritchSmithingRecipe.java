package net.enderkitty.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class EldritchSmithingRecipe implements Recipe<SidedInventory> {
    private final Ingredient top; // 1
    private final Ingredient bottom; // 5
    private final Ingredient right; // 4
    private final Ingredient left; // 2
    private final Ingredient center; // 3
    private final ItemStack result; // 0
    protected final int craftingTime;
    
    private int topIndex = 1;
    private int bottomIndex = 5;
    private int rightIndex = 4;
    private int leftIndex = 2;
    private int centerIndex = 3;
    
    public EldritchSmithingRecipe(Ingredient top, Ingredient bottom, Ingredient right, Ingredient left, Ingredient center, ItemStack result, int craftingTime) {
        this.result = result;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;
        this.center = center;
        this.craftingTime = craftingTime;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        defaultedList.add(this.top);
        defaultedList.add(this.bottom);
        defaultedList.add(this.right);
        defaultedList.add(this.left);
        defaultedList.add(this.center);
        return defaultedList;
    }
    
    
    
    @Override
    public boolean matches(SidedInventory inventory, World world) {
        if (world.isClient) return false;
        
        return this.top.test(inventory.getStack(topIndex)) && this.bottom.test(inventory.getStack(bottomIndex)) && 
                this.right.test(inventory.getStack(rightIndex)) && this.left.test(inventory.getStack(leftIndex)) && this.center.test(inventory.getStack(centerIndex));
    }
    
    @Override
    public ItemStack craft(SidedInventory inventory, DynamicRegistryManager registryManager) {
        return result;
    }
    @Override
    public boolean fits(int width, int height) {
        return true;
    }
    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return result;
    }
    
    @Override public RecipeSerializer<?> getSerializer() { return ArcaneDimsRecipeRegistries.ELDRITCH_SMITHING_SERIALIZER; }
    @Override public RecipeType<?> getType() { return ArcaneDimsRecipeRegistries.ELDRITCH_SMITHING_TYPE; }
    
    
    
    public static class Serializer implements RecipeSerializer<EldritchSmithingRecipe> {
        
        private static final Codec<EldritchSmithingRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("top").forGetter(recipe -> recipe.top),
                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("bottom").forGetter(recipe -> recipe.bottom),
                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("right").forGetter(recipe -> recipe.right),
                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("left").forGetter(recipe -> recipe.left),
                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("center").forGetter(recipe -> recipe.center),
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                Codec.INT.fieldOf("craftingTime").forGetter(recipe -> recipe.craftingTime)
        ).apply(instance, EldritchSmithingRecipe::new));
        
        
        @Override
        public Codec<EldritchSmithingRecipe> codec() {
            return CODEC;
        }
        
        @Override
        public EldritchSmithingRecipe read(PacketByteBuf buf) {
            Ingredient top = Ingredient.fromPacket(buf);
            Ingredient bottom = Ingredient.fromPacket(buf);
            Ingredient right = Ingredient.fromPacket(buf);
            Ingredient left = Ingredient.fromPacket(buf);
            Ingredient center = Ingredient.fromPacket(buf);
            ItemStack itemStack = buf.readItemStack();
            int craftingTime = buf.readVarInt();
            return new EldritchSmithingRecipe(top, bottom, right, left, center, itemStack, craftingTime);
        }
        
        @Override
        public void write(PacketByteBuf buf, EldritchSmithingRecipe recipe) {
            recipe.top.write(buf);
            recipe.bottom.write(buf);
            recipe.right.write(buf);
            recipe.left.write(buf);
            recipe.center.write(buf);
            buf.writeVarInt(recipe.craftingTime);
            buf.writeItemStack(recipe.result);
        }
    }
    
}

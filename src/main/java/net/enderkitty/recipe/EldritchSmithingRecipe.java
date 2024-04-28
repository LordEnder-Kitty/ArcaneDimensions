package net.enderkitty.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.enderkitty.ArcaneDimensions;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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

    public int getCraftingTime() {
        return craftingTime;
    }
    
    @Override
    public boolean matches(SidedInventory inventory, World world) {
        if (world.isClient) return false;
        
        return this.top.test(inventory.getStack(topIndex)) && 
                this.bottom.test(inventory.getStack(bottomIndex)) && 
                this.right.test(inventory.getStack(rightIndex)) && 
                this.left.test(inventory.getStack(leftIndex)) && 
                this.center.test(inventory.getStack(centerIndex));
    }
    
    @Override
    public ItemStack craft(SidedInventory inventory, DynamicRegistryManager registryManager) {
        return result.copy();
    }
    @Override
    public boolean fits(int width, int height) {
        return true;
    }
    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return result;
    }
    
    @Override public RecipeSerializer<?> getSerializer() { return Serializer.INSTANCE; }
    @Override public RecipeType<?> getType() { return Type.INSTANCE; }
    
    
    
    public static class Serializer implements RecipeSerializer<EldritchSmithingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final Identifier ID = new Identifier(ArcaneDimensions.MOD_ID, "eldritch_smithing");
        
        
        private static final Codec<EldritchSmithingRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("top", Ingredient.EMPTY).forGetter(recipe -> recipe.top),
                Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("bottom", Ingredient.EMPTY).forGetter(recipe -> recipe.bottom),
                Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("right", Ingredient.EMPTY).forGetter(recipe -> recipe.right),
                Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("left", Ingredient.EMPTY).forGetter(recipe -> recipe.left),
                Ingredient.ALLOW_EMPTY_CODEC.optionalFieldOf("center", Ingredient.EMPTY).forGetter(recipe -> recipe.center),
                ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                Codec.INT.optionalFieldOf("craftingTime", 200).forGetter(recipe -> recipe.craftingTime)
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

    public static class Type implements RecipeType<EldritchSmithingRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "eldritch_smithing";
    }
    
}

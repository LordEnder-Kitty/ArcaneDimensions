package net.enderkitty.item;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ArcaneDimsToolMaterials implements ToolMaterial {
    
    SENIOREM_WOOD(1, 70, 2.5f, 0.0f, 18, () -> Ingredient.ofItems(ArcaneDimsBlocks.SENIOREM_PLANKS)),
    ELDERSTONE(1, 140, 5f, 1.5f, 16, () -> Ingredient.ofItems(ArcaneDimsBlocks.ELDERSTONE));
    
    
    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;
    
    ArcaneDimsToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy<Ingredient>(repairIngredient);
    }
    
    @Override
    public int getDurability() {
        return this.itemDurability;
    }
    
    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }
    
    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }
    
    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }
    
    @Override
    public int getEnchantability() {
        return this.enchantability;
    }
    
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}

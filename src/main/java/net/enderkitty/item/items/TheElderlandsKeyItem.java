package net.enderkitty.item.items;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.block.block_entities.WardCageBlockEntity;
import net.enderkitty.item.ArcaneDimsItems;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TheElderlandsKeyItem extends Item {
    public static final String CHARGED_KEY = "Charged";
    
    public TheElderlandsKeyItem(Settings settings) {
        super(settings);
    }
    
    public static ItemStack keyStack() {
        ItemStack stack = new ItemStack(ArcaneDimsItems.ELDERLANDS_PORTAL_KEY);
        stack.getOrCreateNbt().putBoolean(CHARGED_KEY, false);
        return stack;
    }
    public static ItemStack keyStackCharged() {
        ItemStack stack = new ItemStack(ArcaneDimsItems.ELDERLANDS_PORTAL_KEY);
        stack.getOrCreateNbt().putBoolean(CHARGED_KEY, true);
        return stack;
    }
    public static boolean isKeyCharged(ItemStack stack) {
        return stack.getOrCreateNbt().getBoolean(CHARGED_KEY);
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        ItemStack itemStack = context.getStack();
        
        if (world.getBlockState(pos).isOf(ArcaneDimsBlocks.WARD_CAGE)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof WardCageBlockEntity wardCage) {
                if (itemStack.getOrCreateNbt().contains(CHARGED_KEY) && !itemStack.getOrCreateNbt().getBoolean(CHARGED_KEY) && wardCage.getSouls() > 0) {
                    itemStack.getOrCreateNbt().putBoolean(CHARGED_KEY, true);
                    wardCage.setSouls(wardCage.getSouls() - 1);
                    world.playSound(null, pos, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1, 1);
                } else {
                    world.playSound(null, pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1, 0.5f);
                }
            }
        }
        
        return super.useOnBlock(context);
    }
    
    @Override
    public boolean hasGlint(ItemStack stack) {
        return TheElderlandsKeyItem.isKeyCharged(stack) || super.hasGlint(stack);
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Text charged = Text.translatable("item.arcanedims.elderlands_portal_key.tooltip");
        tooltip.add(Text.literal(charged.getString() + stack.getOrCreateNbt().getBoolean(CHARGED_KEY)));
        super.appendTooltip(stack, world, tooltip, context);
    }
    
    @Override
    public String getTranslationKey(ItemStack stack) {
        return stack.getOrCreateNbt().getBoolean(CHARGED_KEY) ? "item.arcanedims.elderlands_portal_key.charged" : super.getTranslationKey(stack);
    }
}

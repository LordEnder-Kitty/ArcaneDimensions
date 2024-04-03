package net.enderkitty.item.items;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerItemCooldownManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WardCageItem extends BlockItem {
    public WardCageItem(Block block, Settings settings) {
        super(block, settings);
    }
    
    public static final String SOULS_KEY = "Souls";
    public static final String BLOCKENTITYTAG_KEY = "BlockEntityTag";
    
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return ActionResult.FAIL;
        }
        ItemStack itemStack = user.getStackInHand(hand);
        if (entity instanceof WardenEntity && entity.getHealth() <= entity.getMaxHealth() / 2 && itemStack.getOrCreateSubNbt(BLOCKENTITYTAG_KEY).getInt(SOULS_KEY) < 10) {
            user.getItemCooldownManager().set(this, 20);
            entity.damage(entity.getWorld().getDamageSources().starve(), 80);
            itemStack.getOrCreateSubNbt(BLOCKENTITYTAG_KEY).putInt(SOULS_KEY, stack.getOrCreateSubNbt(BLOCKENTITYTAG_KEY).getInt(SOULS_KEY) + 1);
            entity.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 1);
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Text cagedSouls = Text.translatable("item.arcanedims.ward_cage.tooltop");
        tooltip.add(Text.literal(cagedSouls.getString() + stack.getOrCreateSubNbt(BLOCKENTITYTAG_KEY).getInt(SOULS_KEY)));
        super.appendTooltip(stack, world, tooltip, context);
    }
    
    public static boolean hasSouls(ItemStack stack) {
        return stack.getOrCreateSubNbt(BLOCKENTITYTAG_KEY).getInt(SOULS_KEY) > 0;
    }
}

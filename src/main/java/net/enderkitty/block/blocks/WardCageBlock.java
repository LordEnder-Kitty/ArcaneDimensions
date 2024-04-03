package net.enderkitty.block.blocks;

import net.enderkitty.block.block_entities.WardCageBlockEntity;
import net.enderkitty.item.ArcaneDimsItems;
import net.enderkitty.item.items.WardCageItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class WardCageBlock extends Block implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    
    public WardCageBlock(Settings settings) {
        super(settings);
    }

    @Nullable @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WardCageBlockEntity(pos, state);
    }

    /* Rotation */
    @Nullable @Override public BlockState getPlacementState(ItemPlacementContext ctx) {return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()); }
    @Override public BlockState rotate(BlockState state, BlockRotation rotation) { return state.with(FACING, rotation.rotate(state.get(FACING))); }
    @Override public BlockState mirror(BlockState state, BlockMirror mirror) { return state.rotate(mirror.getRotation(state.get(FACING))); }
    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(FACING); }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random rand) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof WardCageBlockEntity && (int)world.getBlockEntityRenderData(pos) > 0) {
            Random random = new Random();
            Random random2 = new Random();
            for (int i = 0; i < (int) world.getBlockEntityRenderData(pos); i++) {
                float offsetX = random.nextFloat(-0.4f, 0.4f);
                float offsetY = random.nextFloat(-0.3f, 0.3f);
                float offsetZ = random.nextFloat(-0.4f, 0.4f);
                float offsetXVelocity = random2.nextFloat(-0.001f, 0.001f);
                float offsetYVelocity = random2.nextFloat(-0.001f, 0.001f);
                float offsetZVelocity = random2.nextFloat(-0.001f, 0.001f);
                world.addParticle(ParticleTypes.SOUL,
                        blockEntity.getPos().getX() + 0.5f + offsetX,
                        blockEntity.getPos().getY() + 0.5f + offsetY,
                        blockEntity.getPos().getZ() + 0.5f + offsetZ,
                        offsetXVelocity, offsetYVelocity, offsetZVelocity);
            }
        }
        super.randomDisplayTick(state, world, pos, rand);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (itemStack.getOrCreateNbt().contains(WardCageItem.SOULS_KEY) && itemStack.getSubNbt(WardCageItem.BLOCKENTITYTAG_KEY).contains(WardCageItem.SOULS_KEY)) {
            if (blockEntity instanceof WardCageBlockEntity cageBlock) {
                cageBlock.setSouls(itemStack.getOrCreateNbt().getInt(WardCageItem.SOULS_KEY));
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof WardCageBlockEntity) {
            if (!world.isClient && player.isCreative()) {
                ItemStack itemStack = new ItemStack(ArcaneDimsItems.WARD_CAGE);
                blockEntity.setStackNbt(itemStack);
                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, itemStack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
        }
        return super.onBreak(world, pos, state, player);
    }
}

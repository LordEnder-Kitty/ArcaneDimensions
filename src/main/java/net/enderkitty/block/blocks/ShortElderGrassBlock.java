package net.enderkitty.block.blocks;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ShortElderGrassBlock extends ShortPlantBlock {
    public ShortElderGrassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        TallPlantBlock tallPlantBlock = (TallPlantBlock)(state.isOf(ArcaneDimsBlocks.ELDER_GRASS) ? ArcaneDimsBlocks.ELDER_FLY_SPORE_GRASS : Blocks.TALL_GRASS);
        if (tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
            TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
        }
    }
}

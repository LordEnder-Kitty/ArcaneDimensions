package net.enderkitty.block.blocks;

import net.enderkitty.client.particle.ArcaneDimsParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ElderFlySporeGrassBlock extends TallPlantBlock {
    public ElderFlySporeGrassBlock(Settings settings) {
        super(settings);
    }


    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        
        super.randomTick(state, world, pos, random);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        int rand = new Random().nextInt(1, 20);
        if (rand > 1) return; else 
            world.addParticle(ArcaneDimsParticles.ELDER_FLY_SPORE, 
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 
                new Random().nextDouble(-0.1, 0.1), 
                new Random().nextDouble(0.3), 
                new Random().nextDouble(-0.1, 0.1));
        
        super.randomDisplayTick(state, world, pos, random);
    }
}

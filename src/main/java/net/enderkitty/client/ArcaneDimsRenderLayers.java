package net.enderkitty.client;

import net.enderkitty.block.ArcaneDimsBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ArcaneDimsRenderLayers {
    
    public static void register() {
        BlockRenderLayerMap.INSTANCE.putBlock(ArcaneDimsBlocks.SENIOREM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ArcaneDimsBlocks.SENIOREM_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ArcaneDimsBlocks.WARD_CAGE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ArcaneDimsBlocks.SENIOREM_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ArcaneDimsBlocks.SENIOREM_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ArcaneDimsBlocks.ELDER_GRASS_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ArcaneDimsBlocks.ELDER_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ArcaneDimsBlocks.ELDER_FLY_SPORE_GRASS, RenderLayer.getCutout());
    }
}

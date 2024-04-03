package net.enderkitty;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.enderkitty.block.ArcaneDimsBlocks;
import net.enderkitty.client.ArcaneDimsColors;
import net.enderkitty.client.ArcaneDimsRenderLayers;
import net.enderkitty.client.entity_renderers.InvisibleEntityRenderer;
import net.enderkitty.client.particle.ArcaneDimsParticles;
import net.enderkitty.client.particle.ElderFlySporeParticle;
import net.enderkitty.entity.ArcaneDimsBoats;
import net.enderkitty.entity.ArcaneDimsEntities;
import net.enderkitty.screen.ArcaneDimsScreenHandlers;
import net.enderkitty.screen.EldritchSmithingTableScreen;
import net.enderkitty.util.ArcaneDimsModelPredicateProviders;
import net.enderkitty.util.FlammableAndStrippable;
import net.enderkitty.util.MiscIdentifiers;
import net.enderkitty.world.dimension.ArcaneDimsDimensionTypes;
import net.enderkitty.world.dimension.ElderlandsDimensionEffects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;

public class ArcaneDimensionsClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        ArcaneDimsRenderLayers.register();
        
        ArcaneDimsColors.registerBlockColors();
        ArcaneDimsColors.registerItemColors();
        
        ParticleRenderEvents.ALLOW_BLOCK_DUST_TINT.register(((state, world, pos) -> !state.isOf(ArcaneDimsBlocks.ELDER_GRASS_BLOCK)));
        
        FlammableAndStrippable.registerFlammable();
        FlammableAndStrippable.registerStrippable();

        DimensionRenderingRegistry.registerDimensionEffects(ArcaneDimsDimensionTypes.THE_ELDERLANDS_ID, ElderlandsDimensionEffects.elderlands);
        
        ArcaneDimsModelPredicateProviders.registerPredicates();

        ParticleFactoryRegistry.getInstance().register(ArcaneDimsParticles.ELDER_FLY_SPORE, ElderFlySporeParticle.Factory::new);
        
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, MiscIdentifiers.SENIOREM_SIGN_TEXTURE));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, MiscIdentifiers.SENIOREM_HANGING_SIGN_TEXTURE));
        
        TerraformBoatClientHelper.registerModelLayers(ArcaneDimsBoats.SENIOREM_BOAT_ID, false);

        HandledScreens.register(ArcaneDimsScreenHandlers.ELDRITCH_SMITHING_TABLE_SCREEN_HANDLER, EldritchSmithingTableScreen::new);
        
        EntityRendererRegistry.register(ArcaneDimsEntities.SOUL_SWARM, InvisibleEntityRenderer::new);
        EntityRendererRegistry.register(ArcaneDimsEntities.ELDER_FLY, InvisibleEntityRenderer::new);
        EntityRendererRegistry.register(ArcaneDimsEntities.SOUL_CHARGE, InvisibleEntityRenderer::new);
        
        
        //LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
        //    if (entityRenderer instanceof PlayerEntityRenderer playerEntityRenderer) {
        //        registrationHelper.register(new LordEnder_KittyFeatureRenderer(playerEntityRenderer));
        //    }
        //});
    }
}

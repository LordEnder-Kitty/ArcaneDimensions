package net.enderkitty.client.entity_renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

@Environment(value = EnvType.CLIENT)
public class InvisibleEntityRenderer extends EntityRenderer<Entity> {
    public InvisibleEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }
    
    @Override
    public Identifier getTexture(Entity entity) {
        return null;
    }
}

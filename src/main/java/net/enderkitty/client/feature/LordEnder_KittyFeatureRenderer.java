package net.enderkitty.client.feature;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class LordEnder_KittyFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public LordEnder_KittyFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, float h, float j, float k, float l) {
//        if (!"LordEnder_Kitty".equals(abstractClientPlayerEntity.getName().getString()) || abstractClientPlayerEntity.isInvisible()) {
//            return;
//        }
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(abstractClientPlayerEntity.getSkinTextures().texture()));
        int m = LivingEntityRenderer.getOverlay(abstractClientPlayerEntity, 0.0f);
        for (int n = 0; n < 2; ++n) {
            float o = MathHelper.lerp(h, abstractClientPlayerEntity.prevYaw, abstractClientPlayerEntity.getYaw()) - 
                    MathHelper.lerp(h, abstractClientPlayerEntity.prevBodyYaw, abstractClientPlayerEntity.bodyYaw);
            float p = MathHelper.lerp(h, abstractClientPlayerEntity.prevPitch, abstractClientPlayerEntity.getPitch());
            matrixStack.push();
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(o));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(p));
            matrixStack.translate(0.375f * (float)(n * 2 - 1), 0.0f, 0.0f);
            matrixStack.translate(0.0f, -0.375f, 0.0f);
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-p));
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-o));
            float q = 1.3333334f;
            matrixStack.scale(1.3333334f, 1.3333334f, 1.3333334f);
            ((PlayerEntityModel<?>)this.getContextModel()).renderEars(matrixStack, vertexConsumer, i, m);
            matrixStack.pop();
        }
    }
}

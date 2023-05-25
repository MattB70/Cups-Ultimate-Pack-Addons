package com.mattborle.cupsaddons.client.renderer.entity;

import com.mattborle.cupsaddons.CupsAddons;
import com.mattborle.cupsaddons.client.model.entity.SparkModel;
import com.mattborle.cupsaddons.entity.SparkEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SparkRenderer extends GeoEntityRenderer<SparkEntity> {

    public SparkRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SparkModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(SparkEntity animatable) {
        return new ResourceLocation(CupsAddons.MOD_ID, "textures/entities/spark.png");
    }

    @Override
    public RenderType getRenderType(SparkEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        poseStack.scale(3f, 3f, 3f); // Scale the mob visually
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}

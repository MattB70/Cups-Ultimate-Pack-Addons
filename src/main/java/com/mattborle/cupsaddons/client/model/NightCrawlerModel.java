package com.mattborle.cupsaddons.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

//  TODO: Re-write this to take advantage of GeckoLib as the regenerated MCreator model is unreadable.
public class NightCrawlerModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("cupsaddons", "modelnight_crawler"), "main");
    public final ModelPart RightLeg;
    public final ModelPart LeftLeg;
    public final ModelPart body;
    public final ModelPart head;

    public NightCrawlerModel(ModelPart root) {
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.body = root.getChild("body");
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.1842F, 3.427F, 1.4983F));
        PartDefinition cube_r1 = RightLeg.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(112, -8).addBox(2.25F, -2.5223F, 10.7775F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.1842F, 0.6861F, 0.2166F, -1.6581F, 3.1416F, 0.0F));
        PartDefinition right_leg_r1 = RightLeg.addOrReplaceChild("right_leg_r1",
                CubeListBuilder.create().texOffs(34, 34).addBox(-3.75F, 15.9078F, -3.4429F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.1842F, 0.6861F, 0.2166F, 0.1309F, 0.0F, 0.0F));
        PartDefinition right_leg_r2 = RightLeg.addOrReplaceChild("right_leg_r2",
                CubeListBuilder.create().texOffs(0, 24).addBox(-3.75F, -0.865F, -1.9675F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.1842F, 0.6861F, 0.2166F, -0.0873F, 0.0F, 0.0F));
        PartDefinition right_leg_r3 = RightLeg.addOrReplaceChild("right_leg_r3",
                CubeListBuilder.create().texOffs(0, 48).addBox(-3.75F, -1.1063F, -1.2307F, 3.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.1842F, 0.6861F, 0.2166F, -0.1309F, 0.0F, 0.0F));
        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.1881F, 3.3671F, 1.4983F));
        PartDefinition cube_r2 = LeftLeg.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(112, -8).addBox(-2.25F, -2.5223F, 10.7775F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.1881F, 0.7461F, 0.2166F, -1.6581F, 3.1416F, 0.0F));
        PartDefinition right_leg_r4 = LeftLeg.addOrReplaceChild("right_leg_r4",
                CubeListBuilder.create().texOffs(21, 21).addBox(0.75F, -0.865F, -1.9675F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.1881F, 0.7461F, 0.2166F, -0.0873F, 0.0F, 0.0F));
        PartDefinition right_leg_r5 = LeftLeg.addOrReplaceChild("right_leg_r5",
                CubeListBuilder.create().texOffs(34, 34).addBox(0.75F, 15.9078F, -3.4429F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.1881F, 0.7461F, 0.2166F, 0.1309F, 0.0F, 0.0F));
        PartDefinition right_leg_r6 = LeftLeg.addOrReplaceChild("right_leg_r6",
                CubeListBuilder.create().texOffs(42, 0).addBox(0.75F, -1.1063F, -1.2307F, 3.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-2.1881F, 0.7461F, 0.2166F, -0.1309F, 0.0F, 0.0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.75F, 1.5F));
        PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3",
                CubeListBuilder.create().texOffs(30, 0).addBox(-2.5F, -3.0F, -1.3F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.0F, -3.0F, -0.95F, 0.5672F, 0.0F, 0.0F));
        PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4",
                CubeListBuilder.create().texOffs(112, -8).addBox(-3.1F, -3.75F, -5.25F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(112, -8)
                        .addBox(-0.1F, -5.75F, -5.25F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.5F, 3.9F, 3.0F, -1.5708F, -2.2689F, 0.0F));
        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5",
                CubeListBuilder.create().texOffs(112, -8).addBox(3.75F, -3.0F, -5.25F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(112, -8)
                        .addBox(0.75F, -5.0F, -5.25F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.5F, 3.9F, 3.0F, -1.5708F, 2.2689F, 0.0F));
        PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6",
                CubeListBuilder.create().texOffs(112, -8).addBox(0.5F, -5.25F, -5.25F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.5F, 3.9F, 3.0F, -1.6581F, 3.1416F, 0.0F));
        PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7",
                CubeListBuilder.create().texOffs(112, -8).addBox(0.0F, -2.0F, -3.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.5F, 3.9F, 3.0F, 1.5708F, -1.3614F, -1.5708F));
        PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8",
                CubeListBuilder.create().texOffs(1, 1).addBox(-4.5F, -3.0F, -2.3F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.5F, 0.0F, -0.2F, 0.0873F, 0.0F, 0.0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -5.201F, 0.6782F));
        PartDefinition cube_r9 = head.addOrReplaceChild("cube_r9",
                CubeListBuilder.create().texOffs(112, -8).addBox(1.5F, -5.5F, -14.15F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.5F, 9.851F, 3.8218F, -1.6581F, 2.7925F, 0.0F));
        PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10",
                CubeListBuilder.create().texOffs(112, -8).addBox(-0.5F, -5.75F, -14.15F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.5F, 9.851F, 3.8218F, -1.6581F, -2.7925F, 0.0F));
        PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11",
                CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, -3.0F, -3.25F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.807F, -1.4913F, 0.1309F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green,
                               float blue, float alpha) {
        RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.head.xRot = headPitch / (180F / (float) Math.PI);
        this.LeftLeg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
        this.RightLeg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
    }
}

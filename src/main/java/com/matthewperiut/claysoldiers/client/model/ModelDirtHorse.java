package com.matthewperiut.claysoldiers.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelDirtHorse extends BipedEntityModel {

    public ModelPart bipedEar1;
    public ModelPart bipedEar2;
    public ModelPart bipedTail;
    public ModelPart bipedNeck;
    public ModelPart bipedMane;

    public ModelPart head;
    public ModelPart body;
    public ModelPart leg1;
    public ModelPart leg2;
    public ModelPart leg3;
    public ModelPart leg4;

    public ModelDirtHorse() {
        this(0.0F);
    }

    public ModelDirtHorse(float f) {
        this(f, 0.0F);
    }

    public ModelDirtHorse(float f, float f1) {
        this.head = new ModelPart(0, 0);
        this.head.addCuboid(-1.0F, 0.0F, -4.0F, 2, 2, 4, f + 0.2F);
        this.head.setPivot(0.0F, -3.75F + f1, -7.75F);
        this.bipedEar1 = new ModelPart(0, 0);
        this.bipedEar1.addCuboid(-1.25F, -0.8F, -1.0F, 1, 1, 1, f - 0.1F);
        this.bipedEar1.setPivot(0.0F, -3.75F + f1, -7.75F);
        this.bipedEar2 = new ModelPart(0, 0);
        this.bipedEar2.addCuboid(0.25F, -0.8F, -1.0F, 1, 1, 1, f - 0.1F);
        this.bipedEar2.setPivot(0.0F, -3.75F + f1, -7.75F);
        this.body = new ModelPart(0, 8);
        this.body.addCuboid(-2.0F, 0.0F, -4.0F, 4, 4, 8, f);
        this.body.setPivot(0.0F, 0.0F + f1, 0.0F);
        this.bipedNeck = new ModelPart(12, 0);
        this.bipedNeck.addCuboid(-1.0F, 0.0F, -6.0F, 2, 2, 6, f + 0.4F);
        this.bipedNeck.setPivot(0.0F, 0.0F + f1, -2.0F);
        this.bipedMane = new ModelPart(28, 0);
        this.bipedMane.addCuboid(-1.0F, -1.1F, -6.0F, 2, 1, 6, f);
        this.bipedMane.setPivot(0.0F, 0.0F + f1, -2.0F);
        this.leg1 = new ModelPart(24, 10);
        this.leg1.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.leg1.setPivot(-1.0F, 3.75F + f1, -2.75F);
        this.leg2 = new ModelPart(24, 10);
        this.leg2.mirror = true;
        this.leg2.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.leg2.setPivot(1.0F, 3.75F + f1, -2.75F);
        this.leg3 = new ModelPart(24, 10);
        this.leg3.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.leg3.setPivot(-1.0F, 3.75F + f1, 2.75F);
        this.leg4 = new ModelPart(24, 10);
        this.leg4.mirror = true;
        this.leg4.addCuboid(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.leg4.setPivot(1.0F, 3.75F + f1, 2.75F);
        this.bipedTail = new ModelPart(36, 11);
        this.bipedTail.addCuboid(-0.5F, 0.0F, -0.5F, 1, 5, 1, f + 0.15F);
        this.bipedTail.setPivot(0.0F, 0.0F + f1, 3.75F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.head.render(f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.bipedNeck.render(f5);
        this.bipedMane.render(f5);
        this.bipedTail.render(f5);
        this.bipedEar1.render(f5);
        this.bipedEar2.render(f5);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotFloat, float rotYaw, float rotPitch, float partTicks) {
        head.yaw = rotYaw / 57.29578F;
        head.pitch = (rotPitch / 57.29578F) + 0.79F;

        leg1.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.25F;
        leg2.pitch = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.25F;
        leg3.pitch = leg1.pitch;
        leg4.pitch = leg2.pitch;

        leg1.roll = 0.0F;
        leg2.roll = 0.0F;
        leg1.yaw = 0.0F;
        leg2.yaw = 0.0F;
        leg3.yaw = 0.0F;
        leg4.yaw = 0.0F;

        bipedTail.pitch = 0.3F + (leg1.pitch * leg1.pitch);
        bipedMane.pitch = bipedNeck.pitch = -0.6F;
        bipedEar1.pitch = bipedEar2.pitch = head.pitch;
        bipedEar1.yaw = bipedEar2.yaw = head.yaw;
    }
}

package com.matthewperiut.claysoldiers.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelDirtHorse extends BipedEntityModel {

    public Cuboid bipedEar1;
    public Cuboid bipedEar2;
    public Cuboid bipedTail;
    public Cuboid bipedNeck;
    public Cuboid bipedMane;

    public Cuboid head;
    public Cuboid body;
    public Cuboid leg1;
    public Cuboid leg2;
    public Cuboid leg3;
    public Cuboid leg4;

    public ModelDirtHorse() {
        this(0.0F);
    }

    public ModelDirtHorse(float f) {
        this(f, 0.0F);
    }

    public ModelDirtHorse(float f, float f1) {
        this.head = new Cuboid(0, 0);
        this.head.method_1818(-1.0F, 0.0F, -4.0F, 2, 2, 4, f + 0.2F);
        this.head.setRotationPoint(0.0F, -3.75F + f1, -7.75F);
        this.bipedEar1 = new Cuboid(0, 0);
        this.bipedEar1.method_1818(-1.25F, -0.8F, -1.0F, 1, 1, 1, f - 0.1F);
        this.bipedEar1.setRotationPoint(0.0F, -3.75F + f1, -7.75F);
        this.bipedEar2 = new Cuboid(0, 0);
        this.bipedEar2.method_1818(0.25F, -0.8F, -1.0F, 1, 1, 1, f - 0.1F);
        this.bipedEar2.setRotationPoint(0.0F, -3.75F + f1, -7.75F);
        this.body = new Cuboid(0, 8);
        this.body.method_1818(-2.0F, 0.0F, -4.0F, 4, 4, 8, f);
        this.body.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedNeck = new Cuboid(12, 0);
        this.bipedNeck.method_1818(-1.0F, 0.0F, -6.0F, 2, 2, 6, f + 0.4F);
        this.bipedNeck.setRotationPoint(0.0F, 0.0F + f1, -2.0F);
        this.bipedMane = new Cuboid(28, 0);
        this.bipedMane.method_1818(-1.0F, -1.1F, -6.0F, 2, 1, 6, f);
        this.bipedMane.setRotationPoint(0.0F, 0.0F + f1, -2.0F);
        this.leg1 = new Cuboid(24, 10);
        this.leg1.method_1818(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.leg1.setRotationPoint(-1.0F, 3.75F + f1, -2.75F);
        this.leg2 = new Cuboid(24, 10);
        this.leg2.mirror = true;
        this.leg2.method_1818(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.leg2.setRotationPoint(1.0F, 3.75F + f1, -2.75F);
        this.leg3 = new Cuboid(24, 10);
        this.leg3.method_1818(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.leg3.setRotationPoint(-1.0F, 3.75F + f1, 2.75F);
        this.leg4 = new Cuboid(24, 10);
        this.leg4.mirror = true;
        this.leg4.method_1818(-1.0F, 0.0F, -1.0F, 2, 8, 2, f - 0.25F);
        this.leg4.setRotationPoint(1.0F, 3.75F + f1, 2.75F);
        this.bipedTail = new Cuboid(36, 11);
        this.bipedTail.method_1818(-0.5F, 0.0F, -0.5F, 1, 5, 1, f + 0.15F);
        this.bipedTail.setRotationPoint(0.0F, 0.0F + f1, 3.75F);
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
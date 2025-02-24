package com.matthewperiut.claysoldiers.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.math.MathHelper;

public class ModelClayMan extends BipedEntityModel {

    public ModelPart bipedRightArmor;
    public ModelPart bipedLeftArmor;
    public ModelPart bipedChest;
    public ModelPart bipedRightPadding;
    public ModelPart bipedLeftPadding;
    public ModelPart bipedPadding;

    public ModelPart gooBase;
    public ModelPart logBase;

    public ModelPart bipedRock;

    public ModelPart speckyHead;
    public ModelPart speckyBody;
    public ModelPart speckyRightArm;
    public ModelPart speckyLeftArm;
    public ModelPart speckyRightLeg;
    public ModelPart speckyLeftLeg;

    public ModelPart stick;
    public ModelPart stickBlunt;
    public ModelPart stickSharp;

    public float armLeft;

    public boolean hasStick;
    public boolean hasArmor;
    public boolean hasCrown;
    public boolean hasSpecks;

    public boolean isClimbing;
    public boolean isSharpened;
    public boolean isPadded;
    public boolean isGooey;

    public boolean hasLogs;
    public boolean hasRocks;

    public ModelClayMan() {
        this(0.0F);
    }

    public ModelClayMan(float f) {
        this(f, 0.0F);
    }

    public ModelClayMan(float f, float f1) {
        // for all fields below (Cuboids/ModelParts):
        // method_1818 is addCuboid

        // field_619 is bipedHead
        head = new ModelPart(0, 0);
        head.addCuboid(-1.5F, -3.0F, -1.5F, 3, 3, 3, f);
        head.setPivot(0.0F, 0.0F + f1, 0.0F);

        // field_620 is hat
        hat = new ModelPart(19, 16);
        hat.addCuboid(-1.5F, -4.0F, -1.5F, 3, 2, 3, f + 0.3F);
        hat.setPivot(0.0F, 0.0F + f1, 0.0F);

        // field_621 is body
        body = new ModelPart(15, 0);
        body.addCuboid(-2.0F, 0.0F, -1.0F, 4, 5, 2, f);
        body.setPivot(0.0F, 0.0F + f1, 0.0F);

        // field_622 is rightArm
        rightArm = new ModelPart(9, 7);
        rightArm.addCuboid(-1.0F, -1.0F, -1.0F, 2, 6, 2, f);
        rightArm.setPivot(-3.0F, 1.0F + f1, 0.0F);

        // field_623 is leftArm
        leftArm = new ModelPart(9, 7);
        leftArm.mirror = true;
        leftArm.addCuboid(-1.0F, -1.0F, -1.0F, 2, 6, 2, f);
        leftArm.setPivot(3.0F, 1.0F + f1, 0.0F);

        // field_624 is rightLeg
        rightLeg = new ModelPart(0, 7);
        rightLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
        rightLeg.setPivot(-1.0F, 5.0F + f1, 0.0F);

        // field_625 is leftLeg
        leftLeg = new ModelPart(0, 7);
        leftLeg.mirror = true;
        leftLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
        leftLeg.setPivot(1.0F, 5.0F + f1, 0.0F);

        this.stick = new ModelPart(31, 11);
        this.stick.addCuboid(-0.5F, 3.5F, -4.0F, 1, 1, 3, f);
        this.stick.setPivot(-3.0F, 1.0F + f1, 0.0F);
        this.stickBlunt = new ModelPart(32, 12);
        this.stickBlunt.addCuboid(-0.5F, 3.5F, -6.0F, 1, 1, 2, f);
        this.stickBlunt.setPivot(-3.0F, 1.0F + f1, 0.0F);
        this.stickSharp = new ModelPart(9, 0);
        this.stickSharp.addCuboid(-0.5F, 3.5F, -5.5F, 1, 1, 2, f - 0.2F);
        this.stickSharp.setPivot(-3.0F, 1.0F + f1, 0.0F);
        this.bipedChest = new ModelPart(0, 21);
        this.bipedChest.addCuboid(-2.0F, 0.0F, -1.0F, 4, 4, 2, f + 0.3F);
        this.bipedChest.setPivot(0.0F, 0.0F + f1, 0.0F);
        this.bipedRightArmor = new ModelPart(0, 16);
        this.bipedRightArmor.addCuboid(-1.0F, -1.0F, -1.0F, 2, 2, 2, f + 0.4F);
        this.bipedRightArmor.setPivot(-3.0F, 1.0F + f1, 0.0F);
        this.bipedLeftArmor = new ModelPart(0, 16);
        this.bipedLeftArmor.mirror = true;
        this.bipedLeftArmor.addCuboid(-1.0F, -1.0F, -1.0F, 2, 2, 2, f + 0.4F);
        this.bipedLeftArmor.setPivot(3.0F, 1.0F + f1, 0.0F);
        this.bipedPadding = new ModelPart(12, 21);
        this.bipedPadding.addCuboid(-2.0F, 2.9F, -1.0F, 4, 2, 2, f + 0.2F);
        this.bipedPadding.setPivot(0.0F, 0.0F + f1, 0.0F);
        this.bipedRightPadding = new ModelPart(9, 16);
        this.bipedRightPadding.addCuboid(-1.0F, -0.1F, -1.0F, 2, 2, 2, f + 0.3F);
        this.bipedRightPadding.setPivot(-3.0F, 1.0F + f1, 0.0F);
        this.bipedLeftPadding = new ModelPart(9, 16);
        this.bipedLeftPadding.mirror = true;
        this.bipedLeftPadding.addCuboid(-1.0F, -0.1F, -1.0F, 2, 2, 2, f + 0.3F);
        this.bipedLeftPadding.setPivot(3.0F, 1.0F + f1, 0.0F);
        this.speckyHead = new ModelPart(37, 17);
        this.speckyHead.addCuboid(-1.5F, -3.0F, -1.5F, 3, 3, 3, f + 0.05F);
        this.speckyHead.setPivot(0.0F, 0.0F + f1, 0.0F);
        this.speckyBody = new ModelPart(52, 17);
        this.speckyBody.addCuboid(-2.0F, 0.0F, -1.0F, 4, 5, 2, f + 0.05F);
        this.speckyBody.setPivot(0.0F, 0.0F + f1, 0.0F);
        this.speckyRightArm = new ModelPart(37, 24);
        this.speckyRightArm.addCuboid(-1.0F, -1.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyRightArm.setPivot(-3.0F, 1.0F + f1, 0.0F);
        this.speckyLeftArm = new ModelPart(46, 24);
        this.speckyLeftArm.mirror = true;
        this.speckyLeftArm.addCuboid(-1.0F, -1.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyLeftArm.setPivot(3.0F, 1.0F + f1, 0.0F);
        this.speckyRightLeg = new ModelPart(46, 24);
        this.speckyRightLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyRightLeg.setPivot(-1.0F, 5.0F + f1, 0.0F);
        this.speckyLeftLeg = new ModelPart(37, 24);
        this.speckyLeftLeg.mirror = true;
        this.speckyLeftLeg.addCuboid(-1.0F, 0.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyLeftLeg.setPivot(1.0F, 5.0F + f1, 0.0F);
        this.gooBase = new ModelPart(0, 27);
        this.gooBase.addCuboid(-2.5F, 9.0F, -1.5F, 5, 2, 3, f);
        this.gooBase.setPivot(0.0F, 0.0F + f1, 0.0F);
        this.logBase = new ModelPart(16, 26);
        this.logBase.addCuboid(-2.5F, -6.5F, -1.5F, 5, 3, 3, f);
        this.logBase.setPivot(0.0F, 0.0F + f1, 0.0F);
        this.bipedRock = new ModelPart(31, 3);
        this.bipedRock.mirror = true;
        this.bipedRock.addCuboid(-1.0F, 3.5F, -1.0F, 2, 2, 2, f + 0.375F);
        this.bipedRock.setPivot(3.0F, 1.0F + f1, 0.0F);
    }

    @Override
    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        setAngles(f, f1, f2, f3, f4, f5);
        // render is render
        // head.render
        this.head.render(f5);
        // body.render
        this.body.render(f5);
        // rightarm.render
        this.rightArm.render(f5);
        // leftarm.render
        this.leftArm.render(f5);
        // rightleg.render
        this.rightLeg.render(f5);
        // leftleg.render
        this.leftLeg.render(f5);
        if (this.hasCrown) {
            // hat.render
            this.hat.render(f5);
        }
        if (this.hasStick) {
            this.stick.render(f5);
            if (this.isSharpened) {
                this.stickSharp.render(f5);
            } else {
                this.stickBlunt.render(f5);
            }
        }
        if (this.hasArmor) {
            this.bipedChest.render(f5);
            this.bipedRightArmor.render(f5);
            this.bipedLeftArmor.render(f5);
            if (this.isPadded) {
                this.bipedPadding.render(f5);
                this.bipedRightPadding.render(f5);
                this.bipedLeftPadding.render(f5);
            }
        }
        if (this.hasSpecks) {
            this.speckyHead.render(f5);
            this.speckyBody.render(f5);
            this.speckyRightArm.render(f5);
            this.speckyLeftArm.render(f5);
            this.speckyRightLeg.render(f5);
            this.speckyLeftLeg.render(f5);
        }
        if (this.isGooey)
            this.gooBase.render(f5);
        if (this.hasLogs)
            this.logBase.render(f5);
        if (this.hasRocks)
            this.bipedRock.render(f5);
    }

    public void setAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.yaw = f3 / 57.29578F;
        this.head.pitch = f4 / 57.29578F;
        this.hat.yaw = this.head.yaw;
        this.hat.pitch = this.head.pitch;
        this.rightArm.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        this.leftArm.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.rightArm.roll = 0.0F;
        this.leftArm.roll = 0.0F;
        this.rightLeg.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leftLeg.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        this.rightLeg.yaw = 0.0F;
        this.leftLeg.yaw = 0.0F;
        ModelPart var10000;
        if (this.riding) {
            var10000 = this.rightArm;
            var10000.pitch += -0.6283185F;
            var10000 = this.leftArm;
            var10000.pitch += -0.6283185F;
            this.rightLeg.pitch = -1.256637F;
            this.leftLeg.pitch = -1.256637F;
            this.rightLeg.yaw = 0.3141593F;
            this.leftLeg.yaw = -0.3141593F;
        }

        if (this.leftArmPose) {
            this.leftArm.pitch = this.leftArm.pitch * 0.5F - 0.3141593F;
        }

        if (this.rightArmPose) {
            this.rightArm.pitch = this.rightArm.pitch * 0.5F - 0.3141593F;
        }

        this.rightArm.yaw = 0.0F;
        this.leftArm.yaw = 0.0F;
        float f6;
        float f7;
        if (this.handSwingProgress > -9990.0F) {
            f6 = this.handSwingProgress;
            this.body.yaw = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593F * 2.0F) * 0.2F;
            var10000 = this.rightArm;
            var10000.yaw += this.body.yaw;
            var10000 = this.leftArm;
            var10000.yaw += this.body.yaw;
            var10000 = this.leftArm;
            var10000.pitch += this.body.yaw;
            f6 = 1.0F - this.handSwingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * 3.141593F);
            float f8 = MathHelper.sin(this.handSwingProgress * 3.141593F) * -(this.head.pitch - 0.7F) * 0.75F;
            var10000 = this.rightArm;
            var10000.pitch = (float) ((double) var10000.pitch - ((double) f7 * 1.2 + (double) f8));
            var10000 = this.rightArm;
            var10000.yaw += this.body.yaw * 2.0F;
            this.rightArm.roll = MathHelper.sin(this.handSwingProgress * 3.141593F) * -0.4F;
        }

        var10000 = this.rightArm;
        var10000.roll += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        var10000 = this.leftArm;
        var10000.roll -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        var10000 = this.rightArm;
        var10000.pitch += MathHelper.sin(f2 * 0.067F) * 0.05F;
        var10000 = this.leftArm;
        var10000.pitch -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        if (this.isClimbing) {
            --this.rightArm.pitch;
            --this.leftArm.pitch;
            var10000 = this.rightLeg;
            var10000.pitch -= 0.7F;
            var10000 = this.leftLeg;
            var10000.pitch -= 0.7F;
            var10000 = this.head;
            var10000.pitch -= 0.6F;
            var10000 = this.hat;
            var10000.pitch -= 0.6F;
        } else if (this.hasLogs) {
            this.rightArm.pitch = 3.141593F;
            this.leftArm.pitch = 3.141593F;
        } else if (this.armLeft > 0.0F) {
            f6 = -4.0F + this.armLeft * 4.0F;
            f7 = 1.0F - this.armLeft;
            this.leftArm.pitch = f6;
            this.leftArm.roll = f7;
        }

        this.speckyHead.pitch = this.head.pitch;
        this.speckyHead.yaw = this.head.yaw;
        this.bipedPadding.yaw = this.bipedChest.yaw = this.speckyBody.yaw = this.body.yaw;
        this.stickBlunt.pitch = this.stickSharp.pitch = this.stick.pitch = this.bipedRightPadding.pitch = this.bipedRightArmor.pitch = this.speckyRightArm.pitch = this.rightArm.pitch;
        this.stickBlunt.yaw = this.stickSharp.yaw = this.stick.yaw = this.bipedRightPadding.yaw = this.bipedRightArmor.yaw = this.speckyRightArm.yaw = this.rightArm.yaw;
        this.stickBlunt.roll = this.stickSharp.roll = this.stick.roll = this.bipedRightPadding.roll = this.bipedRightArmor.roll = this.speckyRightArm.roll = this.rightArm.roll;
        this.bipedRock.pitch = this.bipedLeftPadding.pitch = this.bipedLeftArmor.pitch = this.speckyLeftArm.pitch = this.leftArm.pitch;
        this.bipedRock.yaw = this.bipedLeftPadding.yaw = this.bipedLeftArmor.yaw = this.speckyLeftArm.yaw = this.leftArm.yaw;
        this.bipedRock.roll = this.bipedLeftPadding.roll = this.bipedLeftArmor.roll = this.speckyLeftArm.roll = this.leftArm.roll;
        this.speckyRightLeg.pitch = this.rightLeg.pitch;
        this.speckyRightLeg.yaw = this.rightLeg.yaw;
        this.speckyRightLeg.roll = this.rightLeg.roll;
        this.speckyLeftLeg.pitch = this.leftLeg.pitch;
        this.speckyLeftLeg.yaw = this.leftLeg.yaw;
        this.speckyLeftLeg.roll = this.leftLeg.roll;
    }
}

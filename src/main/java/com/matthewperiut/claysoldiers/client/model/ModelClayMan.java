package com.matthewperiut.claysoldiers.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.util.maths.MathHelper;

public class ModelClayMan extends Biped {
    public Cuboid bipedRightArmor;

    public Cuboid bipedLeftArmor;

    public Cuboid bipedChest;

    public Cuboid bipedRightPadding;

    public Cuboid bipedLeftPadding;

    public Cuboid bipedPadding;

    public Cuboid gooBase;

    public Cuboid logBase;

    public Cuboid bipedRock;

    public Cuboid speckyHead;

    public Cuboid speckyBody;

    public Cuboid speckyRightArm;

    public Cuboid speckyLeftArm;

    public Cuboid speckyRightLeg;

    public Cuboid speckyLeftLeg;

    public Cuboid stick;

    public Cuboid stickBlunt;

    public Cuboid stickSharp;

    public float armLeft;

    public boolean hasStick;

    public boolean hasArmour;

    public boolean hasCrown;

    public boolean hasGunpowder;

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
        field_619 = new Cuboid(0, 0);
        field_619.method_1818(-1.5F, -3.0F, -1.5F, 3, 3, 3, f);
        field_619.setRotationPoint(0.0F, 0.0F + f1, 0.0F);

        // field_620 is hat
        field_620 = new Cuboid(19, 16);
        field_620.method_1818(-1.5F, -4.0F, -1.5F, 3, 2, 3, f + 0.3F);
        field_620.setRotationPoint(0.0F, 0.0F + f1, 0.0F);

        // field_621 is body
        field_621 = new Cuboid(15, 0);
        field_621.method_1818(-2.0F, 0.0F, -1.0F, 4, 5, 2, f);
        field_621.setRotationPoint(0.0F, 0.0F + f1, 0.0F);

        // field_622 is rightArm
        field_622 = new Cuboid(9, 7);
        field_622.method_1818(-1.0F, -1.0F, -1.0F, 2, 6, 2, f);
        field_622.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);

        // field_623 is leftArm
        field_623 = new Cuboid(9, 7);
        field_623.mirror = true;
        field_623.method_1818(-1.0F, -1.0F, -1.0F, 2, 6, 2, f);
        field_623.setRotationPoint(3.0F, 1.0F + f1, 0.0F);

        // field_624 is rightLeg
        field_624 = new Cuboid(0, 7);
        field_624.method_1818(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
        field_624.setRotationPoint(-1.0F, 5.0F + f1, 0.0F);

        // field_625 is leftLeg
        field_625 = new Cuboid(0, 7);
        field_625.mirror = true;
        field_625.method_1818(-1.0F, 0.0F, -1.0F, 2, 6, 2, f);
        field_625.setRotationPoint(1.0F, 5.0F + f1, 0.0F);

        this.stick = new Cuboid(31, 11);
        this.stick.method_1818(-0.5F, 3.5F, -4.0F, 1, 1, 3, f);
        this.stick.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.stickBlunt = new Cuboid(32, 12);
        this.stickBlunt.method_1818(-0.5F, 3.5F, -6.0F, 1, 1, 2, f);
        this.stickBlunt.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.stickSharp = new Cuboid(9, 0);
        this.stickSharp.method_1818(-0.5F, 3.5F, -5.5F, 1, 1, 2, f - 0.2F);
        this.stickSharp.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.bipedChest = new Cuboid(0, 21);
        this.bipedChest.method_1818(-2.0F, 0.0F, -1.0F, 4, 4, 2, f + 0.3F);
        this.bipedChest.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedRightArmor = new Cuboid(0, 16);
        this.bipedRightArmor.method_1818(-1.0F, -1.0F, -1.0F, 2, 2, 2, f + 0.4F);
        this.bipedRightArmor.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.bipedLeftArmor = new Cuboid(0, 16);
        this.bipedLeftArmor.mirror = true;
        this.bipedLeftArmor.method_1818(-1.0F, -1.0F, -1.0F, 2, 2, 2, f + 0.4F);
        this.bipedLeftArmor.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
        this.bipedPadding = new Cuboid(12, 21);
        this.bipedPadding.method_1818(-2.0F, 2.9F, -1.0F, 4, 2, 2, f + 0.2F);
        this.bipedPadding.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedRightPadding = new Cuboid(9, 16);
        this.bipedRightPadding.method_1818(-1.0F, -0.1F, -1.0F, 2, 2, 2, f + 0.3F);
        this.bipedRightPadding.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.bipedLeftPadding = new Cuboid(9, 16);
        this.bipedLeftPadding.mirror = true;
        this.bipedLeftPadding.method_1818(-1.0F, -0.1F, -1.0F, 2, 2, 2, f + 0.3F);
        this.bipedLeftPadding.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
        this.speckyHead = new Cuboid(37, 17);
        this.speckyHead.method_1818(-1.5F, -3.0F, -1.5F, 3, 3, 3, f + 0.05F);
        this.speckyHead.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.speckyBody = new Cuboid(52, 17);
        this.speckyBody.method_1818(-2.0F, 0.0F, -1.0F, 4, 5, 2, f + 0.05F);
        this.speckyBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.speckyRightArm = new Cuboid(37, 24);
        this.speckyRightArm.method_1818(-1.0F, -1.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyRightArm.setRotationPoint(-3.0F, 1.0F + f1, 0.0F);
        this.speckyLeftArm = new Cuboid(46, 24);
        this.speckyLeftArm.mirror = true;
        this.speckyLeftArm.method_1818(-1.0F, -1.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyLeftArm.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
        this.speckyRightLeg = new Cuboid(46, 24);
        this.speckyRightLeg.method_1818(-1.0F, 0.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyRightLeg.setRotationPoint(-1.0F, 5.0F + f1, 0.0F);
        this.speckyLeftLeg = new Cuboid(37, 24);
        this.speckyLeftLeg.mirror = true;
        this.speckyLeftLeg.method_1818(-1.0F, 0.0F, -1.0F, 2, 6, 2, f + 0.05F);
        this.speckyLeftLeg.setRotationPoint(1.0F, 5.0F + f1, 0.0F);
        this.gooBase = new Cuboid(0, 27);
        this.gooBase.method_1818(-2.5F, 9.0F, -1.5F, 5, 2, 3, f);
        this.gooBase.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.logBase = new Cuboid(16, 26);
        this.logBase.method_1818(-2.5F, -6.5F, -1.5F, 5, 3, 3, f);
        this.logBase.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        this.bipedRock = new Cuboid(31, 3);
        this.bipedRock.mirror = true;
        this.bipedRock.method_1818(-1.0F, 3.5F, -1.0F, 2, 2, 2, f + 0.375F);
        this.bipedRock.setRotationPoint(3.0F, 1.0F + f1, 0.0F);
    }

    @Override
    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5);
        // method_1815 is render
        // head.render
        this.field_619.method_1815(f5);
        // body.render
        this.field_621.method_1815(f5);
        // rightarm.render
        this.field_622.method_1815(f5);
        // leftarm.render
        this.field_623.method_1815(f5);
        // rightleg.render
        this.field_624.method_1815(f5);
        // leftleg.render
        this.field_625.method_1815(f5);
        if (this.hasCrown) {
            // hat.render
            this.field_620.method_1815(f5);
        }
        if (this.hasStick) {
            this.stick.method_1815(f5);
            if (this.isSharpened) {
                this.stickSharp.method_1815(f5);
            } else {
                this.stickBlunt.method_1815(f5);
            }
        }
        if (this.hasArmour) {
            this.bipedChest.method_1815(f5);
            this.bipedRightArmor.method_1815(f5);
            this.bipedLeftArmor.method_1815(f5);
            if (this.isPadded) {
                this.bipedPadding.method_1815(f5);
                this.bipedRightPadding.method_1815(f5);
                this.bipedLeftPadding.method_1815(f5);
            }
        }
        if (this.hasGunpowder) {
            this.speckyHead.method_1815(f5);
            this.speckyBody.method_1815(f5);
            this.speckyRightArm.method_1815(f5);
            this.speckyLeftArm.method_1815(f5);
            this.speckyRightLeg.method_1815(f5);
            this.speckyLeftLeg.method_1815(f5);
        }
        if (this.isGooey)
            this.gooBase.method_1815(f5);
        if (this.hasLogs)
            this.logBase.method_1815(f5);
        if (this.hasRocks)
            this.bipedRock.method_1815(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        this.field_619.yaw = f3 / 57.29578F;
        this.field_619.pitch = f4 / 57.29578F;
        this.field_620.yaw = this.field_619.yaw;
        this.field_620.pitch = this.field_619.pitch;
        this.field_622.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        this.field_623.pitch = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.field_622.roll = 0.0F;
        this.field_623.roll = 0.0F;
        this.field_624.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.field_625.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        this.field_624.yaw = 0.0F;
        this.field_625.yaw = 0.0F;
        Cuboid var10000;
        if (this.isRiding) {
            var10000 = this.field_622;
            var10000.pitch += -0.6283185F;
            var10000 = this.field_623;
            var10000.pitch += -0.6283185F;
            this.field_624.pitch = -1.256637F;
            this.field_625.pitch = -1.256637F;
            this.field_624.yaw = 0.3141593F;
            this.field_625.yaw = -0.3141593F;
        }

        if (this.field_628) {
            this.field_623.pitch = this.field_623.pitch * 0.5F - 0.3141593F;
        }

        if (this.field_629) {
            this.field_622.pitch = this.field_622.pitch * 0.5F - 0.3141593F;
        }

        this.field_622.yaw = 0.0F;
        this.field_623.yaw = 0.0F;
        float f6;
        float f7;
        if (this.handSwingProgress > -9990.0F) {
            f6 = this.handSwingProgress;
            this.field_621.yaw = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593F * 2.0F) * 0.2F;
            var10000 = this.field_622;
            var10000.yaw += this.field_621.yaw;
            var10000 = this.field_623;
            var10000.yaw += this.field_621.yaw;
            var10000 = this.field_623;
            var10000.pitch += this.field_621.yaw;
            f6 = 1.0F - this.handSwingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * 3.141593F);
            float f8 = MathHelper.sin(this.handSwingProgress * 3.141593F) * -(this.field_619.pitch - 0.7F) * 0.75F;
            var10000 = this.field_622;
            var10000.pitch = (float)((double)var10000.pitch - ((double)f7 * 1.2D + (double)f8));
            var10000 = this.field_622;
            var10000.yaw += this.field_621.yaw * 2.0F;
            this.field_622.roll = MathHelper.sin(this.handSwingProgress * 3.141593F) * -0.4F;
        }

        var10000 = this.field_622;
        var10000.roll += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        var10000 = this.field_623;
        var10000.roll -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        var10000 = this.field_622;
        var10000.pitch += MathHelper.sin(f2 * 0.067F) * 0.05F;
        var10000 = this.field_623;
        var10000.pitch -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        if (this.isClimbing) {
            --this.field_622.pitch;
            --this.field_623.pitch;
            var10000 = this.field_624;
            var10000.pitch -= 0.7F;
            var10000 = this.field_625;
            var10000.pitch -= 0.7F;
            var10000 = this.field_620;
            var10000.pitch -= 0.6F;
            var10000 = this.field_620;
            var10000.pitch -= 0.6F;
        } else if (this.hasLogs) {
            this.field_622.pitch = 3.141593F;
            this.field_623.pitch = 3.141593F;
        } else if (this.armLeft > 0.0F) {
            f6 = -4.0F + this.armLeft * 4.0F;
            f7 = 1.0F - this.armLeft;
            this.field_623.pitch = f6;
            this.field_623.roll = f7;
        }

        this.speckyHead.pitch = this.field_620.pitch;
        this.speckyHead.yaw = this.field_620.yaw;
        this.bipedPadding.yaw = this.bipedChest.yaw = this.speckyBody.yaw = this.field_621.yaw;
        this.stickBlunt.pitch = this.stickSharp.pitch = this.stick.pitch = this.bipedRightPadding.pitch = this.bipedRightArmor.pitch = this.speckyRightArm.pitch = this.field_622.pitch;
        this.stickBlunt.yaw = this.stickSharp.yaw = this.stick.yaw = this.bipedRightPadding.yaw = this.bipedRightArmor.yaw = this.speckyRightArm.yaw = this.field_622.yaw;
        this.stickBlunt.roll = this.stickSharp.roll = this.stick.roll = this.bipedRightPadding.roll = this.bipedRightArmor.roll = this.speckyRightArm.roll = this.field_622.roll;
        this.bipedRock.pitch = this.bipedLeftPadding.pitch = this.bipedLeftArmor.pitch = this.speckyLeftArm.pitch = this.field_623.pitch;
        this.bipedRock.yaw = this.bipedLeftPadding.yaw = this.bipedLeftArmor.yaw = this.speckyLeftArm.yaw = this.field_623.yaw;
        this.bipedRock.roll = this.bipedLeftPadding.roll = this.bipedLeftArmor.roll = this.speckyLeftArm.roll = this.field_623.roll;
        this.speckyRightLeg.pitch = this.field_624.pitch;
        this.speckyRightLeg.yaw = this.field_624.yaw;
        this.speckyRightLeg.roll = this.field_624.roll;
        this.speckyLeftLeg.pitch = this.field_625.pitch;
        this.speckyLeftLeg.yaw = this.field_625.yaw;
        this.speckyLeftLeg.roll = this.field_625.roll;
    }
}

package com.matthewperiut.claysoldiers.client.render;

//import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;

import com.matthewperiut.claysoldiers.client.model.ModelClayMan;
import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import net.minecraft.client.render.entity.UndeadEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class RenderClayMan extends UndeadEntityRenderer {
    public ModelClayMan mc1;

    public RenderClayMan(BipedEntityModel model, float f) {
        super(model, f);
        this.mc1 = (ModelClayMan) model;
    }

    protected void applyScale(LivingEntity entityliving, float f) {
        EntityClayMan c1 = (EntityClayMan) entityliving;
        this.mc1.hasStick = c1.hasStick();
        this.mc1.hasSpecks = c1.hasSpecks();
        this.mc1.hasArmor = c1.hasArmor();
        this.mc1.hasCrown = c1.hasCrown();
        this.mc1.isPadded = c1.isPadded();
        this.mc1.isSharpened = c1.isSharpened();
        this.mc1.isGooey = c1.isGooey();
        this.mc1.hasLogs = c1.hasLogs();
        this.mc1.hasRocks = c1.hasRocks();
        this.mc1.armLeft = c1.armLeft();
        boolean flag = false;
        if (c1.isOnLadder()) {
            ++c1.climbTime;
            flag = true;
        }

        this.mc1.isClimbing = flag;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        if (c1.isGlowing()) {
            if (c1.hurtTime <= 0 && c1.deathTime <= 0) {
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            } else {
                GL11.glColor3f(1.0F, 0.5F, 0.5F);
            }
        }

    }

    public void render(LivingEntity entityliving, double d, double d1, double d2, float f, float f1) {
        f1 *= 2.0F;
        super.render(entityliving, d, d1, d2, f, f1);
    }
}

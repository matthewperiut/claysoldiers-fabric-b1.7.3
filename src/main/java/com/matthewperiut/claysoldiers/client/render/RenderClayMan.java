package com.matthewperiut.claysoldiers.client.render;

//import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;

import com.matthewperiut.claysoldiers.client.model.ModelClayMan;
import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.model.Biped;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;

public class RenderClayMan extends BipedEntityRenderer {
    public ModelClayMan mc1;

    public RenderClayMan(Biped model, float f) {
        super(model, f);
        this.mc1 = (ModelClayMan)model;
    }

    // method_823 is preRenderCallback
    @Override
    protected void method_823(Living entityliving, float f)
    {
        EntityClayMan c1 = (EntityClayMan)entityliving;
        this.mc1.hasStick = c1.hasStick();
        this.mc1.hasGunpowder = c1.isGunpowdered();
        this.mc1.hasArmour = c1.hasArmour();
        this.mc1.hasCrown = c1.hasCrown();
        this.mc1.isPadded = c1.isPadded();
        this.mc1.isSharpened = c1.isSharpened();
        this.mc1.isGooey = c1.isGooey();
        this.mc1.hasLogs = c1.hasLogs();
        this.mc1.hasRocks = c1.hasRocks();
        this.mc1.armLeft = c1.armLeft();
        boolean flag = false;
        // method 932 is isOnLadder
        if (c1.method_932()) {
            c1.climbTime++;
            flag = true;
        }
        this.mc1.isClimbing = flag;
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        if (c1.isGlowing())
            if (c1.hurtTime > 0 || c1.deathTime > 0)
            {
                GL11.glColor3f(1.0F, 0.5F, 0.5F);
            }
            else
            {
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }
    }

    @Override
    public void method_822(Living entityliving, double d, double d1, double d2, float f, float f1) {
        f1 *= 2.0F;
        super.method_822(entityliving, d, d1, d2, f, f1);
    }
}

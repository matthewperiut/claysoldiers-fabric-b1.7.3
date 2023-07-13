package com.matthewperiut.claysoldiers.client.render;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelBase;
import net.minecraft.entity.Living;
import org.lwjgl.opengl.GL11;

public class RenderDirtHorse extends LivingEntityRenderer {
    public RenderDirtHorse(EntityModelBase model, float f) {
        super(model, f);
    }

    @Override
    protected void method_823(Living entityliving, float f) {
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }

    @Override
    public void method_822(Living entityliving, double d, double d1, double d2, float f, float f1) {
        f1 *= 2.0F;
        super.method_822(entityliving, d, d1, d2, f, f1);
    }
}

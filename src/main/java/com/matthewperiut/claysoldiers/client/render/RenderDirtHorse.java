package com.matthewperiut.claysoldiers.client.render;

import com.matthewperiut.claysoldiers.client.model.ModelDirtHorse;
import net.minecraft.client.render.entity.UndeadEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

public class RenderDirtHorse extends UndeadEntityRenderer {
    public RenderDirtHorse(ModelDirtHorse model, float f) {
        super(model, f);
    }

    protected void applyScale(LivingEntity entityliving, float f) {
        GL11.glScalef(0.7F, 0.7F, 0.7F);
    }

    public void render(LivingEntity entityliving, double d, double d1, double d2, float f, float f1) {
        f1 *= 2.0F;
        super.render(entityliving, d, d1, d2, f, f1);
    }
}

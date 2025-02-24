package com.matthewperiut.claysoldiers.client.render;

import com.matthewperiut.claysoldiers.entity.behavior.EntityGravelChunk;
import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderGravelChunk extends EntityRenderer {
    private final BlockRenderManager renderBlocks = new BlockRenderManager();
    private final Random random = new Random();

    public RenderGravelChunk() {
        // shadow size
        this.shadowRadius = 0.0F;

        // size
        this.shadowDarkness = 0.75F;
    }

    public void doRenderItem(EntityGravelChunk entitygravelchunk, double d, double d1, double d2, float f, float f1) {
        this.random.setSeed(187L);
        ItemStack itemstack = new ItemStack(Block.GRAVEL.id, 1, 0);
        GL11.glPushMatrix();
        float f2 = MathHelper.sin(((float) entitygravelchunk.entityAge + f1) / 10.0F);
        float f3 = ((float) entitygravelchunk.entityAge + f1) / 20.0F;
        byte byte0 = 1;
        GL11.glTranslatef((float) d, (float) d1 + f2, (float) d2);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        // method_42 is render item in 3d
        if (itemstack.itemId < 256 && BlockRenderManager.isSideLit(Block.BLOCKS[itemstack.itemId].getRenderType())) {
            GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
            this.bindTexture("/terrain.png");
            float f4 = 0.1F;
            GL11.glScalef(f4, f4, f4);

            for (int j = 0; j < byte0; ++j) {
                GL11.glPushMatrix();
                if (j > 0) {
                    float f5 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
                    float f7 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
                    float f9 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F / f4;
                    GL11.glTranslatef(f5, f7, f9);
                }

                // method_48 render block on inventory
                this.renderBlocks.render(Block.BLOCKS[itemstack.itemId], itemstack.getDamage2(), entitygravelchunk.getBrightnessAtEyes(f1));
                GL11.glPopMatrix();
            }
        }

        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }

    private void renderQuad(Tessellator tessellator, int i, int j, int k, int l, int i1) {
        tessellator.startQuads();
        tessellator.color(i1);
        tessellator.vertex(i, j, 0.0D);
        tessellator.vertex(i, j + l, 0.0D);
        tessellator.vertex(i + k, j + l, 0.0D);
        tessellator.vertex(i + k, j, 0.0D);
        tessellator.draw();
    }

    public void renderTexturedQuad(int i, int j, int k, int l, int i1, int j1) {
        float f = 0.0F;
        float f1 = 0.00390625F;
        float f2 = 0.00390625F;
        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.startQuads();
        tessellator.vertex(i, j + j1, f, (float) (k) * f1, (float) (l + j1) * f2);
        tessellator.vertex(i + i1, j + j1, f, (float) (k + i1) * f1, (float) (l + j1) * f2);
        tessellator.vertex(i + i1, j, f, (float) (k + i1) * f1, (float) (l) * f2);
        tessellator.vertex(i, j, f, (float) (k) * f1, (float) (l) * f2);
        tessellator.draw();
    }

    @Override
    public void render(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.doRenderItem((EntityGravelChunk) entity, d, d1, d2, f, f1);
    }
}

package com.matthewperiut.claysoldiers.item;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class ItemClayMan extends TemplateItemBase {
    public int clayTeam;

    public ItemClayMan(Identifier id, int j) {
        super(id);
        this.clayTeam = j;
        this.maxStackSize = 16;
    }

    protected void spawnEntity(World world, double x, double y, double z) {
        EntityClayMan ec = new EntityClayMan(world, x, y, z, this.clayTeam);
        world.playSound(x, y, z, "step.gravel", 0.8F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 0.9F);
        world.spawnEntity(ec);
    }

    public boolean useOnBlock(ItemStack itemstack, PlayerEntity entityplayer, World world, int i, int j, int k, int l) {
        if (world.getBlockId(i, j, k) != Block.SNOW.id) {
            if (l == 0) {
                --j;
            }

            if (l == 1) {
                ++j;
            }

            if (l == 2) {
                --k;
            }

            if (l == 3) {
                ++k;
            }

            if (l == 4) {
                --i;
            }

            if (l == 5) {
                ++i;
            }

            if (!world.isAir(i, j, k)) {
                return false;
            }
        }

        boolean jack = false;
        int p = world.getBlockId(i, j, k);
        if (p == 0 || Block.BY_ID[p].getCollisionShape(world, i, j, k) == null) {
            if (!world.isClient) {
                while (itemstack.count > 0) {
                    double a = (double) i + 0.25D + (double) rand.nextFloat() * 0.5D;
                    double b = (double) j + 0.5D;
                    double c = (double) k + 0.25D + (double) rand.nextFloat() * 0.5D;
                    spawnEntity(world, a, b, c);
                    jack = true;
                    --itemstack.count;
                }
            }
        }

        return jack;
    }
}
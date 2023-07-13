package com.matthewperiut.claysoldiers.item;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import com.matthewperiut.claysoldiers.entity.behavior.EntityDirtHorse;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

import java.util.List;

public class ItemClayDisruptor extends TemplateItemBase {
    public ItemClayDisruptor(Identifier id) {
        super(id);
        this.setDurability(16);
        this.setMaxStackSize(1);
    }

    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }
    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer) {
        if (itemstack.cooldown == 0)
        {
            boolean used = false;
            List list1 = world.getEntities(entityplayer, entityplayer.boundingBox.expand(16.0D, 16.0D, 16.0D));

            int x;
            for(x = 0; x < list1.size(); ++x) {
                EntityBase entity1 = (EntityBase)list1.get(x);
                if (entity1 instanceof EntityClayMan && !entity1.removed && ((Living)entity1).health > 0) {
                    entity1.damage(entityplayer, 100);
                    used = true;
                } else if (entity1 instanceof EntityDirtHorse && !entity1.removed && ((Living)entity1).health > 0) {
                    entity1.damage(entityplayer, 100);
                    used = true;
                }
            }

            x = MathHelper.floor(entityplayer.x);
            int y = MathHelper.floor(entityplayer.boundingBox.minY);
            int z = MathHelper.floor(entityplayer.z);

            int i;
            double a;
            double b;
            double c;
            for(i = -12; i < 13; ++i) {
                for(int j = -12; j < 13; ++j) {
                    for(int k = -12; k < 13; ++k) {
                        if (j + y > 0 && j + y < 127 && world.getTileId(x + i, y + j, z + k) == BlockBase.CLAY.id) {
                            a = (double)i;
                            b = (double)j;
                            c = (double)k;
                            if (Math.sqrt(a * a + b * b + c * c) <= 12.0D) {
                                this.blockCrush(world, x + i, y + j, z + k);
                                used = true;
                            }
                        }
                    }
                }
            }

            if (used)
            {
                itemstack.applyDamage(1, entityplayer);
                itemstack.cooldown = 10;
            }
        }

        return itemstack;
    }

    public void blockCrush(Level worldObj, int x, int y, int z) {
        int a = worldObj.getTileId(x, y, z);
        int b = worldObj.getTileMeta(x, y, z);
        if (a != 0) {
            BlockBase.BY_ID[a].onBlockRemoved(worldObj, x, y, z);
            worldObj.playSound(x,y,z, "step.gravel", 0.8F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.9F);
            BlockBase.BY_ID[a].drop(worldObj, x, y, z, b);
            worldObj.setTile(x, y, z, 0);
        }
    }
}
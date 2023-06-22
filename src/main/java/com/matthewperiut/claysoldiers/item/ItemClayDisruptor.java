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
    static long l = System.currentTimeMillis() / 1000;
    public ItemInstance use(ItemInstance itemstack, Level world, PlayerBase entityplayer) {

        // using time instead of shoving a counter to tick
        long now = System.currentTimeMillis() / 1000;
        if (now - l > 127)
        {
            // prep for multiplayer when stapi updates ONE DAY
            for(Object p : world.players)
            {
                ((PlayerBase) p).unusedByte = 0;
            }
            l = now;
        }

        // wait 2 seconds
        if (entityplayer.unusedByte + 1 < now - l)
        {
            entityplayer.unusedByte = (byte) (now - l);
            itemstack.applyDamage(1, entityplayer);
            List list1 = world.getEntities(entityplayer, entityplayer.boundingBox.expand(16.0D, 16.0D, 16.0D));

            int x;
            for(x = 0; x < list1.size(); ++x) {
                EntityBase entity1 = (EntityBase)list1.get(x);
                if (entity1 instanceof EntityClayMan && !entity1.removed && ((Living)entity1).health > 0) {
                    entity1.damage(entityplayer, 100);
                } else if (entity1 instanceof EntityDirtHorse && !entity1.removed && ((Living)entity1).health > 0) {
                    entity1.damage(entityplayer, 100);
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
                            }
                        }
                    }
                }
            }

            for(i = 0; i < 128; ++i) {
                double angle = (double)i / 3.0D;
                a = 0.5D + (double)i / 6.0D;
                b = Math.sin(angle) * 0.25D;
                c = Math.cos(angle) * 0.25D;
                double d = entityplayer.x + b * a;
                double e = entityplayer.boundingBox.minY + 0.5D;
                double f = entityplayer.z + c * a;

                world.addParticle("portal", d, e, f, e, 0.0D, f);
            }
        }

        return itemstack;
    }

    public void blockCrush(Level worldObj, int x, int y, int z) {
        int a = worldObj.getTileId(x, y, z);
        int b = worldObj.getTileMeta(x, y, z);
        if (a != 0) {
            //ModLoader.getMinecraftInstance().effectRenderer.addBlockDestroyEffects(x, y, z, a, b);
            BlockBase.BY_ID[a].onBlockRemoved(worldObj, x, y, z);
            BlockBase.BY_ID[a].drop(worldObj, x, y, z, b);
            worldObj.setTileInChunk(x, y, z, 0);
        }
    }
}
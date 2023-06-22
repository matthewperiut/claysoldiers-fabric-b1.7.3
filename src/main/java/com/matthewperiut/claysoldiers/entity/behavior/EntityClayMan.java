package com.matthewperiut.claysoldiers.entity.behavior;

import com.matthewperiut.claysoldiers.ClaySoldiersMod;
import com.matthewperiut.claysoldiers.item.ItemClayMan;
import com.matthewperiut.claysoldiers.item.ItemListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.class_61;
import net.minecraft.entity.*;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.food.FoodBase;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;

import java.util.List;

import static net.modificationstation.stationapi.api.registry.Identifier.of;

@HasTrackingParameters(trackingDistance = 5, updatePeriod = 2)
public class EntityClayMan extends AnimalBase {
    public int clayTeam;
    public int weaponPoints;
    public int armorPoints;
    public int foodLeft;
    public int sugarTime;
    public int resPoints;
    public int entCount;
    public int strikeTime;
    public int climbTime;
    public int gooTime;
    public int smokeTime;
    public int gooStock;
    public int smokeStock;
    public int logs;
    public int rocks;
    public int blockX;
    public int blockY;
    public int blockZ;
    public int throwTime;
    public float swingLeft;
    public boolean gunpowdered;
    public boolean king;
    public boolean glowing;
    public boolean isSwinging;
    public boolean stickSharp;
    public boolean armorPadded;
    public boolean heavyCore;
    public boolean isSwingingLeft;
    public EntityBase targetFollow;
    public EntityBase killedByPlayer;

    public EntityClayMan(Level world) {
        super(world);
        this.health = 20;
        this.clayTeam = 0;
        //mcp yOffset
        this.standingEyeHeight = 0.0F;
        //mcp stepHeight
        this.field_1641 = 0.1F;
        //mcp moveSpeed
        this.movementSpeed = 0.3F;
        this.setSize(0.15F, 0.4F);
        //mcp posX posY posZ
        this.setPosition(this.x, this.y, this.z);
        this.texture = this.clayManTexture(0);
        //mcp renderDistanceWeight
        this.renderDistanceMultiplier = 5.0D;
    }

    public EntityClayMan(Level world, double x, double y, double z, int i) {
        super(world);
        this.health = 20;
        this.clayTeam = i;
        //mcp yOffset
        this.standingEyeHeight = 0.0F;
        //mcp stepHeight
        this.field_1641 = 0.1F;
        //mcp moveSpeed
        this.movementSpeed = 0.3F;
        this.setSize(0.15F, 0.4F);
        this.setPosition(x, y, z);
        this.texture = this.clayManTexture(i);
        //mcp renderDistanceWeight
        this.renderDistanceMultiplier = 5.0D;
        //this.level.playSound(this, "step.gravel", 0.8F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.9F);
        this.level.playSound(this, "step.gravel", 0.8F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.9F);
    }

    public String clayManTexture(int i) {
        String joe = "claysoldiers:textures/entities/clay";
        if (i == 0) {
            joe = joe + "Grey";
        } else if (i == 1) {
            joe = joe + "Red";
        } else if (i == 2) {
            joe = joe + "Yellow";
        } else if (i == 3) {
            joe = joe + "Green";
        } else if (i == 4) {
            joe = joe + "Blue";
        } else if (i == 5) {
            joe = joe + "Orange";
        } else {
            joe = joe + "Purple";
        }

        return joe + ".png";
    }

    public int teamCloth(int teamNum) {
        if (teamNum == 0) {
            return 8;
        } else if (teamNum == 1) {
            return 14;
        } else if (teamNum == 2) {
            return 4;
        } else if (teamNum == 3) {
            return 13;
        } else if (teamNum == 4) {
            return 11;
        } else {
            return teamNum == 5 ? 1 : 10;
        }
    }

    public int teamDye(int teamNum) {
        if (teamNum == 0) {
            return 8;
        } else if (teamNum == 1) {
            return 1;
        } else if (teamNum == 2) {
            return 11;
        } else if (teamNum == 3) {
            return 2;
        } else if (teamNum == 4) {
            return 4;
        } else {
            return teamNum == 5 ? 14 : 5;
        }
    }

    @Override
    public void tickHandSwing() {
        //mcp updatePlayerActionState

        super.tickHandSwing();
        if (this.strikeTime > 0) {
            --this.strikeTime;
        }

        // NOTE: this.entity = (mcp) playerToAttack
        if (this.sugarTime > 0) {
            this.movementSpeed = 0.6F + (this.entity == null && this.targetFollow == null ? 0.0F : 0.15F);
            --this.sugarTime;
        } else {
            this.movementSpeed = 0.3F + (this.entity == null && this.targetFollow == null ? 0.0F : 0.15F);
        }

        // mcp handleWaterMovement
        if (this.isTouchingWater()) {
            // mcp isJumping
            this.jumping = true;
        }

        int i;
        double frogman;
        double aaa;
        if (this.foodLeft > 0 && this.health <= 15 && this.health > 0) {
            for(i = 0; i < 12; ++i) {
                frogman = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                double b = this.y + 0.25D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                aaa = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                // todo: implement effect renderer when stapi gets that? maybe it already exists?
                //ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntitySlimeFX(this.worldObj, frogman, b, a, Item.porkRaw));
            }

            this.health += 15;
            --this.foodLeft;
        }

        if (this.onGround) {
            this.climbTime = 10;
        }

        if (this.smokeTime > 0) {
            --this.smokeTime;
        }

        if (this.throwTime > 0) {
            --this.throwTime;
        }

        if (this.gooTime > 0) {
            int j;
            //mcp -> bin : motion -> velocity
            this.velocityX = 0.0D;
            this.velocityY = 0.0D;
            this.velocityZ = 0.0D;
            // mcp "moveForward"
            this.field_1029 = 0.0F;
            // mcp "moveStrafing"
            this.field_1060 = 0.0F;
            // mcp isJumping
            this.jumping = false;
            this.movementSpeed = 0.0F;
            --this.gooTime;
            i = MathHelper.floor(this.x);
            j = MathHelper.floor(this.boundingBox.minY - 1.0D);
            int k = MathHelper.floor(this.z);

            j = this.level.getTileId(i, j, k);
            if (j > 0 && j < 128 && (j == 0 || BlockBase.BY_ID[j].getCollisionShape(this.level, i, j, k) == null)) {
                this.gooTime = 0;
            }
        }

        if (this.throwTime > 6)
        {
            this.movementSpeed = -this.movementSpeed;
        }

        ++this.entCount;
        // mcp this.playerToAttack -> this.entity
        // mcp isDead -> removed
        if (this.entity instanceof EntityClayMan)
        {
            EntityClayMan e = (EntityClayMan) this.entity;
            if (this.clayTeam == e.clayTeam)
            {
                this.entity = null;

                this.setTarget((class_61)null);
            }
        }

        if (this.entity != null && this.entity.removed) {
            this.entity = null;
            // mcp setPathToEntity -> setTarget
            // mcp PathEntity -> class_61
            this.setTarget((class_61)null);
            //getDistanceToEntity -> distanceTo
            //canEntityBeSeen -> method_928
        } else if (this.entity != null && this.rand.nextInt(25) == 0 && ((double)this.distanceTo(this.entity) > 8.0D || !this.method_928(this.entity))) {
            this.entity = null;
            this.setTarget((class_61)null);
        }

        if (this.targetFollow != null && this.targetFollow.removed) {
            this.targetFollow = null;
            this.setTarget((class_61)null);
        } else if (this.targetFollow != null && this.rand.nextInt(25) == 0 && ((double)this.distanceTo(this.targetFollow) > 8.0D || !this.method_928(this.targetFollow))) {
            this.targetFollow = null;
            this.setTarget((class_61)null);
        }

        if (this.smokeTime <= 0 && this.entCount > 2 + this.rand.nextInt(2) && this.health > 0) {
            this.entCount = 0;

            //List list = this.level.getEntities(this, this.boundingBox.expand(8.0D, 5.0D, 8.0D));
            List list = this.level.getEntities(this, this.boundingBox.expand(8.0D, 5.0D, 8.0D));

            EntityClayMan ec;
            for(int j = 0; j < list.size(); ++j)
            {
                EntityBase entity = (EntityBase)list.get(j);
                // method_928 is something like this entity can see them
                if (entity instanceof EntityClayMan && this.rand.nextInt(3) == 0 && this.method_928(entity))
                {
                    EntityClayMan clayman = (EntityClayMan)entity;
                    if (clayman.health > 0 && clayman.clayTeam != this.clayTeam && this.clayTeam > 0 && this.logs <= 0) {
                        if (clayman.king) {
                            if (this.entity == null || !(this.entity instanceof EntityClayMan)) {
                                this.entity = clayman;
                                break;
                            }

                            ec = (EntityClayMan)this.entity;
                    if (!ec.king) {
                        this.entity = clayman;
                        break;
                    }
                } else if (this.entity == null) {
                    this.entity = clayman;
                    break;
                }
            }
        } else if (this.entity == null && this.targetFollow == null && this.vehicle == null && entity instanceof EntityDirtHorse && entity.passenger == null && this.method_928(entity)) {
            this.targetFollow = entity;
            break;
        } else if (this.entity == null && entity instanceof WalkingBase && this.method_928(entity)) {
            WalkingBase mob = (WalkingBase)entity;
            if (mob.method_634() != null)
            {
                this.entity = mob;
                break;
            }
        } else {
            if (this.entity == null && this.targetFollow == null && entity instanceof FishHook && this.method_928(entity)) {
                this.targetFollow = entity;
                break;
            }

            if ((this.targetFollow == null || this.targetFollow instanceof EntityClayMan) && entity instanceof Item && this.method_928(entity)) {
                        Item item = (Item)entity;
                        if (item.item != null) {
                            ItemInstance stack = item.item;
                            if (stack.count > 0) {
                                if (this.weaponPoints <= 0 && stack.itemId == ItemBase.stick.id) {
                                    this.targetFollow = item;
                                    break;
                                }

                                if (this.armorPoints <= 0 && stack.itemId == ItemBase.leather.id) {
                                    this.targetFollow = item;
                                    break;
                                }

                                if (this.rocks <= 0 && stack.itemId == BlockBase.GRAVEL.id) {
                                    this.targetFollow = item;
                                    break;
                                }

                                if (!this.glowing && stack.itemId == ItemBase.glowstoneDust.id) {
                                    this.targetFollow = item;
                                    break;
                                }

                                if (!this.king && stack.itemId == ItemBase.goldIngot.id) {
                                    boolean jack = false;
                                    List list2 = this.level.getEntities(this, this.boundingBox.expand(24.0D, 16.0D, 24.0D));

                                    for(int k = 0; k < list2.size(); ++k) {
                                        EntityBase entity2 = (EntityBase) list2.get(k);
                                        if (entity2 instanceof EntityClayMan) {
                                            EntityClayMan clayman = (EntityClayMan)entity2;
                                            if (clayman.clayTeam == this.clayTeam && clayman.king) {
                                                jack = true;
                                                break;
                                            }
                                        }
                                    }

                                    if (!jack) {
                                        this.targetFollow = item;
                                        break;
                                    }
                                } else {
                                    if (!this.gunpowdered && stack.itemId == ItemBase.gunpowder.id) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (this.sugarTime <= 0 && stack.itemId == ItemBase.sugar.id) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (this.foodLeft <= 0 && stack.getType() != null && stack.getType() instanceof FoodBase) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (this.resPoints <= 0 && stack.itemId == ItemBase.clay.id) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (this.gooStock <= 0 && stack.itemId == ItemBase.slimeball.id) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (this.smokeStock <= 0 && stack.itemId == ItemBase.redstoneDust.id) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (this.weaponPoints > 0 && !this.stickSharp && stack.itemId == ItemBase.flint.id) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (this.armorPoints > 0 && !this.armorPadded && stack.itemId == BlockBase.WOOL.id) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (!this.heavyCore && this.vehicle == null && stack.itemId == ItemBase.ironIngot.id) {
                                        this.targetFollow = item;
                                        break;
                                    }

                                    if (this.resPoints > 0 && stack.getType() != null && stack.getType() instanceof ItemClayMan) {
                                        ItemClayMan ic = (ItemClayMan)stack.getType();
                                        if (ic.clayTeam == this.clayTeam) {
                                            this.targetFollow = item;
                                            break;
                                        }
                                    } else {
                                        if (stack.itemId == ItemBase.dyePowder.id && stack.getDamage() == this.teamDye(this.clayTeam)) {
                                            this.targetFollow = item;
                                            break;
                                        }

                                        if (stack.itemId == BlockBase.WOOD.id && this.vehicle == null) {
                                            int gottam = 0;
                                            if (this.logs < 20 && stack.count >= 5) {
                                                gottam = 1;
                                            }

                                            if (gottam > 0) {
                                                this.targetFollow = item;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (this.entity != null) {
                if (this.strikeTime <= 0 && this.method_928(this.entity) && (double)this.distanceTo(this.entity) < (this.weaponPoints > 0 ? 1.0D : 0.7D) + (double)this.rand.nextFloat() * 0.1D) {
                    if (this.hitTargetMakesDead(this.entity)) {
                        this.entity = null;
                        this.setTarget((class_61)null);
                    }
                } else if (this.rocks > 0 && this.throwTime <= 0 && this.method_928(this.entity)) {
                    frogman = (double)this.distanceTo(this.entity);
                    if (frogman >= 1.75D && frogman <= 7.0D) {
                        --this.rocks;
                        this.throwTime = 20;
                        this.throwRockAtEnemy(this.entity);
                    }
                }
            } else if (this.targetFollow != null) {
                // mcp hasPath is method_633
                if (!this.method_633() || this.rand.nextInt(10) == 0) {
                    // mcp getPathToEntity -> method_192
                    this.setTarget(this.level.method_192(this.targetFollow, this, 16.0F));
                }

                if (this.targetFollow instanceof Item) {
                    Item item = (Item)this.targetFollow;
                    if (item.item != null && this.method_928(item) && (double)this.distanceTo(item) < 0.75D) {
                        ItemInstance stack = item.item;
                        if (stack.count > 0) {
                            if (stack.itemId == ItemBase.stick.id) {
                                this.weaponPoints = 15;
                                this.stickSharp = false;
                                this.gotcha((Item)this.targetFollow);
                            } else if (stack.itemId == ItemBase.leather.id) {
                                this.armorPoints = 15;
                                this.armorPadded = false;
                                this.gotcha((Item) this.targetFollow);
                            } else if (stack.itemId == BlockBase.GRAVEL.id) {
                                this.rocks = 15;
                                this.gotcha((Item) this.targetFollow);
                            } else if (stack.itemId == ItemBase.glowstoneDust.id) {
                                this.glowing = true;
                                this.gotcha((Item) this.targetFollow);
                            } else if (stack.itemId == ItemBase.goldIngot.id) {
                                boolean jack = false;
                                List list2 = this.level.getEntities(this, this.boundingBox.expand(24.0D, 16.0D, 24.0D));

                                for(int k = 0; k < list2.size(); ++k) {
                                    EntityBase entity2 = (EntityBase)list2.get(k);
                                    if (entity2 instanceof EntityClayMan) {
                                        EntityClayMan clayman = (EntityClayMan)entity2;
                                        if (clayman.clayTeam == this.clayTeam && clayman.king) {
                                            jack = true;
                                            break;
                                        }
                                    }
                                }

                                if (!jack) {
                                    this.king = true;
                                    this.gotcha((Item) this.targetFollow);
                                    item.remove();
                                } else {
                                    this.targetFollow = null;
                                    this.setTarget((class_61)null);
                                }
                            } else if (stack.itemId == ItemBase.gunpowder.id) {
                                this.gunpowdered = true;
                                this.gotcha((Item) this.targetFollow);
                            } else if (stack.itemId == ItemBase.sugar.id) {
                                this.sugarTime = 1200;
                                this.gotcha((Item)this.targetFollow);
                            } else if (stack.getType() != null && stack.getType() instanceof FoodBase) {
                                this.foodLeft = 4;
                                this.gotcha((Item)this.targetFollow);
                            } else if (stack.itemId == ItemBase.clay.id) {
                                this.resPoints = 4;
                                this.gotcha((Item)this.targetFollow);
                            } else if (stack.itemId == ItemBase.redstoneDust.id) {
                                this.smokeStock = 2;
                                this.gotcha((Item)this.targetFollow);
                            } else if (stack.itemId == ItemBase.slimeball.id) {
                                this.gooStock = 2;
                                this.gotcha((Item)this.targetFollow);
                            } else if (stack.itemId == ItemBase.ironIngot.id) {
                                this.heavyCore = true;
                                this.gotcha((Item)this.targetFollow);
                            } else {
                                double a;
                                double b;
                                double c;
                                if (stack.itemId == ItemBase.flint.id) {
                                    if (this.weaponPoints > 0) {
                                        this.stickSharp = true;

                                        for(int j = 0; j < 4; ++j) {
                                            a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                            b = this.boundingBox.minY + 0.125D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.25D;
                                            c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                            //ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityDiggingFX(this.worldObj, a, b, c, 0.0D, 0.0D, 0.0D, Block.planks, 0, 0));
                                        }

                                        this.level.playSound(this, "random.wood click", 0.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
                                    }

                                    this.targetFollow = null;
                                } else if (stack.itemId == BlockBase.WOOL.id) {
                                    if (this.armorPoints > 0) {
                                        this.armorPadded = true;

                                        for(int j = 0; j < 4; ++j) {
                                            a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                            b = this.boundingBox.minY + 0.125D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.25D;
                                            c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                            //ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityDiggingFX(this.worldObj, a, b, c, 0.0D, 0.0D, 0.0D, Block.cloth, 0, 0));
                                        }

                                        this.level.playSound(this, "step.cloth", 0.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
                                    }

                                    this.targetFollow = null;
                                } else if (stack.getType() != null && stack.getType() instanceof ItemClayMan) {
                                    this.swingArm();
                                    this.level.playSound(item, "step.gravel", 0.8F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.9F);
                                    ItemBase item1 = ItemListener.greyDoll.asItem();
                                    if (this.clayTeam == 1) {
                                        item1 = ItemListener.redDoll.asItem();
                                    } else if (this.clayTeam == 2) {
                                        item1 = ItemListener.yellowDoll.asItem();
                                    } else if (this.clayTeam == 3) {
                                        item1 = ItemListener.greenDoll.asItem();
                                    } else if (this.clayTeam == 4) {
                                        item1 = ItemListener.blueDoll.asItem();
                                    } else if (this.clayTeam == 5) {
                                        item1 = ItemListener.orangeDoll.asItem();
                                    } else if (this.clayTeam == 6) {
                                        item1 = ItemListener.purpleDoll.asItem();
                                    }

                                    for(int q = 0; q < 18; ++q) {
                                        //a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        //double b = this.y + 0.25D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        //double c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        //ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntitySlimeFX(this.worldObj, a, b, c, item1));
                                    }

                                    ec = new EntityClayMan(level, item.x, item.y, item.z, this.clayTeam);
                                    this.level.spawnEntity(ec);
                                    this.gotcha((Item) this.targetFollow);
                                    --this.resPoints;
                                } else if (stack.itemId == BlockBase.LOG.id && this.vehicle == null) {
                                    int gottam = 0;
                                    if (this.logs < 20 && stack.count >= 5) {
                                        gottam = 1;
                                    }

                                    if (gottam > 0) {
                                        this.level.playSound(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                                        if (gottam == 1) {
                                            this.logs += 5;
                                            if (item.item != null) {
                                                ItemInstance var10000 = item.item;
                                                var10000.count -= 5;
                                            }
                                        }

                                        if (item.item == null || item.item.count <= 0) {
                                            item.remove();
                                        }
                                    }

                                    this.setTarget((class_61)null);
                                    this.targetFollow = null;
                                } else if (stack.itemId == ItemBase.dyePowder.id)
                                {
                                    this.targetFollow = null;
                                }
                            }
                        }
                    }
                } else if (this.targetFollow instanceof EntityClayMan && (double)this.distanceTo(this.targetFollow) < 1.75D) {
                    this.targetFollow = null;
                } else if (this.targetFollow instanceof FishHook && (double)this.distanceTo(this.targetFollow) < 1.0D) {
                    this.targetFollow = null;
                } else if (this.targetFollow instanceof EntityDirtHorse && (double)this.distanceTo(this.targetFollow) < 0.75D && this.gooTime <= 0) {
                    if (this.vehicle == null && this.targetFollow.passenger == null && !this.heavyCore && this.logs <= 0) {
                        this.startRiding(this.targetFollow);
                        this.level.playSound(this, "step.gravel", 0.6F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
                    }

                    this.targetFollow = null;
                }
            } else {
                this.updateBlockFinder();
                if (this.logs > 0 && this.rand.nextInt(16) == 0) {
                    this.updateBuildings();
                }
            }
        }

        if (this.isSwinging) {
            this.lastHandSwingProgress += 0.15F;
            this.handSwingProgress += 0.15F;
            if (this.lastHandSwingProgress > 1.0F || this.handSwingProgress > 1.0F) {
                this.isSwinging = false;
                this.lastHandSwingProgress = 0.0F;
                this.handSwingProgress = 0.0F;
            }
        }

        if (this.isSwingingLeft) {
            this.swingLeft += 0.15F;
            if (this.swingLeft > 1.0F) {
                this.isSwingingLeft = false;
                this.swingLeft = 0.0F;
            }
        }

    }

    public void updateBlockFinder() {
        int x = MathHelper.floor(this.x);
        int y = MathHelper.floor(this.boundingBox.minY);
        int z = MathHelper.floor(this.z);
        if (this.blockX != 0 && this.blockY != 0 && this.blockZ != 0 && !this.method_633()) {
            class_61 emily = this.level.method_189(this, this.blockX, this.blockY, this.blockZ, 16.0F);
            if (emily != null && this.rand.nextInt(5) != 0) {
                this.setTarget(emily);
            } else {
                this.blockX = 0;
                this.blockY = 0;
                this.blockZ = 0;
            }
        }

        int i = x;
        int j = y;
        int k = z;

        for(int q = 0; q < 16; ++q) {
            if (j >= 4 && j <= 124 && this.isAirySpace(i, j, k) && !this.isAirySpace(i, j - 1, k)) {
                int b = j - 1;
                if (this.checkSides(i, b, k, i, j, k, this.blocDist(i, b, k, x, y, z), q == 0)) {
                    break;
                }

                ++b;
                int a = i - 1;
                if (this.checkSides(a, b, k, i, j, k, this.blocDist(a, b, k, x, y, z), q == 0)) {
                    break;
                }

                a += 2;
                if (this.checkSides(a, b, k, i, j, k, this.blocDist(a, b, k, x, y, z), q == 0)) {
                    break;
                }

                --a;
                int c = k - 1;
                if (this.checkSides(a, b, c, i, j, k, this.blocDist(a, b, c, x, y, z), q == 0)) {
                    break;
                }

                c += 2;
                if (this.checkSides(a, b, c, i, j, k, this.blocDist(a, b, c, x, y, z), q == 0)) {
                    break;
                }

                i = x + this.rand.nextInt(6) - this.rand.nextInt(6);
                j = y + this.rand.nextInt(3) - this.rand.nextInt(3);
                k = z + this.rand.nextInt(6) - this.rand.nextInt(6);
            }
        }

    }

    public double blocDist(int a, int b, int c, int x, int y, int z) {
        double i = (double)(a - x);
        double j = (double)(b - y);
        double k = (double)(c - z);
        return Math.sqrt(i * i + j * j + k * k);
    }

    // todo: make this more stapi friendly? I feel like this might have issues.
    public boolean isAirySpace(int x, int y, int z) {
        int p = this.level.getTileId(x, y, z);
        return p == 0 || BlockBase.BY_ID[p].getCollisionShape(level, x, y, z) == null;
    }

    public boolean checkSides(int a, int b, int c, int i, int j, int k, double dist, boolean first) {
        if (b > 4 && b < 124 && this.level.getTileId(a, b, c) == BlockBase.CHEST.id) {
            if (first && this.blockX == i && this.blockY == j && this.blockZ == k) {
                this.setTarget((class_61)null);
                this.blockX = 0;
                this.blockY = 0;
                this.blockZ = 0;
                this.chestOperations(a, b, c, true);
                return true;
            }

            if (this.blockX == 0 && this.blockY == 0 && this.blockZ == 0 && this.chestOperations(a, b, c, false)) {
                class_61 emily = this.level.method_189(this, i, j, k, 16.0F);
                if (emily != null) {
                    this.setTarget(emily);
                    this.blockX = i;
                    this.blockY = j;
                    this.blockZ = k;
                    return true;
                }
            }
        }

        return false;
    }

    public boolean chestOperations(int x, int y, int z, boolean arrived) {
        TileEntityBase te = this.level.getTileEntity(x, y, z);
        if (te != null && te instanceof TileEntityChest) {
            TileEntityChest chest = (TileEntityChest)te;

            for(int q = 0; q < chest.getInventorySize(); ++q) {
                if (chest.getInventoryItem(q) != null) {
                    ItemInstance stack = chest.getInventoryItem(q);
                    if (stack.count > 0) {
                        if (this.weaponPoints <= 0 && stack.itemId == ItemBase.stick.id) {
                            if (arrived) {
                                this.weaponPoints = 15;
                                this.stickSharp = false;
                                this.gotcha(chest, q);
                            }

                            return true;
                        }

                        if (this.armorPoints <= 0 && stack.itemId == ItemBase.leather.id) {
                            if (arrived) {
                                this.armorPoints = 15;
                                this.armorPadded = false;
                                this.gotcha(chest, q);
                            }

                            return true;
                        }

                        if (this.rocks <= 0 && stack.itemId == BlockBase.GRAVEL.id) {
                            if (arrived) {
                                this.rocks = 15;
                                this.gotcha(chest, q);
                            }

                            return true;
                        }

                        if (!this.glowing && stack.itemId == ItemBase.glowstoneDust.id) {
                            if (arrived) {
                                this.glowing = true;
                                this.gotcha(chest, q);
                            }

                            return true;
                        }

                        int u;
                        if (!this.king && stack.itemId == ItemBase.goldIngot.id) {
                            boolean jack = false;
                            List list2 = this.level.getEntities(this, this.boundingBox.expand(24.0D, 16.0D, 24.0D));

                            for(u = 0; u < list2.size(); ++u) {
                                EntityBase entity2 = (EntityBase)list2.get(u);
                                if (entity2 instanceof EntityClayMan) {
                                    EntityClayMan clayman = (EntityClayMan)entity2;
                                    if (clayman.clayTeam == this.clayTeam && clayman.king) {
                                        jack = true;
                                        break;
                                    }
                                }
                            }

                            if (!jack) {
                                if (arrived) {
                                    this.king = true;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }
                        } else {
                            if (!this.gunpowdered && stack.itemId == ItemBase.gunpowder.id) {
                                if (arrived) {
                                    this.gunpowdered = true;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if (this.sugarTime <= 0 && stack.itemId == ItemBase.sugar.id) {
                                if (arrived) {
                                    this.sugarTime = 1200;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if (this.foodLeft <= 0 && stack.getType() != null && stack.getType() instanceof FoodBase) {
                                if (arrived) {
                                    this.foodLeft = 4;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if (this.resPoints <= 0 && stack.itemId == ItemBase.clay.id) {
                                if (arrived) {
                                    this.resPoints = 4;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if (this.gooStock <= 0 && stack.itemId == ItemBase.slimeball.id) {
                                if (arrived) {
                                    this.gooStock = 2;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if (this.smokeStock <= 0 && stack.itemId == ItemBase.redstoneDust.id) {
                                if (arrived) {
                                    this.smokeStock = 2;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if (stack.itemId == BlockBase.LOG.id && this.vehicle == null) {
                                int gottam = 0;
                                if (this.logs < 20 && stack.count >= 5) {
                                    gottam = 1;
                                }

                                if (arrived && gottam > 0) {
                                    this.level.playSound(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                                    if (gottam == 1) {
                                        this.logs += 5;
                                        chest.takeInventoryItem(q, 5);
                                    }
                                }

                                return gottam > 0 || arrived;
                            }

                            double a;
                            double b;
                            int j;
                            if (this.weaponPoints > 0 && !this.stickSharp && stack.itemId == ItemBase.flint.id) {
                                if (arrived) {
                                    this.stickSharp = true;

                                    for(j = 0; j < 4; ++j) {
                                        a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        a = this.boundingBox.minY + 0.125D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.25D;
                                        b = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        //todo: ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityDiggingFX(this.worldObj, a, a, b, 0.0D, 0.0D, 0.0D, Block.planks, 0, 0));
                                    }

                                    this.level.playSound(this, "random.wood click", 0.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
                                }

                                return true;
                            }

                            if (this.armorPoints > 0 && !this.armorPadded && stack.itemId == BlockBase.WOOL.id) {
                                if (arrived) {
                                    this.armorPadded = true;

                                    for(j = 0; j < 4; ++j) {
                                        a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        a = this.boundingBox.minY + 0.125D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.25D;
                                        b = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        //todo: ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityDiggingFX(this.worldObj, a, a, b, 0.0D, 0.0D, 0.0D, Block.cloth, 0, 0));
                                    }

                                    this.level.playSound(this, "step.cloth", 0.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
                                }

                                return true;
                            }

                            if (!this.heavyCore && this.vehicle == null && stack.itemId == ItemBase.ironIngot.id) {
                                if (arrived) {
                                    this.heavyCore = true;
                                    this.gotcha(chest, q);
                                }

                                return true;
                            }

                            if (this.resPoints > 0 && stack.getType() != null && stack.getType() instanceof ItemClayMan) {
                                ItemClayMan ic = (ItemClayMan)stack.getType();
                                if (ic.clayTeam == this.clayTeam) {
                                    if (arrived) {
                                        this.swingArm();
                                        ItemBase item1 = ItemListener.greyDoll.asItem();
                                        if (this.clayTeam == 1) {
                                            item1 = ItemListener.redDoll.asItem();
                                        } else if (this.clayTeam == 2) {
                                            item1 = ItemListener.yellowDoll.asItem();
                                        } else if (this.clayTeam == 3) {
                                            item1 = ItemListener.greenDoll.asItem();
                                        } else if (this.clayTeam == 4) {
                                            item1 = ItemListener.blueDoll.asItem();
                                        } else if (this.clayTeam == 5) {
                                            item1 = ItemListener.orangeDoll.asItem();
                                        } else if (this.clayTeam == 6) {
                                            item1 = ItemListener.purpleDoll.asItem();
                                        }

                                        for(u = 0; u < 18; ++u) {
                                            a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                            b = this.y + 0.25D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                            double c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                            //todo: ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntitySlimeFX(this.worldObj, a, b, c, item1));
                                        }

                                        double a1 = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        double b1 = this.y + (double)this.rand.nextFloat() * 0.125D;
                                        double c1 = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                                        EntityClayMan ec = new EntityClayMan(this.level, a1, b1, c1, this.clayTeam);
                                        this.level.spawnEntity(ec);
                                        this.gotcha(chest, q);
                                        --this.resPoints;
                                    }

                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public void updateBuildings() {
        int x = MathHelper.floor(this.x);
        int y = MathHelper.floor(this.boundingBox.minY);
        int z = MathHelper.floor(this.z);
        if (y >= 4 && y <= 120) {
            int broad = 2;
            int high = 3;
            if (this.logs == 20) {
                broad = 3;
                high = 4;
            }

            boolean flag = false;

            for(int a = -broad; a < broad + 1 && !flag; ++a) {
                for(int b = -1; b < high + 1 && !flag; ++b) {
                    for(int c = -broad; c < broad + 1 && !flag; ++c) {
                        if (b == -1) {
                            if (this.isAirySpace(x + a, y + b, z + c)) {
                                flag = true;
                            }
                        } else if (!this.isAirySpace(x + a, y + b, z + c) || this.level.getMaterial(x + a, y + b, z + c) == Material.WATER) {
                            flag = true;
                        }
                    }
                }
            }

            if (!flag) {
                double gee = (double)broad;
                List list = this.level.getEntities(this, this.boundingBox.expand(gee, gee, gee));
                if (list.size() > 0) {
                    flag = true;
                }
            }

            if (!flag) {
                if (this.logs == 20 && this.rand.nextInt(2) == 0) {
                    this.buildHouseThree();
                } else if (this.logs >= 10 && this.rand.nextInt(3) > 0) {
                    this.buildHouseTwo();
                } else if (this.logs >= 5) {
                    this.buildHouseOne();
                }
            }

        }
    }

    public void dropLogs() {
        this.dropItem(BlockBase.LOG.id, this.logs);
        this.logs = 0;
    }

    public void buildHouseOne() {
        int x = MathHelper.floor(this.x + 0.5D);
        int y = MathHelper.floor(this.boundingBox.minY);
        int z = MathHelper.floor(this.z + 0.5D);
        int direction = this.rand.nextInt(4);

        for(int j = 0; j < 3; ++j) {
            int b = j;

            for(int i = -1; i < 3; ++i) {
                for(int k = -1; k < 2; ++k) {
                    int a = i;
                    int c = k;
                    if (direction % 2 == 0) {
                        a = -i;
                        c = -k;
                    }

                    if (direction / 2 == 0) {
                        int swap = a;
                        a = c;
                        c = swap;
                    }

                    if (j == 0) {
                        if (i != -1 && i != 2 && k != -1) {
                            this.level.setTile(x + a, y + b, z + c, 0);
                        } else {
                            this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                        }
                    } else if (j == 1) {
                        if (i == -1) {
                            this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                            this.level.setTileMeta(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                        } else if (i == 2) {
                            this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                            this.level.setTileMeta(x + a, y + b, z + c, (direction + 2) % 4);
                        } else if (k == -1) {
                            this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                        } else {
                            this.level.setTile(x + a, y + b, z + c, 0);
                        }
                    } else if (i == 0) {
                        this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                        this.level.setTileMeta(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                    } else if (i == 1) {
                        this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                        this.level.setTileMeta(x + a, y + b, z + c, (direction + 2) % 4);
                    } else {
                        this.level.setTile(x + a, y + b, z + c, 0);
                    }
                }
            }
        }

        this.level.playSound(this, "random.wood click", 1.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        this.level.playSound(this, "step.wood", 1.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        this.logs -= 5;
    }

    public void buildHouseTwo() {
        int x = MathHelper.floor(this.x);
        int y = MathHelper.floor(this.boundingBox.minY);
        int z = MathHelper.floor(this.z);
        int direction = this.rand.nextInt(4);

        for(int j = 0; j < 3; ++j) {
            int b = j;

            for(int i = -2; i < 3; ++i) {
                for(int k = -2; k < 3; ++k) {
                    int a = i;
                    int c = k;
                    if (direction % 2 == 0) {
                        a = -i;
                        c = -k;
                    }

                    if (direction / 2 == 0) {
                        int swap = a;
                        a = c;
                        c = swap;
                    }

                    if (i != -2 && i != 2 || k != -2 && k != 2) {
                        if (j != 0 && j != 1) {
                            if (j == 2) {
                                if (i == -2) {
                                    this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                                    this.level.setTileMeta(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if (i == 2) {
                                    this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                                    this.level.setTileMeta(x + a, y + b, z + c, (direction + 2) % 4);
                                } else if (k == -2) {
                                    this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                                    this.level.setTileMeta(x + a, y + b, z + c, (direction + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if (k == 2) {
                                    this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                                    this.level.setTileMeta(x + a, y + b, z + c, direction % 4);
                                } else {
                                    this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                                }
                            }
                        } else if (i != -2 && i != 2 && k != -2 && (k != 2 || i == 0 && j != 1)) {
                            this.level.setTile(x + a, y + b, z + c, 0);
                        } else {
                            this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                        }
                    }
                }
            }
        }

        this.level.playSound(this, "random.wood click", 1.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        this.level.playSound(this, "step.wood", 1.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        this.logs -= 10;
    }

    public void buildHouseThree() {
        int x = MathHelper.floor(this.x);
        int y = MathHelper.floor(this.boundingBox.minY);
        int z = MathHelper.floor(this.z);
        int direction = this.rand.nextInt(4);

        for(int j = 0; j < 4; ++j) {
            int b = j;

            for(int i = -3; i < 4; ++i) {
                for(int k = -2; k < 3; ++k) {
                    int a = i;
                    int c = k;
                    if (direction % 2 == 0) {
                        a = -i;
                        c = -k;
                    }

                    if (direction / 2 == 0) {
                        int swap = a;
                        a = c;
                        c = swap;
                    }

                    if (i != -3 && i != 3 || k != -2 && k != 2) {
                        if (j < 3) {
                            if (i != -3 && i != 3 && k != -2 && (k != 2 || i == 0 && j <= 0)) {
                                if (i == -2 && j == 0 && k == 0) {
                                    this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.CHEST.id);
                                    this.level.setTileMeta(x + a, y + b, z + c, (direction + 2) % 4);
                                    TileEntityChest chest = (TileEntityChest)this.level.getTileEntity(x + a, y + b, z + c);
                                    chest.setInventoryItem(0, new ItemInstance(ItemBase.stick, 16, 0));
                                } else if (i == 0 && j == 0 && k == -1) {
                                    this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                                    this.level.setTileMeta(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if (i == 1 && j == 1 && k == -1) {
                                    this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                                    this.level.setTileMeta(x + a, y + b, z + c, (direction + 2 + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if (i == 2 && j == 1 && k == -1) {
                                    this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                                } else if (i == 2 && j == 2 && k == 0) {
                                    this.level.setTileInChunk(x + a, y + b, z + c, BlockBase.WOOD_STAIRS.id);
                                    this.level.setTileMeta(x + a, y + b, z + c, (direction + (direction % 2 == 0 ? 1 : -1)) % 4);
                                } else if (i == 0 && j == 2 && k == -1) {
                                    this.level.setTile(x + a, y + b, z + c, 0);
                                } else if (i == 1 && j == 2 && k == -1) {
                                    this.level.setTile(x + a, y + b, z + c, 0);
                                } else if (i == 2 && j == 2 && k == -1) {
                                    this.level.setTile(x + a, y + b, z + c, 0);
                                } else if (j == 2) {
                                    this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                                } else {
                                    this.level.setTile(x + a, y + b, z + c, 0);
                                }
                            } else {
                                this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                            }
                        } else if (j == 3) {
                            if (i != -3 && i != 3 && k != -2 && (k != 2 || i == 0 && j <= 0)) {
                                this.level.setTile(x + a, y + b, z + c, 0);
                            } else if (i != -2 && i != 0 && i != 2 && k != 0) {
                                this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                                this.level.setTileMeta(x + a, y + b, z + c, 2);
                            } else {
                                this.level.setTile(x + a, y + b, z + c, BlockBase.WOOD.id);
                            }
                        }
                    }
                }
            }
        }

        this.level.playSound(this, "random.wood click", 1.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        this.level.playSound(this, "step.wood", 1.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        this.logs -= 20;
    }

    @Override
    public void travel(float f, float f1) {
        // moveEntityWithHeading -> travel
        super.travel(f, f1);
        double d2 = (this.x - this.prevX) * 2.0D;
        double d3 = (this.z - this.prevZ) * 2.0D;
        float f5 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4.0F;
        if (f5 > 1.0F) {
            f5 = 1.0F;
        }

        // field_704_R -> limbDistance
        this.limbDistance += (f5 - this.limbDistance) * 0.4F;
        // field 703_S -> field_1050
        this.field_1050 += this.limbDistance;
    }

    public void swingArm() {
        if (!this.isSwinging) {
            this.isSwinging = true;
            // prevSwingProcess -> lastHandSwingProgress
            this.lastHandSwingProgress = 0.0F;
            // swingProgress -> handSwingProgress
            this.handSwingProgress = 0.0F;
        }

    }

    public void swingLeftArm() {
        if (!this.isSwingingLeft) {
            this.isSwingingLeft = true;
            this.swingLeft = 0.01F;
        }

    }

    public boolean hitTargetMakesDead(EntityBase e) {
        this.strikeTime = 12;
        this.swingArm();
        int power = this.weaponPoints > 0 ? 3 + this.rand.nextInt(2) + (this.stickSharp ? 1 : 0) : 2;
        if (this.weaponPoints > 0) {
            --this.weaponPoints;
        }

        boolean flag = e.damage(this, power);
        if (flag && e instanceof Living) {
            Living el = (Living)e;
            if (el.health <= 0) {
                return true;
            }
        }

        return false;
    }

    public void throwRockAtEnemy(EntityBase entity) {
        double d = entity.z - this.x;
        double d1 = entity.z - this.z;
        EntityGravelChunk entitygravelchunk = new EntityGravelChunk(this.level, this, this.clayTeam);
        entitygravelchunk.y += 0.3999999761581421D;
        double d2 = entity.y + (double)entity.getEyeHeight() - 0.10000000298023223D - entitygravelchunk.y;
        float f1 = MathHelper.sqrt(d * d + d1 * d1) * 0.2F;
        this.level.spawnEntity(entitygravelchunk);
        entitygravelchunk.setArrowHeading(d, d2 + (double)f1, d1, 0.6F, 12.0F);
        this.attackTime = 30;
        this.field_1029 = -this.field_1029;
        this.yaw = (float)(Math.atan2(d1, d) * 180.0D / 3.1415927410125732D) - 90.0F;
        // hasAttacked
        this.field_663 = true;
        this.swingLeftArm();
    }

    public void gotcha(Item item) {
        this.level.playSound(item, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        if (item.item != null) {
            --item.item.count;
            if (item.item.count <= 0) {
                // setEntityDead -> remove
                item.remove();
            }
        } else {
            item.remove();
        }

        this.targetFollow = null;
        this.setTarget((class_61)null);
    }

    public void gotcha(TileEntityChest chest, int q) {
        this.level.playSound(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        //decrStackSize -> takeInventoryItem
        chest.takeInventoryItem(q, 1);
    }

    @Override
    public void writeCustomDataToTag(CompoundTag nbttagcompound) {
        super.writeCustomDataToTag(nbttagcompound);
        nbttagcompound.put("ClayTeam", (short)this.clayTeam);
        nbttagcompound.put("WeaponPoints", (short)this.weaponPoints);
        nbttagcompound.put("ArmorPoints", (short)this.armorPoints);
        nbttagcompound.put("FoodLeft", (short)this.foodLeft);
        nbttagcompound.put("SugarTime", (short)this.sugarTime);
        nbttagcompound.put("ResPoints", (short)this.resPoints);
        nbttagcompound.put("StrikeTime", (short)this.strikeTime);
        nbttagcompound.put("ClimbTime", (short)this.climbTime);
        nbttagcompound.put("GooTime", (short)this.gooTime);
        nbttagcompound.put("SmokeTime", (short)this.smokeTime);
        nbttagcompound.put("GooStock", (short)this.gooStock);
        nbttagcompound.put("SmokeStock", (short)this.smokeStock);
        nbttagcompound.put("Logs", (short)this.logs);
        nbttagcompound.put("Rocks", (short)this.rocks);
        nbttagcompound.put("Gunpowdered", this.gunpowdered);
        nbttagcompound.put("King", this.king);
        nbttagcompound.put("Glowing", this.glowing);
        nbttagcompound.put("StickSharp", this.stickSharp);
        nbttagcompound.put("ArmorPadded", this.armorPadded);
        nbttagcompound.put("HeavyCore", this.heavyCore);
    }

    @Override
    public void readCustomDataFromTag(CompoundTag nbttagcompound) {
        super.readCustomDataFromTag(nbttagcompound);
        this.clayTeam = nbttagcompound.getShort("ClayTeam");
        this.texture = this.clayManTexture(this.clayTeam);
        this.weaponPoints = nbttagcompound.getShort("WeaponPoints");
        this.armorPoints = nbttagcompound.getShort("ArmorPoints");
        this.foodLeft = nbttagcompound.getShort("FoodLeft");
        this.sugarTime = nbttagcompound.getShort("SugarTime");
        this.resPoints = nbttagcompound.getShort("ResPoints");
        this.strikeTime = nbttagcompound.getShort("StrikeTime");
        this.climbTime = nbttagcompound.getShort("ClimbTime");
        this.gooTime = nbttagcompound.getShort("GooTime");
        this.smokeTime = nbttagcompound.getShort("SmokeTime");
        this.gooStock = nbttagcompound.getShort("GooStock");
        this.smokeStock = nbttagcompound.getShort("SmokeStock");
        this.logs = nbttagcompound.getShort("Logs");
        this.rocks = nbttagcompound.getShort("Rocks");
        this.gunpowdered = nbttagcompound.getBoolean("Gunpowdered");
        this.king = nbttagcompound.getBoolean("King");
        this.glowing = nbttagcompound.getBoolean("Glowing");
        this.stickSharp = nbttagcompound.getBoolean("StickSharp");
        this.armorPadded = nbttagcompound.getBoolean("ArmorPadded");
        this.heavyCore = nbttagcompound.getBoolean("HeavyCore");
    }

    @Override
    protected String getHurtSound() {
        this.level.playSound(this, "random.hurt", 0.6F, 1.0F * (this.rand.nextFloat() * 0.2F + 1.6F));
        this.level.playSound(this, "step.gravel", 0.6F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        return "";
    }

    @Override
    protected String getDeathSound() {
        this.level.playSound(this, "random.hurt", 0.6F, 1.0F * (this.rand.nextFloat() * 0.2F + 1.6F));
        return "step.gravel";
    }

    @Override
    protected void jump() {
        if (this.gooTime <= 0) {
            if (this.sugarTime > 0) {
                this.velocityY = 0.375D;
            } else {
                this.velocityY = 0.275D;
            }
        }
    }

    @Override
    public boolean method_932() {
        // isOnLadder
        // isCollidedHorizontally -> field_1624
        if (this.logs <= 0 && this.field_1624 && !this.onGround && this.climbTime > 0) {
            if (this.climbTime != 10) {
                this.throwTime = 5;
                --this.climbTime;
                return true;
            }

            if (this.velocityY < 0.05D) {
                --this.climbTime;
                this.throwTime = 5;
                return true;
            }
        }

        return false;
    }

    @Override
    protected boolean method_940() { // canDespawn
        return false;
    }

    public boolean hasStick() {
        return this.weaponPoints > 0;
    }

    public boolean hasArmor() {
        return this.armorPoints > 0;
    }

    public boolean hasSpecks() {
        return this.gunpowdered;
    }

    public boolean hasCrown() {
        return this.king;
    }

    public boolean isGlowing() {
        return this.glowing;
    }

    public boolean isSharpened() {
        return this.stickSharp;
    }

    public boolean isPadded() {
        return this.armorPadded;
    }

    public boolean isGooey() {
        return this.gooTime > 0;
    }

    public boolean hasLogs() {
        return this.logs > 0;
    }

    public float armLeft() {
        return this.swingLeft;
    }

    public boolean hasRocks() {
        return this.rocks > 0 && this.throwTime <= 0 && this.logs <= 0;
    }

    @Override
    public void getDrops() {
        if (!this.gunpowdered) {
            ItemBase item1 = ItemListener.greyDoll.asItem();
            if (this.clayTeam == 1) {
                item1 = ItemListener.redDoll.asItem();
            } else if (this.clayTeam == 2) {
                item1 = ItemListener.yellowDoll.asItem();
            } else if (this.clayTeam == 3) {
                item1 = ItemListener.greenDoll.asItem();
            } else if (this.clayTeam == 4) {
                item1 = ItemListener.blueDoll.asItem();
            } else if (this.clayTeam == 5) {
                item1 = ItemListener.orangeDoll.asItem();
            } else if (this.clayTeam == 6) {
                item1 = ItemListener.purpleDoll.asItem();
            }

            this.dropItem(item1.id, 1);
            if (this.resPoints > 0) {
                this.dropItem(ItemBase.clay.id, 1);
            }

            if (this.weaponPoints > 7 && this.rand.nextInt(2) == 0) {
                this.dropItem(ItemBase.stick.id, 1);
            }

            if (this.armorPoints > 7 && this.rand.nextInt(2) == 0) {
                this.dropItem(ItemBase.leather.id, 1);
            }

            if (this.rocks > 7 && this.rand.nextInt(2) == 0) {
                this.dropItem(BlockBase.GRAVEL.id, 1);
            }

            if (this.smokeStock > 1 && this.rand.nextInt(2) == 0) {
                this.dropItem(ItemBase.redstoneDust.id, 1);
            }

            if (this.gooStock > 1 && this.rand.nextInt(2) == 0) {
                this.dropItem(ItemBase.slimeball.id, 1);
            }

            if (this.smokeStock > 1 && this.rand.nextInt(2) == 0) {
                this.dropItem(ItemBase.redstoneDust.id, 1);
            }

            if (this.gooStock > 1 && this.rand.nextInt(2) == 0) {
                this.dropItem(ItemBase.slimeball.id, 1);
            }

            if (this.glowing && this.rand.nextInt(2) == 0) {
                this.dropItem(ItemBase.glowstoneDust.id, 1);
            }

            if (this.king) {
                this.dropItem(ItemBase.goldIngot.id, 1);
            }

            if (this.heavyCore) {
                this.dropItem(ItemBase.ironIngot.id, 1);
            }

            if (this.logs > 0) {
                this.dropLogs();
            }
        }

    }

    @Override
    public boolean damage(EntityBase e, int i) {
        if (this.vehicle != null && i < 100 && this.rand.nextInt(2) == 0) {
            // attackEntityFrom -> damage
            return this.vehicle.damage(e, i);
        } else {
            if (e != null && e instanceof EntityClayMan) {
                EntityClayMan james = (EntityClayMan)e;
                if (james.clayTeam == this.clayTeam) {
                    return false;
                }

                if (this.logs > 0) {
                    this.dropLogs();
                }

                if (this.smokeTime <= 0) {
                    this.entity = e;
                }

                if (this.armorPoints > 0) {
                    i /= 2;
                    if (this.armorPadded) {
                        --i;
                    }

                    --this.armorPoints;
                    if (i < 0) {
                        i = 0;
                    }
                }

                if (this.health - i > 0) {
                    int j;
                    double a;
                    double b;
                    double c;
                    if ((james.smokeStock <= 0 || this.smokeTime <= 0 || this.rand.nextInt(2) == 0) && james.gooStock > 0 && this.gooTime <= 0 && this.onGround) {
                        --james.gooStock;
                        this.gooTime = 150;
                        this.level.playSound(this, "mob.slimeattack", 0.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));

                        for(j = 0; j < 4; ++j) {
                            a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                            b = this.boundingBox.minY + 0.125D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                            c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                            this.level.addParticle("slime", a, b, c, 0.0D, 0.1D, 0.0D);
                        }

                        this.velocityX = 0.0D;
                        this.velocityY = 0.0D;
                        this.velocityZ = 0.0D;
                        this.field_1029 = 0.0F;
                        this.field_1060 = 0.0F;
                        this.jumping = false;
                    } else if (james.smokeStock > 0 && this.smokeTime <= 0) {
                        --james.smokeStock;
                        this.smokeTime = 100;
                        this.level.playSound(this, "random.fizz", 0.75F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));

                        for(j = 0; j < 8; ++j) {
                            a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                            b = this.boundingBox.minY + 0.25D + (double)this.rand.nextFloat() * 0.25D;
                            c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                            this.level.addParticle("reddust", a, b, c, 0.0D, 0.1D, 0.0D);
                        }

                        this.targetFollow = null;
                        this.entity = null;
                        this.setTarget((class_61)null);
                    }
                }
            } else {
                i = 20;
                if (e instanceof FishHook) {
                    return false;
                }
            }

            boolean fred = super.damage(e, i);
            if (fred && this.health <= 0) {
                ItemBase item1 = ItemListener.greyDoll.asItem();
                if (this.clayTeam == 1) {
                    item1 = ItemListener.redDoll.asItem();
                } else if (this.clayTeam == 2) {
                    item1 = ItemListener.yellowDoll.asItem();
                } else if (this.clayTeam == 3) {
                    item1 = ItemListener.greenDoll.asItem();
                } else if (this.clayTeam == 4) {
                    item1 = ItemListener.blueDoll.asItem();
                } else if (this.clayTeam == 5) {
                    item1 = ItemListener.orangeDoll.asItem();
                } else if (this.clayTeam == 6) {
                    item1 = ItemListener.purpleDoll.asItem();
                }

                for(int q = 0; q < 24; ++q) {
                    double a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                    double b = this.y + 0.25D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                    double c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                    //todo: ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntitySlimeFX(this.worldObj, a, b, c, item1));
                }

                this.removed = true;
                if (e != null && e instanceof PlayerBase) {
                    this.killedByPlayer = e;
                }

                if (this.gunpowdered) {
                    this.level.createExplosion((EntityBase) null, this.x, this.y, this.z, 1.0F);
                }
            }

            return fred;
        }
    }

    @Override
    public void accelerate(double d, double d1, double d2) {
        if (this.gooTime <= 0) {
            this.velocityX += d;
            this.velocityY += d1;
            this.velocityZ += d2;
        }
    }

    @Override
    public void method_925(EntityBase entity, int i, double d, double d1) {
        if (this.gooTime <= 0) {
            // knockback -> method_925
            super.method_925(entity, i, d, d1);
            if (entity != null && entity instanceof EntityClayMan) {
                EntityClayMan ec = (EntityClayMan)entity;
                if ((!ec.heavyCore || !this.heavyCore) && (ec.heavyCore || this.heavyCore)) {
                    if (!ec.heavyCore && this.heavyCore) {
                        this.velocityX *= 0.2D;
                        this.velocityY *= 0.4D;
                        this.velocityZ *= 0.2D;
                    } else {
                        this.velocityX *= 1.5D;
                        this.velocityZ *= 1.5D;
                    }
                } else {
                    this.velocityX *= 0.6D;
                    this.velocityY *= 0.75D;
                    this.velocityZ *= 0.6D;
                }
            } else if (entity != null && entity instanceof EntityGravelChunk) {
                this.velocityX *= 0.6D;
                this.velocityY *= 0.75D;
                this.velocityZ *= 0.6D;
            }

        }
    }

    // scope needed for horse
    public boolean isJumping()
    {
        return jumping;
    }
    public void setJumping(boolean j)
    {
        jumping = j;
    }

    public float getMoveForward()
    {
        return field_1029;
    }

    public float getMoveStrafe()
    {
        return field_1060;
    }

    public float getFallDistance()
    {
        return fallDistance;
    }

    public void setFallDistance(float f)
    {
        fallDistance = f;
    }
}
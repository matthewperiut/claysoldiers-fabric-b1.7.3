package com.matthewperiut.claysoldiers.entity.behavior;

import com.matthewperiut.claysoldiers.item.ItemListener;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;

import java.util.List;

public class EntityDirtHorse extends AnimalBase {
    public boolean gotRider;

    public EntityDirtHorse(Level world) {
        super(world);
        this.health = 30;
        this.standingEyeHeight = 0.0F;
        this.field_1641 = 0.1F;
        this.movementSpeed = 0.6F;
        this.setSize(0.25F, 0.4F);
        this.setPosition(this.x, this.y, this.z);
        this.texture = "claysoldiers:textures/entities/dirtHorse.png";
        this.renderDistanceMultiplier = 5.0D;
    }

    public EntityDirtHorse(Level world, double x, double y, double z) {
        super(world);
        this.health = 30;
        this.standingEyeHeight = 0.0F;
        this.field_1641 = 0.1F;
        this.movementSpeed = 0.6F;
        this.setSize(0.25F, 0.4F);
        this.setPosition(x, y, z);
        this.texture = "claysoldiers:textures/entities/dirtHorse.png";
        this.renderDistanceMultiplier = 5.0D;
        this.level.playSound(this, "step.gravel", 0.8F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.9F);
    }

    public void tick() {
        super.tick();
        if (this.gotRider) {
            if (this.passenger != null) {
                this.gotRider = false;
                return;
            }

            List list = this.level.getEntities(this, this.boundingBox.expand(0.1D, 0.1D, 0.1D));

            for(int i = 0; i < list.size(); ++i) {
                EntityBase entity = (EntityBase)list.get(i);
                if (entity instanceof EntityClayMan) {
                    Living entityliving = (Living)entity;
                    if (entityliving.vehicle == null && entityliving.passenger != this) {
                        entity.startRiding(this);
                        break;
                    }
                }
            }

            this.gotRider = false;
        }

    }

    public void tickHandSwing() {
        if (this.passenger != null && this.passenger instanceof EntityClayMan) {
            EntityClayMan rider = (EntityClayMan)this.passenger;
            this.jumping = rider.isJumping() || this.isTouchingWater();
            this.field_1029 = rider.getMoveForward() * (rider.sugarTime > 0 ? 1.0F : 2.0F);
            this.field_1060 = rider.getMoveStrafe() * (rider.sugarTime > 0 ? 1.0F : 2.0F);
            this.yaw = this.prevYaw = rider.yaw;
            this.pitch = this.prevPitch = rider.pitch;
            rider.field_1012 = this.field_1012;
            ((EntityClayMan) this.passenger).setFallDistance(0.0F);
            if (rider.removed || rider.health <= 0) {
                rider.startRiding((EntityBase)null);
            }
        } else {
            super.tickHandSwing();
        }

    }

    public void travel(float f, float f1) {
        super.travel(f, f1);
        double d2 = (this.x - this.prevX) * 2.0D;
        double d3 = (this.z - this.prevZ) * 2.0D;
        float f5 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4.0F;
        if (f5 > 1.0F) {
            f5 = 1.0F;
        }

        this.limbDistance += (f5 - this.limbDistance) * 0.4F;
        this.field_1050 += this.limbDistance;
    }

    public void writeCustomDataToTag(CompoundTag nbttagcompound) {
        super.writeCustomDataToTag(nbttagcompound);
        this.gotRider = this.passenger == null;
        nbttagcompound.put("GotRider", this.gotRider);
    }

    public void readCustomDataFromTag(CompoundTag nbttagcompound) {
        super.readCustomDataFromTag(nbttagcompound);
        this.gotRider = nbttagcompound.getBoolean("GotRider");
    }

    protected String getHurtSound() {
        this.level.playSound(this, "step.gravel", 0.6F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        return "";
    }

    protected String getDeathSound() {
        return "step.gravel";
    }

    protected void jump() {
        this.velocityY = 0.4D;
    }

    protected boolean canDespawn() {
        return false;
    }

    public void getDrops() {
        ItemBase item1 = ItemListener.horseDoll;
        this.dropItem(item1.id, 1);
    }

    public boolean damage(EntityBase e, int i) {
        if (e == null || !(e instanceof EntityClayMan)) {
            i = 30;
        }

        boolean fred = super.damage(e, i);
        if (fred && this.health <= 0) {
            ItemBase item1 = ItemListener.horseDoll;

            for(int q = 0; q < 24; ++q) {
                double a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                double b = this.y + 0.25D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                double c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                //////////////////ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntitySlimeFX(this.worldObj, a, b, c, item1));
            }

            this.removed = true;
        }

        return fred;
    }

    public void method_925(EntityBase entity, int i, double d, double d1) {
        super.method_925(entity, i, d, d1);
        if (entity != null && entity instanceof EntityClayMan) {
            this.velocityX *= 0.6D;
            this.velocityY *= 0.75D;
            this.velocityZ *= 0.6D;
        }

    }

    public boolean isOnLadder() {
        return false;
    }
}

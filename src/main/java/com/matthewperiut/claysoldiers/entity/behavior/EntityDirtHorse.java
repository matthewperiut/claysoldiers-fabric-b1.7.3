package com.matthewperiut.claysoldiers.entity.behavior;

import com.matthewperiut.claysoldiers.entity.EntityListener;
import com.matthewperiut.claysoldiers.item.ItemListener;
import com.matthewperiut.claysoldiers.util.ClientUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityDirtHorse extends AnimalEntity implements MobSpawnDataProvider {
    public boolean gotRider;

    public EntityDirtHorse(World world) {
        super(world);
        this.health = 30;
        this.standingEyeHeight = 0.0F;
        this.stepHeight = 0.1F;
        this.movementSpeed = 0.6F;
        this.setBoundingBoxSpacing(0.25F, 0.4F);
        this.setPosition(this.x, this.y, this.z);
        this.texture = "claysoldiers:textures/entities/dirtHorse.png";
        this.renderDistanceMultiplier = 5.0D;
    }

    public EntityDirtHorse(World world, double x, double y, double z) {
        super(world);
        this.health = 30;
        this.standingEyeHeight = 0.0F;
        this.stepHeight = 0.1F;
        this.movementSpeed = 0.6F;
        this.setBoundingBoxSpacing(0.25F, 0.4F);
        this.setPosition(x, y, z);
        this.texture = "/claymans/dirtHorse.png";
        this.renderDistanceMultiplier = 5.0;
        this.world.playSound(this, "step.gravel", 0.8F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.9F);
    }

    public void tick() {
        super.tick();
        if (this.gotRider) {
            if (this.passenger != null) {
                this.gotRider = false;
                return;
            }

            List list = this.world.getEntities(this, this.boundingBox.expand(0.1, 0.1, 0.1));

            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity) list.get(i);
                if (entity instanceof EntityClayMan) {
                    LivingEntity entityliving = (LivingEntity) entity;
                    if (entityliving.vehicle == null && entityliving.passenger != this) {
                        entity.setVehicle(this);
                        break;
                    }
                }
            }

            this.gotRider = false;
        }

    }

    public void tickLiving() {
        if (this.passenger != null && this.passenger instanceof EntityClayMan rider) {
            this.jumping = rider.isJumping() || this.checkWaterCollisions();
            this.forwardSpeed = rider.getForwardVelocity() * (rider.sugarTime > 0 ? 1.0F : 2.0F);
            this.sidewaysSpeed = rider.getHorizontalVelocity() * (rider.sugarTime > 0 ? 1.0F : 2.0F);
            this.yaw = this.prevYaw = rider.yaw;
            this.pitch = this.prevPitch = rider.pitch;
            rider.bodyYaw = this.bodyYaw;
            ((EntityClayMan) this.passenger).setFallDistance(0.0F);
            if (rider.dead || rider.health <= 0) {
                rider.setVehicle(null);
            }
        } else {
            super.tickLiving();
        }

    }

    public void travel(float f, float f1) {
        super.travel(f, f1);
        double d2 = (this.x - this.prevX) * 2.0;
        double d3 = (this.z - this.prevZ) * 2.0;
        float f5 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4.0F;
        if (f5 > 1.0F) {
            f5 = 1.0F;
        }

        this.walkAnimationSpeed += (f5 - this.walkAnimationSpeed) * 0.4F;
        this.walkAnimationProgress += this.walkAnimationSpeed;
    }

    public void write(NbtCompound nbttagcompound) {
        super.write(nbttagcompound);
        this.gotRider = this.passenger == null;
        nbttagcompound.putBoolean("GotRider", this.gotRider);
    }

    public void read(NbtCompound nbttagcompound) {
        super.read(nbttagcompound);
        this.gotRider = nbttagcompound.getBoolean("GotRider");
    }

    protected String getHurtSound() {
        this.world.playSound(this, "step.gravel", 0.6F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        return "";
    }

    protected String getDeathSound() {
        return "step.gravel";
    }

    protected void jump() {
        this.velocityY = 0.4;
    }

    protected boolean canDespawn() {
        return false;
    }

    public void dropItems() {
        Item item1 = ItemListener.horseDoll;
        this.dropItem(item1.id, 1);
    }

    public boolean damage(Entity e, int i) {
        if (e == null || !(e instanceof EntityClayMan)) {
            i = 30;
        }

        boolean fred = super.damage(e, i);
        if (fred && this.health <= 0) {
            Item item1 = ItemListener.horseDoll;

            for (int q = 0; q < 24; ++q) {
                double a = this.x + (double) (this.random.nextFloat() - this.random.nextFloat()) * 0.125;
                double b = this.y + 0.25 + (double) (this.random.nextFloat() - this.random.nextFloat()) * 0.125;
                double c = this.z + (double) (this.random.nextFloat() - this.random.nextFloat()) * 0.125;
                ClientUtil.addPoofParticle(this.world, a, b, c, item1);
            }

            this.dead = true;
        }

        return fred;
    }

    public void applyKnockback(Entity entity, int i, double d, double d1) {
        super.applyKnockback(entity, i, d, d1);
        if (entity != null && entity instanceof EntityClayMan) {
            this.velocityX *= 0.6;
            this.velocityY *= 0.75;
            this.velocityZ *= 0.6;
        }

    }

    public boolean isOnLadder() {
        return false;
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(EntityListener.MOD_ID, "dirthorse");
    }
}

package com.matthewperiut.claysoldiers.entity.behavior;

import com.matthewperiut.claysoldiers.item.ItemListener;
import com.matthewperiut.claysoldiers.util.ClientUtil;
import net.minecraft.entity.AbstractAnimalEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

import static com.matthewperiut.claysoldiers.ClaySoldiersMod.MODID;
import static net.modificationstation.stationapi.api.registry.Identifier.of;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityDirtHorse extends AbstractAnimalEntity implements MobSpawnDataProvider {
    public boolean gotRider;

    public EntityDirtHorse(World world) {
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

    public EntityDirtHorse(World world, double x, double y, double z) {
        super(world);
        this.health = 30;
        this.standingEyeHeight = 0.0F;
        this.field_1641 = 0.1F;
        this.movementSpeed = 0.6F;
        this.setSize(0.25F, 0.4F);
        this.setPosition(x, y, z);
        this.texture = "/claymans/dirtHorse.png";
        this.renderDistanceMultiplier = 5.0;
        this.world.playSound(this, "step.gravel", 0.8F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.9F);
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
                        entity.startRiding(this);
                        break;
                    }
                }
            }

            this.gotRider = false;
        }

    }

    public void tickHandSwing() {
        if (this.passenger != null && this.passenger instanceof EntityClayMan rider) {
            this.jumping = rider.isJumping() || this.method_1393();
            this.forwardVelocity = rider.getForwardVelocity() * (rider.sugarTime > 0 ? 1.0F : 2.0F);
            this.horizontalVelocity = rider.getHorizontalVelocity() * (rider.sugarTime > 0 ? 1.0F : 2.0F);
            this.yaw = this.prevYaw = rider.yaw;
            this.pitch = this.prevPitch = rider.pitch;
            rider.field_1012 = this.field_1012;
            ((EntityClayMan) this.passenger).setFallDistance(0.0F);
            if (rider.removed || rider.health <= 0) {
                rider.startRiding(null);
            }
        } else {
            super.tickHandSwing();
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

        this.limbDistance += (f5 - this.limbDistance) * 0.4F;
        this.field_1050 += this.limbDistance;
    }

    public void writeNBT(CompoundTag nbttagcompound) {
        super.writeNBT(nbttagcompound);
        this.gotRider = this.passenger == null;
        nbttagcompound.put("GotRider", this.gotRider);
    }

    public void readNBT(CompoundTag nbttagcompound) {
        super.readNBT(nbttagcompound);
        this.gotRider = nbttagcompound.getBoolean("GotRider");
    }

    protected String getHurtSound() {
        this.world.playSound(this, "step.gravel", 0.6F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
        return "";
    }

    protected String getDeathSound() {
        return "step.gravel";
    }

    protected void jump() {
        this.yVelocity = 0.4;
    }

    protected boolean canDespawn() {
        return false;
    }

    public void getDrops() {
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
                double a = this.x + (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.125;
                double b = this.y + 0.25 + (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.125;
                double c = this.z + (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.125;
                ClientUtil.addPoofParticle(this.world, a, b, c, item1);
            }

            this.removed = true;
        }

        return fred;
    }

    public void method_925(Entity entity, int i, double d, double d1) {
        super.method_925(entity, i, d, d1);
        if (entity != null && entity instanceof EntityClayMan) {
            this.xVelocity *= 0.6;
            this.yVelocity *= 0.75;
            this.zVelocity *= 0.6;
        }

    }

    public boolean method_932() {
        return false;
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return of(MODID, "dirthorse");
    }
}

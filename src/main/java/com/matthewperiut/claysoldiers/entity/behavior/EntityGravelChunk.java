package com.matthewperiut.claysoldiers.entity.behavior;

import com.matthewperiut.claysoldiers.entity.EntityListener;
import com.matthewperiut.claysoldiers.util.ClientUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityGravelChunk extends Entity implements MobSpawnDataProvider {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private int field_28019_h = 0;
    private boolean inGround = false;
    public boolean doesArrowBelongToPlayer = false;
    public int arrowShake = 0;
    public LivingEntity owner;
    private int ticksInGround;
    private int ticksInAir = 0;
    public int clayTeam;
    public int entityAge;

    public EntityGravelChunk(World world) {
        super(world);
        this.setBoundingBoxSpacing(0.1F, 0.1F);
        this.renderDistanceMultiplier = 5.0;
        this.setPosition(this.x, this.y, this.z);
    }

    public EntityGravelChunk(World world, double d, double d1, double d2, int i) {
        super(world);
        this.setBoundingBoxSpacing(0.1F, 0.1F);
        this.renderDistanceMultiplier = 5.0;
        this.setPosition(d, d1, d2);
        this.standingEyeHeight = 0.0F;
        this.clayTeam = i;
    }

    public EntityGravelChunk(World world, LivingEntity entityliving, int i) {
        super(world);
        this.owner = entityliving;
        this.doesArrowBelongToPlayer = entityliving instanceof PlayerEntity;
        this.setBoundingBoxSpacing(0.1F, 0.1F);
        this.renderDistanceMultiplier = 5.0;
        this.setPositionAndAnglesKeepPrevAngles(entityliving.x, entityliving.y + (double) entityliving.getEyeHeight(), entityliving.z, entityliving.yaw, entityliving.pitch);
        this.x -= MathHelper.cos(this.yaw / 180.0F * 3.141593F) * 0.16F;
        this.y -= 0.10000000149011612;
        this.z -= MathHelper.sin(this.yaw / 180.0F * 3.141593F) * 0.16F;
        this.setPosition(this.x, this.y, this.z);
        this.standingEyeHeight = 0.0F;
        this.velocityX = -MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F);
        this.velocityZ = MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F);
        this.velocityY = -MathHelper.sin(this.pitch / 180.0F * 3.141593F);
        this.setArrowHeading(this.velocityX, this.velocityY, this.velocityZ, 1.5F, 1.0F);
        this.clayTeam = i;
    }

    protected void initDataTracker() {
    }

    public void setArrowHeading(double d, double d1, double d2, float f, float f1) {
        float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += this.random.nextGaussian() * 0.007499999832361937 * (double) f1;
        d1 += this.random.nextGaussian() * 0.007499999832361937 * (double) f1;
        d2 += this.random.nextGaussian() * 0.007499999832361937 * (double) f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        this.velocityX = d;
        this.velocityY = d1;
        this.velocityZ = d2;
        float f3 = MathHelper.sqrt(d * d + d2 * d2);
        this.prevYaw = this.yaw = (float) (Math.atan2(d, d2) * 180.0 / 3.1415927410125732);
        this.prevPitch = this.pitch = (float) (Math.atan2(d1, f3) * 180.0 / 3.1415927410125732);
        this.ticksInGround = 0;
    }

    public void setVelocityClient(double d, double d1, double d2) {
        this.velocityX = d;
        this.velocityY = d1;
        this.velocityZ = d2;
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            float f = MathHelper.sqrt(d * d + d2 * d2);
            this.prevYaw = this.yaw = (float) (Math.atan2(d, d2) * 180.0 / 3.1415927410125732);
            this.prevPitch = this.pitch = (float) (Math.atan2(d1, f) * 180.0 / 3.1415927410125732);
            this.prevPitch = this.pitch;
            this.prevYaw = this.yaw;
            this.setPositionAndAnglesKeepPrevAngles(this.x, this.y, this.z, this.yaw, this.pitch);
            this.ticksInGround = 0;
        }

    }

    public void tick() {
        ++this.entityAge;
        if (this.entityAge > 99999) {
            this.entityAge = 0;
        }

        super.tick();
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            float f = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
            this.prevYaw = this.yaw = (float) (Math.atan2(this.velocityX, this.velocityZ) * 180.0 / 3.1415927410125732);
            this.prevPitch = this.pitch = (float) (Math.atan2(this.velocityY, f) * 180.0 / 3.1415927410125732);
        }

        int i = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
        if (i > 0) {
            Block.BLOCKS[i].updateBoundingBox(this.world, this.xTile, this.yTile, this.zTile);
            Box axisalignedbb = Block.BLOCKS[i].getCollisionShape(this.world, this.xTile, this.yTile, this.zTile);
            if (axisalignedbb != null && axisalignedbb.contains(Vec3d.createCached(this.x, this.y, this.z))) {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inGround) {
            int j = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
            int k = this.world.getBlockMeta(this.xTile, this.yTile, this.zTile);
            if (j == this.inTile && k == this.field_28019_h) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1200) {
                    this.markDead();
                }

            } else {
                this.inGround = false;
                this.velocityX *= this.random.nextFloat() * 0.2F;
                this.velocityY *= this.random.nextFloat() * 0.2F;
                this.velocityZ *= this.random.nextFloat() * 0.2F;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            ++this.ticksInAir;
            Vec3d vec3d = Vec3d.createCached(this.x, this.y, this.z);
            Vec3d vec3d1 = Vec3d.createCached(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
            HitResult movingobjectposition = this.world.raycast(vec3d, vec3d1, false, true);
            vec3d = Vec3d.createCached(this.x, this.y, this.z);
            vec3d1 = Vec3d.createCached(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
            if (movingobjectposition != null) {
                vec3d1 = Vec3d.createCached(movingobjectposition.pos.x, movingobjectposition.pos.y, movingobjectposition.pos.z);
            }

            Entity entity = null;
            List list = this.world.getEntities(this, this.boundingBox.stretch(this.velocityX, this.velocityY, this.velocityZ).expand(1.0, 1.0, 1.0));
            double d = 0.0;

            float f5;
            double b;
            for (int l = 0; l < list.size(); ++l) {
                Entity entity1 = (Entity) list.get(l);
                if (entity1.isCollidable() && (entity1 != this.owner || this.ticksInAir >= 5)) {
                    f5 = 0.3F;
                    Box axisalignedbb1 = entity1.boundingBox.expand(f5, f5, f5);
                    HitResult movingobjectposition1 = axisalignedbb1.raycast(vec3d, vec3d1);
                    if (movingobjectposition1 != null) {
                        b = vec3d.distanceTo(movingobjectposition1.pos);
                        if (b < d || d == 0.0) {
                            entity = entity1;
                            d = b;
                        }
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new HitResult(entity);
            }

            float f1;
            if (movingobjectposition != null) {
                if (movingobjectposition.entity != null) {
                    int attackU = 4;
                    if (!(movingobjectposition.entity instanceof EntityClayMan)) {
                        attackU = 0;
                    }

                    if (movingobjectposition.entity.damage(this.owner, attackU)) {
                        this.markDead();
                    }
                } else {
                    this.xTile = movingobjectposition.blockX;
                    this.yTile = movingobjectposition.blockY;
                    this.zTile = movingobjectposition.blockZ;
                    this.inTile = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
                    this.field_28019_h = this.world.getBlockMeta(this.xTile, this.yTile, this.zTile);
                    this.velocityX = (float) (movingobjectposition.pos.x - this.x);
                    this.velocityY = (float) (movingobjectposition.pos.y - this.y);
                    this.velocityZ = (float) (movingobjectposition.pos.z - this.z);
                    f1 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY + this.velocityZ * this.velocityZ);
                    this.x -= this.velocityX / (double) f1 * 0.05000000074505806;
                    this.y -= this.velocityY / (double) f1 * 0.05000000074505806;
                    this.z -= this.velocityZ / (double) f1 * 0.05000000074505806;
                    this.inGround = true;
                    this.arrowShake = 7;
                }
            }

            this.x += this.velocityX;
            this.y += this.velocityY;
            this.z += this.velocityZ;
            f1 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
            this.yaw = (float) (Math.atan2(this.velocityX, this.velocityZ) * 180.0 / 3.1415927410125732);

            for (this.pitch = (float) (Math.atan2(this.velocityY, f1) * 180.0 / 3.1415927410125732); this.pitch - this.prevPitch < -180.0F; this.prevPitch -= 360.0F) {
            }

            while (this.pitch - this.prevPitch >= 180.0F) {
                this.prevPitch += 360.0F;
            }

            while (this.yaw - this.prevYaw < -180.0F) {
                this.prevYaw -= 360.0F;
            }

            while (this.yaw - this.prevYaw >= 180.0F) {
                this.prevYaw += 360.0F;
            }

            this.pitch = this.prevPitch + (this.pitch - this.prevPitch) * 0.2F;
            this.yaw = this.prevYaw + (this.yaw - this.prevYaw) * 0.2F;
            float f3 = 0.99F;
            f5 = 0.03F;
            if (this.isSubmergedInWater()) {
                for (int i1 = 0; i1 < 4; ++i1) {
                    float f6 = 0.25F;
                    this.world.addParticle("bubble", this.x - this.velocityX * (double) f6, this.y - this.velocityY * (double) f6, this.z - this.velocityZ * (double) f6, this.velocityX, this.velocityY, this.velocityZ);
                }

                f3 = 0.8F;
            }

            this.velocityX *= f3;
            this.velocityY *= f3;
            this.velocityZ *= f3;
            this.velocityY -= f5;
            this.setPosition(this.x, this.y, this.z);
            if (this.ticksInGround > 0 || this.inGround) {
                this.dead = true;
            }

            if (this.dead) {
                double a = this.x + (double) (this.random.nextFloat() - this.random.nextFloat()) * 0.125;
                b = this.boundingBox.minY + 0.125 + (double) (this.random.nextFloat() - this.random.nextFloat()) * 0.25;
                double c = this.z + (double) (this.random.nextFloat() - this.random.nextFloat()) * 0.125;
                ClientUtil.addDiggingParticle(this.world, a, b, c, 0.0, 0.0, 0.0, Block.GRAVEL, 0, 0);
                this.world.playSound(this, "step.gravel", 0.6F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
            }

        }
    }

    public void writeNbt(NbtCompound nbttagcompound) {
        nbttagcompound.putShort("xTile", (short) this.xTile);
        nbttagcompound.putShort("yTile", (short) this.yTile);
        nbttagcompound.putShort("zTile", (short) this.zTile);
        nbttagcompound.putByte("inTile", (byte) this.inTile);
        nbttagcompound.putByte("inData", (byte) this.field_28019_h);
        nbttagcompound.putByte("shake", (byte) this.arrowShake);
        nbttagcompound.putByte("inGround", (byte) (this.inGround ? 1 : 0));
        nbttagcompound.putBoolean("player", this.doesArrowBelongToPlayer);
        nbttagcompound.putByte("ClayTeam", (byte) this.clayTeam);
    }

    public void readNbt(NbtCompound nbttagcompound) {
        this.xTile = nbttagcompound.getShort("xTile");
        this.yTile = nbttagcompound.getShort("yTile");
        this.zTile = nbttagcompound.getShort("zTile");
        this.inTile = nbttagcompound.getByte("inTile") & 255;
        this.field_28019_h = nbttagcompound.getByte("inData") & 255;
        this.arrowShake = nbttagcompound.getByte("shake") & 255;
        this.inGround = nbttagcompound.getByte("inGround") == 1;
        this.doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
        this.clayTeam = nbttagcompound.getByte("ClayTeam") & 255;
    }

    public void onPlayerInteraction(PlayerEntity entityplayer) {
        if (!this.world.isRemote) {
            if (this.inGround && this.doesArrowBelongToPlayer && this.arrowShake <= 0 && entityplayer.inventory.addStack(new ItemStack(Item.ARROW, 1))) {
                this.world.playSound(this, "random.pop", 0.2F, ((this.random.nextFloat() - this.random.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityplayer.sendPickup(this, 1);
                this.markDead();
            }

        }
    }

    public float getShadowRadius() {
        return 0.0F;
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(EntityListener.MOD_ID, "gravelchunk");
    }
}

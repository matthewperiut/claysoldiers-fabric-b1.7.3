package com.matthewperiut.claysoldiers.entity.behavior;

import lombok.Getter;
import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.Box;
import net.minecraft.util.maths.MathHelper;
import net.minecraft.util.maths.Vec3f;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

import static com.matthewperiut.claysoldiers.ClaySoldiersMod.MODID;
import static net.modificationstation.stationapi.api.registry.Identifier.of;

@HasTrackingParameters(trackingDistance = 160, updatePeriod = 2)
public class EntityGravelChunk extends EntityBase implements MobSpawnDataProvider {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private int field_28019_h = 0;
    private boolean inGround = false;
    public boolean doesArrowBelongToPlayer = false;
    public int arrowShake = 0;
    public Living owner;
    private int ticksInGround;
    private int ticksInAir = 0;
    public int clayTeam;
    public int entityAge;

    public EntityGravelChunk(Level world) {
        super(world);
        this.setSize(0.1F, 0.1F);
        this.renderDistanceMultiplier = 5.0D;
        this.setPosition(this.x, this.y, this.z);
    }

    public EntityGravelChunk(Level world, double d, double d1, double d2, int i) {
        super(world);
        this.setSize(0.1F, 0.1F);
        this.renderDistanceMultiplier = 5.0D;
        this.setPosition(d, d1, d2);
        this.standingEyeHeight = 0.0F;
        this.clayTeam = i;
    }

    public EntityGravelChunk(Level world, Living entityliving, int i) {
        super(world);
        this.owner = entityliving;
        this.doesArrowBelongToPlayer = entityliving instanceof PlayerBase;
        this.setSize(0.1F, 0.1F);
        this.renderDistanceMultiplier = 5.0D;
        this.setPositionAndAngles(entityliving.x, entityliving.y + (double)entityliving.getEyeHeight(), entityliving.z, entityliving.yaw, entityliving.pitch);
        this.x -= (double)(MathHelper.cos(this.yaw / 180.0F * 3.141593F) * 0.16F);
        this.y -= 0.10000000149011612D;
        this.z -= (double)(MathHelper.sin(this.yaw / 180.0F * 3.141593F) * 0.16F);
        this.setPosition(this.x, this.y, this.z);
        this.standingEyeHeight = 0.0F;
        this.velocityX = (double)(-MathHelper.sin(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
        this.velocityZ = (double)(MathHelper.cos(this.yaw / 180.0F * 3.141593F) * MathHelper.cos(this.pitch / 180.0F * 3.141593F));
        this.velocityY = (double)(-MathHelper.sin(this.pitch / 180.0F * 3.141593F));
        this.setArrowHeading(this.velocityX, this.velocityY, this.velocityZ, 1.5F, 1.0F);
        this.clayTeam = i;
    }

    protected void initDataTracker()
    {

    }

    public void setArrowHeading(double d, double d1, double d2, float f, float f1) {
        float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        d /= (double)f2;
        d1 /= (double)f2;
        d2 /= (double)f2;
        d += this.rand.nextGaussian() * 0.007499999832361937D * (double)f1;
        d1 += this.rand.nextGaussian() * 0.007499999832361937D * (double)f1;
        d2 += this.rand.nextGaussian() * 0.007499999832361937D * (double)f1;
        d *= (double)f;
        d1 *= (double)f;
        d2 *= (double)f;
        this.velocityX = d;
        this.velocityY = d1;
        this.velocityZ = d2;
        float f3 = MathHelper.sqrt(d * d + d2 * d2);
        this.prevYaw = this.yaw = (float)(Math.atan2(d, d2) * 180.0D / 3.1415927410125732D);
        this.prevPitch = this.pitch = (float)(Math.atan2(d1, (double)f3) * 180.0D / 3.1415927410125732D);
        this.ticksInGround = 0;
    }

    public void setVelocity(double d, double d1, double d2) {
        this.velocityX = d;
        this.velocityY = d1;
        this.velocityZ = d2;
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            float f = MathHelper.sqrt(d * d + d2 * d2);
            this.prevYaw = this.prevYaw = (float)(Math.atan2(d, d2) * 180.0D / 3.1415927410125732D);
            this.prevPitch = this.pitch = (float)(Math.atan2(d1, (double)f) * 180.0D / 3.1415927410125732D);
            //this.prevPitch = this.pitch; todo: investigate this
            this.prevYaw = this.yaw;
            this.setPositionAndAngles(this.x, this.y, this.z, this.yaw, this.pitch);
            this.ticksInGround = 0;
        }

    }

    @Override
    public void tick() {
        ++this.entityAge;
        if (this.entityAge > 99999) {
            this.entityAge = 0;
        }

        super.tick();
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            float f = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
            this.prevYaw = this.yaw = (float)(Math.atan2(this.velocityX, this.velocityZ) * 180.0D / 3.1415927410125732D);
            this.prevPitch = this.pitch = (float)(Math.atan2(this.velocityY, (double)f) * 180.0D / 3.1415927410125732D);
        }

        int i = this.level.getTileId(this.xTile, this.yTile, this.zTile);
        if (i > 0) {
            BlockBase.BY_ID[i].updateBoundingBox(this.level, this.xTile, this.yTile, this.zTile);

            Box axisalignedbb = BlockBase.BY_ID[i].getCollisionShape(this.level, this.xTile, this.yTile, this.zTile);
            if (axisalignedbb != null && axisalignedbb.method_88(Vec3f.from(this.x, this.y, this.z)))
            {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inGround) {
            int j = this.level.getTileId(this.xTile, this.yTile, this.zTile);
            int k = this.level.getTileMeta(this.xTile, this.yTile, this.zTile);
            if (j == this.inTile && k == this.field_28019_h) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1200) {
                    this.remove();
                }

            } else {
                this.inGround = false;
                this.velocityX *= (double)(this.rand.nextFloat() * 0.2F);
                this.velocityY *= (double)(this.rand.nextFloat() * 0.2F);
                this.velocityZ *= (double)(this.rand.nextFloat() * 0.2F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            ++this.ticksInAir;
            Vec3f vec3d = Vec3f.from(this.x, this.y, this.z);
            Vec3f vec3d1 = Vec3f.from(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
            HitResult movingobjectposition = this.level.method_162(vec3d, vec3d1, false, true);
            vec3d = Vec3f.from(this.x, this.y, this.z);
            vec3d1 = Vec3f.from(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
            if (movingobjectposition != null) {
                // field_1988 hit coordinate
                vec3d1 = Vec3f.from(movingobjectposition.field_1988.x, movingobjectposition.field_1988.y, movingobjectposition.field_1988.z);
            }

            EntityBase entity = null;
            // method_86 add Coord
            List list = this.level.getEntities(this, this.boundingBox.method_86(this.velocityX, this.velocityY, this.velocityZ).expand(1.0D, 1.0D, 1.0D));
            double d = 0.0D;

            float f5;
            double b;
            for(int l = 0; l < list.size(); ++l) {
                EntityBase entity1 = (EntityBase)list.get(l);
                // method_1356 is "can be collided with?"
                if (entity1.method_1356() && (entity1 != this.owner || this.ticksInAir >= 5)) {
                    f5 = 0.3F;
                    Box axisalignedbb1 = entity1.boundingBox.expand((double)f5, (double)f5, (double)f5);
                    HitResult movingobjectposition1 = axisalignedbb1.method_89(vec3d, vec3d1);
                    if (movingobjectposition1 != null) {
                        b = vec3d.method_1309(movingobjectposition1.field_1988).method_1300();
                        if (b < d || d == 0.0D) {
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
            // field_1989 is entity hit
            if (movingobjectposition != null) {
                if (movingobjectposition.field_1989 != null) {
                    int attackU = 4;
                    if (!(movingobjectposition.field_1989 instanceof EntityClayMan)) {
                        attackU = 0;
                    }

                    if (movingobjectposition.field_1989.damage(this.owner, attackU)) {
                        this.remove();
                    }
                } else {
                    this.xTile = movingobjectposition.x;
                    this.yTile = movingobjectposition.y;
                    this.zTile = movingobjectposition.z;
                    this.inTile = this.level.getTileId(this.xTile, this.yTile, this.zTile);
                    this.field_28019_h = this.level.getTileMeta(this.xTile, this.yTile, this.zTile);
                    this.velocityX = (double)((float)(movingobjectposition.field_1988.x - this.x));
                    this.velocityY = (double)((float)(movingobjectposition.field_1988.y - this.y));
                    this.velocityZ = (double)((float)(movingobjectposition.field_1988.z - this.z));
                    f1 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityY * this.velocityY + this.velocityZ * this.velocityZ);
                    this.x -= this.velocityX / (double)f1 * 0.05000000074505806D;
                    this.y -= this.velocityY / (double)f1 * 0.05000000074505806D;
                    this.z -= this.velocityZ / (double)f1 * 0.05000000074505806D;
                    this.inGround = true;
                    this.arrowShake = 7;
                }
            }

            this.x += this.velocityX;
            this.y += this.velocityY;
            this.z += this.velocityZ;
            f1 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
            this.yaw = (float)(Math.atan2(this.velocityX, this.velocityZ) * 180.0D / 3.1415927410125732D);

            for(this.pitch = (float)(Math.atan2(this.velocityY, (double)f1) * 180.0D / 3.1415927410125732D); this.pitch - this.prevPitch < -180.0F; this.prevPitch -= 360.0F) {
            }

            while(this.pitch - this.prevPitch >= 180.0F) {
                this.prevPitch += 360.0F;
            }

            while(this.yaw - this.prevYaw < -180.0F) {
                this.prevYaw -= 360.0F;
            }

            while(this.yaw - this.prevYaw >= 180.0F) {
                this.prevYaw += 360.0F;
            }

            this.pitch = this.prevPitch + (this.pitch - this.prevPitch) * 0.2F;
            this.yaw = this.prevYaw + (this.yaw - this.prevYaw) * 0.2F;
            float f3 = 0.99F;
            f5 = 0.03F;
            if (this.isTouchingWater()) {
                for(int i1 = 0; i1 < 4; ++i1) {
                    float f6 = 0.25F;
                    this.level.addParticle("bubble", this.x - this.velocityX * (double)f6, this.y - this.velocityY * (double)f6, this.z - this.velocityZ * (double)f6, this.velocityX, this.velocityY, this.velocityZ);
                }

                f3 = 0.8F;
            }

            this.velocityX *= (double)f3;
            this.velocityY *= (double)f3;
            this.velocityZ *= (double)f3;
            this.velocityY -= (double)f5;
            this.setPosition(this.x, this.y, this.z);
            if (this.ticksInGround > 0 || this.inGround) {
                this.removed = true;
            }

            if (this.removed) {
                double a = this.x + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                b = this.boundingBox.minY + 0.125D + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.25D;
                double c = this.z + (double)(this.rand.nextFloat() - this.rand.nextFloat()) * 0.125D;
                //ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntityDiggingFX(this.worldObj, a, b, c, 0.0D, 0.0D, 0.0D, Block.gravel, 0, 0));
                this.level.playSound(this, "step.gravel", 0.6F, 1.0F / (this.rand.nextFloat() * 0.2F + 0.9F));
            }

        }
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag nbttagcompound) {
        nbttagcompound.put("xTile", (short)this.xTile);
        nbttagcompound.put("yTile", (short)this.yTile);
        nbttagcompound.put("zTile", (short)this.zTile);
        nbttagcompound.put("inTile", (byte)this.inTile);
        nbttagcompound.put("inData", (byte)this.field_28019_h);
        nbttagcompound.put("shake", (byte)this.arrowShake);
        nbttagcompound.put("inGround", (byte)(this.inGround ? 1 : 0));
        nbttagcompound.put("player", this.doesArrowBelongToPlayer);
        nbttagcompound.put("ClayTeam", (byte)this.clayTeam);
    }

    @Override
    protected void writeCustomDataToTag(CompoundTag nbttagcompound) {
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

    @Override
    public void onPlayerCollision(PlayerBase entityplayer)
    {
        if (!this.level.isServerSide) {
            if (this.inGround && this.doesArrowBelongToPlayer && this.arrowShake <= 0 && entityplayer.inventory.addStack(new ItemInstance(ItemBase.arrow, 1))) {
                this.level.playSound(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityplayer.onItemPickup(this, 1);
                this.remove();
            }
        }
    }

    @Override
    public float getEyeHeight() {
        // get shadow size?
        return 0.0F;
    }

    @Getter
    private final Identifier handlerIdentifier = of(MODID, "gravelchunk");
}

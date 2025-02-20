package com.matthewperiut.claysoldiers.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.client.particle.BlockParticle;
import net.minecraft.client.particle.ItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ClientUtil {
    public static void addPoofParticle(World arg, double d, double e, double f, Item arg2) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            addPoofParticleClient(arg, d, e, f, arg2);
        }
    }

    public static void addDiggingParticle(World arg, double d, double e, double f, double g, double h, double i, Block arg2, int j, int k) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            addDiggingParticleClient(arg, d, e, f, g, h, i, arg2, j, k);
        }
    }

    @Environment(EnvType.CLIENT)
    private static void addParticleClient(Particle entity) {
        // todo: ((Minecraft) FabricLoader.getInstance().getGameInstance()).particleManager.addParticle(entity);
    }

    @Environment(EnvType.CLIENT)
    private static void addPoofParticleClient(World arg, double d, double e, double f, Item arg2) {
        addParticleClient(new ItemParticle(arg, d, e, f, arg2));
    }

    @Environment(EnvType.CLIENT)
    private static void addDiggingParticleClient(World arg, double d, double e, double f, double g, double h, double i, Block arg2, int j, int k) {
        addParticleClient(new BlockParticle(arg, d, e, f, g, h, i, arg2, j, k));
    }

    public static void addSlimeFX() {
        //ModLoader.getMinecraftInstance().effectRenderer.addEffect(new EntitySlimeFX
    }

    @Environment(EnvType.CLIENT)
    private static void addSlimeFXClient() {

    }

}

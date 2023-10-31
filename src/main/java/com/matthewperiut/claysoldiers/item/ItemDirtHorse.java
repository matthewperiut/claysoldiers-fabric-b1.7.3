package com.matthewperiut.claysoldiers.item;

import com.matthewperiut.claysoldiers.entity.behavior.EntityDirtHorse;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.registry.Identifier;

public class ItemDirtHorse extends ItemClayMan {
    public ItemDirtHorse(Identifier identifier) {
        super(identifier, -1);
        this.setMaxStackSize(8);
    }

    @Override
    protected void spawnEntity(World world, double x, double y, double z) {
        EntityDirtHorse ec = new EntityDirtHorse(world, x, y, z);
        world.playSound(x, y, z, "step.gravel", 0.8F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 0.9F);
        world.spawnEntity(ec);
    }
}

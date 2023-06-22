package com.matthewperiut.claysoldiers.item;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import com.matthewperiut.claysoldiers.entity.behavior.EntityDirtHorse;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class ItemDirtHorse extends ItemClayMan
{
    public ItemDirtHorse(Identifier identifier)
    {
        super(identifier, -1);
        this.setMaxStackSize(8);
    }

    @Override
    protected void spawnEntity(Level world, double x, double y, double z)
    {
        EntityDirtHorse ec = new EntityDirtHorse(world, x, y, z);
        world.spawnEntity(ec);
    }
}

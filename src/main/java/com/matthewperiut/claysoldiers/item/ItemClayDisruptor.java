package com.matthewperiut.claysoldiers.item;

import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class ItemClayDisruptor extends TemplateItemBase
{
    public ItemClayDisruptor(Identifier identifier)
    {
        super(identifier);
        this.setMaxStackSize(1);
    }
}

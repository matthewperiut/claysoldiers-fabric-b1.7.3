package com.matthewperiut.claysoldiers.item;

import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class ItemDirtHorse extends TemplateItemBase
{
    public ItemDirtHorse(Identifier identifier)
    {
        super(identifier);
        this.setMaxStackSize(8);
    }
}

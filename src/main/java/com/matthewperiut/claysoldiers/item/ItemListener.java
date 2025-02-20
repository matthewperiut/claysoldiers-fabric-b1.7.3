package com.matthewperiut.claysoldiers.item;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    public static Item
            clayDisruptor,
            greyDoll,
            redDoll,
            yellowDoll,
            greenDoll,
            blueDoll,
            orangeDoll,
            purpleDoll,
            horseDoll;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        clayDisruptor = new ItemClayDisruptor(Identifier.of(MOD_ID, "claydisruptor")).setTranslationKey(MOD_ID, "claydisruptor");

        greyDoll = new ItemClayMan(Identifier.of(MOD_ID, "claydoll"), 0).setTranslationKey(MOD_ID, "claydoll");
        redDoll = new ItemClayMan(Identifier.of(MOD_ID, "reddoll"), 1).setTranslationKey(MOD_ID, "reddoll");
        yellowDoll = new ItemClayMan(Identifier.of(MOD_ID, "yellowdoll"), 2).setTranslationKey(MOD_ID, "yellowdoll");
        greenDoll = new ItemClayMan(Identifier.of(MOD_ID, "greendoll"), 3).setTranslationKey(MOD_ID, "greendoll");
        blueDoll = new ItemClayMan(Identifier.of(MOD_ID, "bluedoll"), 4).setTranslationKey(MOD_ID, "bluedoll");
        orangeDoll = new ItemClayMan(Identifier.of(MOD_ID, "orangedoll"), 5).setTranslationKey(MOD_ID, "orangedoll");
        purpleDoll = new ItemClayMan(Identifier.of(MOD_ID, "purpledoll"), 6).setTranslationKey(MOD_ID, "purpledoll");

        horseDoll = new ItemDirtHorse(Identifier.of(MOD_ID, "horsedoll")).setTranslationKey(MOD_ID, "horsedoll");
    }
}

package com.matthewperiut.claysoldiers.entity;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import com.matthewperiut.claysoldiers.entity.behavior.EntityDirtHorse;
import com.matthewperiut.claysoldiers.entity.behavior.EntityGravelChunk;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Null;

import static net.modificationstation.stationapi.api.registry.Identifier.of;

public class EntityListener {
    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerEntities(EntityRegister event) {
        event.register(EntityClayMan.class, "claysoldier");
        event.register(EntityDirtHorse.class, "dirthorse");
        event.register(EntityGravelChunk.class, "gravelchunk");
    }

    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event) {
        Registry.register(event.registry, of(MOD_ID, "claysoldier"), EntityClayMan::new);
        Registry.register(event.registry, of(MOD_ID, "dirthorse"), EntityDirtHorse::new);
    }
}

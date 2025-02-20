package com.matthewperiut.claysoldiers.entity;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import com.matthewperiut.claysoldiers.entity.behavior.EntityDirtHorse;
import com.matthewperiut.claysoldiers.entity.behavior.EntityGravelChunk;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class EntityListener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @EventListener
    public void registerEntities(EntityRegister event) {
        event.register(EntityClayMan.class, "claysoldier");
        event.register(EntityDirtHorse.class, "dirthorse");
        event.register(EntityGravelChunk.class, "gravelchunk");
    }

    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event) {
        Registry.register(event.registry, Identifier.of(MOD_ID, "claysoldier"), EntityClayMan::new);
        Registry.register(event.registry, Identifier.of(MOD_ID, "dirthorse"), EntityDirtHorse::new);
    }
}

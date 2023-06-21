package com.matthewperiut.claysoldiers.entity;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import com.matthewperiut.claysoldiers.entity.model.ModelClayMan;
import com.matthewperiut.claysoldiers.entity.render.RenderClayMan;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Null;

import static net.modificationstation.stationapi.api.registry.Identifier.of;

public class EntityListener
{
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerEntities(EntityRegister event) {
        event.register(EntityClayMan.class, "claysoldier");
        EntityRenderDispatcher.INSTANCE.renderers.put(EntityClayMan.class, new RenderClayMan(new ModelClayMan(0.0F, 13.0F), 0.125F));
    }

    @EventListener
    public void registerMobHandlers(MobHandlerRegistryEvent event) {
        Registry.register(event.registry, of(MOD_ID, "claysoldier"), EntityClayMan::new);
    }
}

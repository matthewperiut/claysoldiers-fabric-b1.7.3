package com.periut.claysoldiers.client;

import com.periut.claysoldiers.client.model.ModelClayMan;
import com.periut.claysoldiers.client.model.ModelDirtHorse;
import com.periut.claysoldiers.client.render.RenderClayMan;
import com.periut.claysoldiers.client.render.RenderDirtHorse;
import com.periut.claysoldiers.client.render.RenderGravelChunk;
import com.periut.claysoldiers.entity.behavior.EntityClayMan;
import com.periut.claysoldiers.entity.behavior.EntityDirtHorse;
import com.periut.claysoldiers.entity.behavior.EntityGravelChunk;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;

public class EntityRendererListener {
    @EventListener
    public void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(EntityClayMan.class, new RenderClayMan(new ModelClayMan(0.0F, 13.0F), 0.125F));
        event.renderers.put(EntityDirtHorse.class, new RenderDirtHorse(new ModelDirtHorse(0.0F, 12.75F), 0.15F));
        event.renderers.put(EntityGravelChunk.class, new RenderGravelChunk());
    }
}

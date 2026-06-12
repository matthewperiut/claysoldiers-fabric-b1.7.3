package com.periut.claysoldiers.client;

import com.periut.claysoldiers.client.model.ModelClayMan;
import com.periut.claysoldiers.client.model.ModelDirtHorse;
import com.periut.claysoldiers.client.render.RenderClayMan;
import com.periut.claysoldiers.client.render.RenderDirtHorse;
import com.periut.claysoldiers.client.render.RenderGravelChunk;
import com.periut.claysoldiers.entity.behavior.EntityClayMan;
import com.periut.claysoldiers.entity.behavior.EntityDirtHorse;
import com.periut.claysoldiers.entity.behavior.EntityGravelChunk;
import com.periut.retroapi.entity.client.RetroEntityRenderers;

public class ClaySoldierEntityRenderers {
    public static void register() {
		RetroEntityRenderers.register(EntityClayMan.class,
			new RenderClayMan(new ModelClayMan(0.0F, 13.0F), 0.125F));
		RetroEntityRenderers.register(EntityDirtHorse.class,
			new RenderDirtHorse(new ModelDirtHorse(0.0F, 12.75F), 0.15F));
		RetroEntityRenderers.register(EntityGravelChunk.class,
			new RenderGravelChunk());
    }
}

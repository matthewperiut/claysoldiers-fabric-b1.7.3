package com.periut.claysoldiers.entity;

import com.periut.claysoldiers.entity.behavior.EntityClayMan;
import com.periut.claysoldiers.entity.behavior.EntityDirtHorse;
import com.periut.claysoldiers.entity.behavior.EntityGravelChunk;
import com.periut.retroapi.entity.EntityRegistration;
import com.periut.retroapi.entity.RetroEntities;
import com.periut.retroapi.entity.client.MobFactory;
import net.minecraft.entity.Entity;

public class ClaySoldierEntities {
	public static EntityRegistration CLAY_SOLDIER, DIRT_HORSE, GRAVEL_CHUNK;
    public static void registerEntities() {
		CLAY_SOLDIER = RetroEntities.register(EntityClayMan.ID, EntityClayMan.class)
			.factory((MobFactory) EntityClayMan::new);

		DIRT_HORSE = RetroEntities.register(EntityDirtHorse.ID, EntityDirtHorse.class)
			.factory((MobFactory) EntityDirtHorse::new);

		GRAVEL_CHUNK = RetroEntities.register(EntityGravelChunk.ID, EntityGravelChunk.class)
			.factory((MobFactory) EntityGravelChunk::new);
    }

    public void registerMobHandlers() {
//        Registry.register(event.registry, Identifier.of(MOD_ID, "claysoldier"), EntityClayMan::new);
//        Registry.register(event.registry, Identifier.of(MOD_ID, "dirthorse"), EntityDirtHorse::new);
    }
}

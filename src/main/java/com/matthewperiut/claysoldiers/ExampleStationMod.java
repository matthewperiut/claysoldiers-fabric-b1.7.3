package com.matthewperiut.claysoldiers;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.WalkingBase;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class ExampleStationMod {

    @Entrypoint.Logger
    public static final Logger LOGGER = Null.get();

    @Entrypoint.Instance
    public static final ExampleStationMod INSTANCE = Null.get();

    @Entrypoint.ModID
    public static final ModID MODID = Null.get();

    @EventListener
    public void init(InitEvent event)
    {
        ExampleStationMod.LOGGER.info("Is this working? " + MODID.toString());
    }
}

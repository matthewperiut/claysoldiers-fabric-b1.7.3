package com.matthewperiut.claysoldiers;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class ClaySoldiersMod {

    @Entrypoint.Logger
    public static final Logger LOGGER = Null.get();

    @Entrypoint.Instance
    public static final ClaySoldiersMod INSTANCE = Null.get();

    @Entrypoint.ModID
    public static final ModID MODID = Null.get();

    @EventListener
    public void init(InitEvent event)
    {
        ClaySoldiersMod.LOGGER.info("Is this working? " + MODID.toString());
    }
}
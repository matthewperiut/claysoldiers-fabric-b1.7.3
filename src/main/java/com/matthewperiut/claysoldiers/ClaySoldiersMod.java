package com.matthewperiut.claysoldiers;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import com.matthewperiut.claysoldiers.entity.behavior.EntityDirtHorse;
import com.matthewperiut.claysoldiers.item.ItemClayMan;
import com.matthewperiut.claysoldiers.item.ItemDirtHorse;
import com.matthewperiut.claysoldiers.optional.RetroCommandsSupport;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.modificationstation.stationapi.api.dispenser.DispenseEvent;
import net.modificationstation.stationapi.api.dispenser.ItemDispenseContext;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class ClaySoldiersMod {

    @Entrypoint.Logger
    public static Logger LOGGER = Null.get();

    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @EventListener
    public void init(InitEvent event) {
        ClaySoldiersMod.LOGGER.info("Clay soldiers loaded as: " + MOD_ID);

        if (FabricLoader.getInstance().isModLoaded("retrocommands")) {
            RetroCommandsSupport.addEntities();
        }
    }

    @EventListener
    public void dispenseArrow(DispenseEvent event) {
        ItemDispenseContext context = event.context;
        BlockPos facingPos = context.getFacingBlockPos();
        Entity entity;

        if (context.itemStack.getItem() instanceof ItemDirtHorse) {
            entity = new EntityDirtHorse(context.dispenser.world);
        }
        else if (context.itemStack.getItem() instanceof ItemClayMan ClayMan) {
            entity = new EntityClayMan(context.dispenser.world, facingPos.x, facingPos.y, facingPos.z, ClayMan.clayTeam);
        }
        else entity = null;
        if (entity != null) {
            context.shootEntity(entity);
            entity.setPosition(facingPos.x + 0.5, facingPos.y + 0.5, facingPos.z + 0.5);
            entity.setVelocityClient(0.0D, 0.0D, 0.0D);

            event.setCanceled(true);
        }
    }
}

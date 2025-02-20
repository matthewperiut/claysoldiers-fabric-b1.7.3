package com.matthewperiut.claysoldiers.optional;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import com.matthewperiut.retrocommands.api.SummonRegistry;

public class RetroCommandsSupport {
    public static void addEntities() {
        SummonRegistry.add(EntityClayMan.class, ((world, posParse, strings) -> {
            int team = Integer.parseInt(strings[5]);
            if (team >= 0 && team <= 6)
                return new EntityClayMan(world, posParse.x, posParse.y, posParse.z, team);
            else
                return null;
        }), "{team (0 through 6)}");
    }
}

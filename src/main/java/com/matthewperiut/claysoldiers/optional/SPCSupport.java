package com.matthewperiut.claysoldiers.optional;

import com.matthewperiut.claysoldiers.entity.behavior.EntityClayMan;
import com.matthewperiut.spc.api.SummonRegistry;

public class SPCSupport {
    public static void addEntities()
    {
        SummonRegistry.add(EntityClayMan.class, ((level, posParse, strings) -> {
            int team = Integer.parseInt(strings[5]);
            if (team >= 0 && team <= 6)
                return new EntityClayMan(level, team);
            else
                return null;
        }), "{team (0 through 6)}");
    }
}

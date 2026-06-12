package com.periut.claysoldiers.item;

import com.periut.retroapi.register.item.RetroItemAccess;
import net.minecraft.item.Item;

import static com.periut.claysoldiers.ClaySoldiersMod.id;

public class ClaySoldierItems {

    public static Item[] items;
    public static Item
            clayDisruptor,
            greyDoll,
            redDoll,
            yellowDoll,
            greenDoll,
            blueDoll,
            orangeDoll,
            purpleDoll,
            horseDoll;

    public static void registerItems() {
        clayDisruptor = RetroItemAccess.of(new ItemClayDisruptor(RetroItemAccess.allocateId()))
			.texture(id("disruptor"))
			.register(id("claydisruptor"));

        greyDoll = RetroItemAccess.of(new ItemClayMan(RetroItemAccess.allocateId(), 0))
			.texture(id("dollGrey"))
			.register(id("claydoll"));

        redDoll = RetroItemAccess.of(new ItemClayMan(RetroItemAccess.allocateId(), 1))
			.texture(id("dollRed"))
			.register(id("reddoll"));
		yellowDoll = RetroItemAccess.of(new ItemClayMan(RetroItemAccess.allocateId(), 2))
			.texture(id("dollYellow"))
			.register(id("yellowdoll"));
		greenDoll = RetroItemAccess.of(new ItemClayMan(RetroItemAccess.allocateId(), 3))
			.texture(id("dollGreen"))
			.register(id("greendoll"));
		blueDoll = RetroItemAccess.of(new ItemClayMan(RetroItemAccess.allocateId(), 4))
			.texture(id("dollBlue"))
			.register(id("bluedoll"));
		orangeDoll = RetroItemAccess.of(new ItemClayMan(RetroItemAccess.allocateId(), 5))
			.texture(id("dollOrange"))
			.register(id("orangedoll"));
		purpleDoll = RetroItemAccess.of(new ItemClayMan(RetroItemAccess.allocateId(), 6))
			.texture(id("dollPurple"))
			.register(id("purpledoll"));
        horseDoll = RetroItemAccess.of(new ItemDirtHorse(RetroItemAccess.allocateId()))
			.texture(id("dollHorse"))
			.register(id("horsedoll"));

        items = new Item[]{
                ClaySoldierItems.clayDisruptor,
                ClaySoldierItems.greyDoll,
                ClaySoldierItems.redDoll,
                ClaySoldierItems.yellowDoll,
                ClaySoldierItems.greenDoll,
                ClaySoldierItems.blueDoll,
                ClaySoldierItems.orangeDoll,
                ClaySoldierItems.purpleDoll,
                ClaySoldierItems.horseDoll,
        };
    }
}

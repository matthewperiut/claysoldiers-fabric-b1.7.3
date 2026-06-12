package com.periut.claysoldiers;

import com.periut.claysoldiers.entity.ClaySoldierEntities;
import com.periut.claysoldiers.item.ClaySoldierItems;
import com.periut.claysoldiers.optional.RetroCommandsSupport;
import com.periut.claysoldiers.recipe.ClaySoldierRecipes;
import net.fabricmc.loader.api.FabricLoader;
import net.ornithemc.osl.core.api.util.NamespacedIdentifier;
import net.ornithemc.osl.core.api.util.NamespacedIdentifiers;
import net.ornithemc.osl.entrypoints.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClaySoldiersMod implements ModInitializer {
	public static final String MOD_ID = "claysoldiers";
	public static final Logger LOGGER = LogManager.getLogger("Clay Soldiers");

	public static NamespacedIdentifier id(String name) {
		return NamespacedIdentifiers.from(MOD_ID, name);
	}

	@Override
	public void init() {
		ClaySoldierItems.registerItems();
		ClaySoldierRecipes.registerRecipes();
		ClaySoldierEntities.registerEntities();

		if (FabricLoader.getInstance().isModLoaded("retrocommands")) {
			RetroCommandsSupport.addEntities();
		}
	}
}

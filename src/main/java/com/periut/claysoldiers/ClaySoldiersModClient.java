package com.periut.claysoldiers;

import com.periut.claysoldiers.client.ClaySoldierEntityRenderers;
import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;

public class ClaySoldiersModClient implements ClientModInitializer {
	@Override
	public void initClient() {
		ClaySoldierEntityRenderers.register();
	}
}

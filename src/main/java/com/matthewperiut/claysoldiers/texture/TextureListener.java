package com.matthewperiut.claysoldiers.texture;

import com.matthewperiut.claysoldiers.item.ItemListener;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        ItemListener.clayDisruptor.setTexture(Identifier.of(MOD_ID, "item/disruptor"));
        ItemListener.greyDoll.setTexture(Identifier.of(MOD_ID, "item/dollGrey"));
        ItemListener.redDoll.setTexture(Identifier.of(MOD_ID, "item/dollRed"));
        ItemListener.yellowDoll.setTexture(Identifier.of(MOD_ID, "item/dollYellow"));
        ItemListener.greenDoll.setTexture(Identifier.of(MOD_ID, "item/dollGreen"));
        ItemListener.blueDoll.setTexture(Identifier.of(MOD_ID, "item/dollBlue"));
        ItemListener.orangeDoll.setTexture(Identifier.of(MOD_ID, "item/dollOrange"));
        ItemListener.purpleDoll.setTexture(Identifier.of(MOD_ID, "item/dollPurple"));
        ItemListener.horseDoll.setTexture(Identifier.of(MOD_ID, "item/dollHorse"));
    }
}

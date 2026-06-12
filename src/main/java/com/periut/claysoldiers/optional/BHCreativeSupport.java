package com.periut.claysoldiers.optional;

//import com.periut.claysoldiers.ClaySoldiersMod;
//import com.periut.claysoldiers.item.ItemListener;
//import net.mine_diver.unsafeevents.listener.EventListener;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import paulevs.bhcreative.api.CreativeTab;
//import paulevs.bhcreative.api.SimpleTab;
//import paulevs.bhcreative.registry.TabRegistryEvent;
//
//public class BHCreativeSupport {
//    public static CreativeTab tabClaySoldiers;
//
//    @EventListener
//    public void onTabInit(TabRegistryEvent event){
//        tabClaySoldiers = new SimpleTab(ClaySoldiersMod.MOD_ID.id("claydoll"), ItemListener.greyDoll);
//        event.register(tabClaySoldiers);
//        for (Item item : ItemListener.items){
//            tabClaySoldiers.addItem(new ItemStack(item, 1));
//        }
//    }
//}

package jw.examples;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import jw.external.jw_modules.events.EventBase;
import jw.fancy_fishing.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class TestEvents extends EventBase {
        //If you jumped here from Main class press CTRL + ALT + LEFT_ARROW

        //EventHandler is kind of Flag that says to Server  notify this Method when PlayerMoveEvent will be triggered
        @EventHandler
        public void onPlayerMove(PlayerMoveEvent event) {
            Main.log("Player is moving ;) " + event.getPlayer().getName());
        }

        //every event in "Bukkit" server need to have @EventHandler annotation
        @EventHandler
        public void onBlockDestroy(BlockDestroyEvent event) {

            Main.log("Block has been destoryed" + event.getBlock());
        }

        //onPluginStart and onPluginStop has    @EventHandler in the parent class
        @Override
        public void onPluginStart(PluginEnableEvent event) {
            Main.log("Plugin has been enabled ");
        }

        @Override
        public void onPluginStop(PluginDisableEvent event) {
            Main.log("Plugin has been disabled ");
        }
}

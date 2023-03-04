package io.github.hojitaro.jumboblockgenerator;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class JumboBlockGenerator extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();

        //int face = Integer.parseInt(block.getState().getData().toString().replaceAll("[^0-9]", ""));
        event.getPlayer().sendMessage("id: " + getFacingId(block));
    }

    public int getFacingId(Block block) {
        BlockData data = block.getBlockData();
        String[] dataArr = data.getAsString().split("[\\[,=\\]]");

        String face = "";
        for (int i = 0;i < dataArr.length;i++) {
            if (dataArr[i].equals("facing") || dataArr[i].equals("axis")) {
                face = dataArr[i + 1];
                break;
            }
        }

        return face.equals("north") || face.equals("z") ? 1 :
        face.equals("east") || face.equals("x") ? 2 :
        face.equals("west") ? 3 :
        face.equals("south") ? 4 :
        face.equals("down") ? 5 :
        0;
    }
}

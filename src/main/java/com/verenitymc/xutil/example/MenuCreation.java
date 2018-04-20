package com.verenitymc.xutil.example;


import com.verenitymc.xutil.item.InventoryItem;
import com.verenitymc.xutil.menu.core.Menu;
import com.verenitymc.xutil.menu.core.MenuHolder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Template for an example
 */
public class MenuCreation {

    /*
    public void createMenu(Player player)
    {
        Menu menu = new Menu(ChatColor.RED + "Example menu", 9, new MenuHolder(player)); //very rough, more to come

        InventoryItem customItem = new InventoryItem(ChatColor.RED + "Test item", new ItemStack(Material.STAINED_GLASS, 1, (short)4), Arrays.asList(""), menu)
        {
            @Override
            public void onLeftClick(Player p)
            {
                p.sendMessage("O shit u clicked");
            }
        };


        menu.setItem(4, customItem); // Set the actual custom item

        menu.openForPlayer(player); //Open it for the player

    }
    */
}

package com.verenitymc.xutil.example;

import com.verenitymc.xutil.item.PhysicalItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Mat
 *
 * Shows you how to use the physical items, using the left or right click
 * methods, remember you can override any methods inside the physical item class.
 *
 */
public class PhysicalCreation {

    private PhysicalItem item;

    public PhysicalCreation(Player p)
    {
        item = new PhysicalItem(ChatColor.RED + "Test item", new ItemStack(Material.DIAMOND_SWORD)) {
            @Override
            public void onRightClick(Player p)
            {
                p.sendMessage(ChatColor.GOLD + "Right click");
            }

            @Override
            public void onLeftClick(Player p)
            {
                p.sendMessage(ChatColor.RED + "Left click");
            }
        };

        item.giveTo(p); //Give the player the item.

    }



}

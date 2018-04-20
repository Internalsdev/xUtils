package com.verenitymc.xutil.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by Mat
 * Quick static method to create custom items without needing to do
 * InventoryItems or PhysicalItems, just a quick few methods, not a lot
 * in here, however just makes it easier (no need to repeat).
 */
public class IFactory {


    public static ItemStack setMeta(ItemStack material, String name, List<String> lore) {
        if (((material == null) || material.getType() == Material.AIR) || (name == null && lore == null))

            return null;

        ItemMeta im = material.getItemMeta();
        if (name != null)
            im.setDisplayName(name);
        if (lore != null) {
            im.setLore(lore);

            material.setItemMeta(im);
            return material;

        }
        return material;
    }


}

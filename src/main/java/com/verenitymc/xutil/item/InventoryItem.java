package com.verenitymc.xutil.item;


import com.verenitymc.xutil.menu.action.ActionClick;
import com.verenitymc.xutil.menu.core.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by mat
 * Only to be used specifically on inventory items that are non decorative, hence the easy "onClick interface etc"
 * Extends both the custom item class and implements the actionclick interface to check for right and left clicking.
 */
public abstract class InventoryItem extends CustomItem implements ActionClick {

    private Menu attachedMenu;

    public InventoryItem(String name, List<String> lore, Material material, short dv, short durability, Menu parentInventory)
    {
        super(name, material, lore, dv, durability);
        this.attachedMenu = parentInventory;
    }


    /***
     * Main Constructor to use for ease I guess, apart from the one underneath.
     * All the arguments are mainly from the customitem class.
     * @param parentInventory - The parent inventory, used for identification purposes.
     */
    public InventoryItem(String name, ItemStack item, List<String> lores, Menu parentInventory)
    {
        super(name, item.getType(), lores, item.getData().getData(), item.getDurability());
        this.attachedMenu = parentInventory;
    }
    
    public InventoryItem(ItemStack item, Menu parentInventory)
    {
        super(item);
        this.attachedMenu = parentInventory;
    }


    public Menu getAttachedMenu()
    {
        return attachedMenu;
    }


    @Override
    public void onRightClick(Player p)
    {

    }

    @Override
    public void onLeftClick(Player p)
    {

    }
}

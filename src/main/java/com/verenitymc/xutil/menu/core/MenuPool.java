package com.verenitymc.xutil.menu.core;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mat
 * Being
 */
public class MenuPool {

    private List<Menu> allMenus; //shall hold all menus

    public MenuPool()
    {
        this.allMenus = new ArrayList<>();
    }

    public List<Menu> getPool()
    {
        return allMenus;
    }

    public Menu getMenu(Menu menu)
    {
        return getPool().stream().filter(foundMenu -> menu.equals(foundMenu)).findFirst().orElse(null);
    }


    public void addMenu(Menu menu)
    {
        getPool().add(menu);
    }

    public void removeMenu(Menu menu)
    {
        getPool().remove(menu);
    }


    /***
     * Checks if a menu matches when the inventory is clicked, needs it in order to have the inventory item trigger it's proper function.
     * Uses checks of an inventory holder as well as the title of the inventory, could possibly be modified in a different manner though.
     ** @param menu - Target menu inventory
     * @return If menu matches
     */

    public boolean menuMatches(Inventory menu)
    {
        return getPool().stream().anyMatch(foundMenu -> foundMenu.inventoryMatches(menu));
    }


    public Menu getMenu(Inventory inventory)
    {
        for (Menu menu : getPool()) {
        	if (menu.getRawInventory().getTitle().equalsIgnoreCase(inventory.getTitle()) && menu.getRawInventory().getSize() == inventory.getSize()) {
        		return menu;
        	}
        }
        
        return null;
    }





}

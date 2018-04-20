package com.verenitymc.xutil.listener;


import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.menu.core.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by matty on 09/12/2017.
 */
public class InventoryCloseListener implements Listener {


    //Check if viewers are equal to one! might be universal menu.

    @EventHandler
    public void onClose(InventoryCloseEvent e)
    {
        Inventory inventory = e.getInventory();
         if(inventory.getViewers().size()==1)
         {
            Menu menu = Core.getMenuPool().getMenu(inventory);
            if(menu!=null)
            {
                menu.removeMenu();
            }
         }
    }

}

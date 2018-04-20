package com.verenitymc.xutil.listener;


import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.item.InventoryItem;
import com.verenitymc.xutil.menu.core.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by matty on 09/12/2017.
 */
public class InventoryClickListener implements Listener {

	@EventHandler
	private void onClick(InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		if (item == null || (item.getType() == Material.AIR) || (!item.hasItemMeta()))
			return;
		Player p = (Player) e.getWhoClicked();
		Inventory inventory = e.getInventory();

		Menu menu = Core.getMenuPool().getMenu(inventory);
		if (menu != null) {
			boolean inventoryItemAt = menu.inventoryItemAt(e.getSlot());
			if (inventoryItemAt) {
				e.setCancelled(true);
				InventoryItem foundItem = menu.getInventoryItems().get(String.valueOf(e.getSlot()));
				//Get the click type for safety and to prevent exploitation.

				switch (e.getClick())
				{
					case LEFT:
						foundItem.onLeftClick(p);
						break;
					case RIGHT:
						foundItem.onRightClick(p);
				}


			}
		}
	}

}

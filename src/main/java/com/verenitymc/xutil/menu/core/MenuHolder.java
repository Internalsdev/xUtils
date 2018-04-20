package com.verenitymc.xutil.menu.core;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

/**
 * Created by Mat
 * Acts as a parent inventory holder base for all inventories.
 */
public class MenuHolder implements InventoryHolder {

    private UUID openerUUID;

    public MenuHolder(Player opener)
    {
        this.openerUUID = opener.getUniqueId();
    }
    @Override
    public Inventory getInventory()
    {
        return null;
    }

    public UUID getOpenerUUID()
    {
        return this.openerUUID;
    }
}

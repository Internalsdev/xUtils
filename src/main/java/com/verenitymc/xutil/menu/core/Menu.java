package com.verenitymc.xutil.menu.core;


import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.item.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

/**
 * Created by matty on 04/12/2017.
 */
public class Menu {

    private String name;
    private int size;
    private InventoryHolder holder;
    private Inventory inventory;
    private HashMap<String, InventoryItem> inventoryContents; //Always refer to the slot as a string, Java doesn't like to package them up correctly in hashmaps for some reason

    private int pages; //for later

    /***
     *
     * @param name - Title of inventory
     * @param size - must be multiple of 9
     * @param holder - I'd advise using the "MenuHolder" if null then it will use the menu holder
     */
    public Menu(String name, int size, InventoryHolder holder)
    {
        this.name = name;
        this.size = size;
        this.holder = holder;
        this.inventoryContents = new HashMap<>();
        this.inventory = Bukkit.createInventory(holder, size, name);
        Core.getMenuPool().addMenu(this);
    }


    /***
     * Second constructor if you dont want to specify an inventoryholder.
     * @param name - Title of inventory
     * @param size - Size of menu
     */

    public Menu (String name, int size)
    {
        this.name = name;
        this.size = size;
        this.inventory = Bukkit.createInventory(null, size, name);
        this.holder = null;
        this.inventoryContents = new HashMap<>();

        if(getRawInventory()==null) {
            System.out.println("Inventory is null");
        }
        Core.getMenuPool().addMenu(this);
    }



    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return size;
    }

    public InventoryHolder getHolder()
    {
        return holder;
    }

    /***
     * Open the inventory for the player
     * @param p - player to open for
     */

    public void openForPlayer(Player p)
    {
        p.openInventory(getRawInventory());
    }


    /***
     * Update for all viewers of the menu.
     */
    public void update()
    {
        getRawInventory().getViewers().forEach(human -> {
            Player p = (Player) human;
            p.updateInventory();
        });
    }


    /***
     * recommend not to use for setting items etc, only raw inventory
     * data, DON'T GET THIS UNLESS YOU REALLY HAVE TO, THIS LIBRARY SHOULD DO MOST OF THE WORK FOR YOU.
     * @return inventory object menu is associated with.
     */

    public Inventory getRawInventory()
    {
        return inventory;
    }


    /***
     * Basically for checking in the listener in the inventory item class
     * @param slot - slot to check at
     * @return if there is an "InventoryItem" at that loction, uses the secondary inventoryitem hashmap
     */
    public boolean inventoryItemAt(int slot)
    {
        return getInventoryItems().get(String.valueOf(slot)) != null;
    }


    /***
     * Set item in the inventory that HAS A FUNCTION WHEN CLICKED
     * @param slot - Slot of where to place item
     * @param item - InventoryItem if not for decoration (block has a function when clicked, or possibly hovered over, idk)
     */

    public void setItem(int slot, InventoryItem item)
    {
        if(getRawInventory() == null){System.out.println("Inventory is null");}
        if(getRawInventory().getItem(slot)!=null ||  (getRawInventory().getItem(slot).getType() != Material.AIR)){
            getInventoryItems().remove("" + slot); //remove from tracking hashmap
        } 
        
        getRawInventory().setItem(slot, item.getItemStack());
        inventoryContents.put(String.valueOf(slot), item);
        update();
    }


    /***
     * add item to the inventory that HAS A FUNCTION WHEN CLICKED
     * @param item - InventoryItem if not for decoration (block has a function when clicked, or possibly hovered over, idk)
     */

    public void addItem(InventoryItem item)
    {
    	int slot = firstEmpty();
    	getRawInventory().setItem(slot, item.getItemStack());
    	inventoryContents.put(String.valueOf(slot), item);
        update();
    }
    
    public void removeItem(ItemStack item) {
    	ItemStack[] inventory = getRawInventory().getContents();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == item) {
            	getRawInventory().setItem(i, null);
            	inventoryContents.remove(String.valueOf(i));
            }
        }
    }


    /***
     * The specific hashmap for "InventoryItem" class items, not standard items, just so we can easily locate them
     * @return Hashmap of specific items
     */

    public HashMap<String, InventoryItem> getInventoryItems()
    {
        return inventoryContents;
    }



    /**
     * Used for opening up a new inventory quickly if you have a menu stored, requires a target.
     * @param toOpen - Menu to open
     * @param p - Player to open the menu for
     */

    public void openAnotherMenu(Menu toOpen, Player p)
    {
        p.closeInventory(); //Don't need to remove from pool, the close listener removes it
        toOpen.openForPlayer(p);
    }



    /***
     * Does the main check to get the actual menu, THIS IS VERY IMPORTANT THAT IT ACTUALLY WORKS.
     * Checks holder, the title and also the viewers within the inventory.
     * @param inventory - inventory you're checking
     * @return
     */

    public boolean inventoryMatches(Inventory inventory)
    {
        return holderMatches(inventory.getHolder()) && titleMatches(inventory.getTitle()) && viewersMatches(inventory.getViewers());
    }


    /*
     * Below are basically the methods for checking if a specific inventory is of a menu instance, could have just extended an inventory but
     * I feel this method gives more flexibility
     */

    protected boolean holderMatches(InventoryHolder holder)
    {
        return holder == null && getHolder()==null ? true : holder==null && getHolder()!=null ? false : holder.equals(getHolder());
    }

    protected boolean titleMatches(String title)
    {
        return getRawInventory().getTitle().equalsIgnoreCase(title);
    }

    protected boolean viewersMatches(List<HumanEntity> viewers)
    {
        return viewers.containsAll(getRawInventory().getViewers());
    }

    /*
      End checks
     */


    public void removeMenu()
    {
        Core.getMenuPool().removeMenu(this);
        inventoryContents = null;
    }
    
    public int firstEmpty() {
        ItemStack[] inventory = getRawInventory().getContents();
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                return i;
            }
        }
        return -1;
    }

}

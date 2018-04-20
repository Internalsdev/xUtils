package com.verenitymc.xutil.item;

import com.verenitymc.xutil.menu.action.ActionClick;
import com.verenitymc.xutil.util.CommonUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Mat
 * Physical class for an item, can be used for specific per player items.
 * Easy right and left click usage.
 * THERE WILL BE A SINGULAR . on the lore if it's a physical item
 * Essentially each addition should be static, it's just going to get the holder and act on it
 */
public abstract class PhysicalItem extends CustomItem implements ActionClick {

    private static HashSet<PhysicalItem> allItems = new HashSet<>(); //Stop duplicate additions using a hashset, speeds up checks.
    public static HashSet<PhysicalItem> getAllItems(){return allItems;}


    public PhysicalItem(String name, List<String> lore, Material material, short dv, short durability)
    {
        super(name, material, lore, dv, durability);
        if(!getAllItems().contains(this)) { //double check ;)
            getAllItems().add(this);
        }
    }

    public PhysicalItem(String name, ItemStack itemStack)
    {
        super(name, itemStack.getType(), itemStack.getItemMeta().getLore(), itemStack.getData().getData(), itemStack.getDurability());
        if(!getAllItems().contains(this)) {
            getAllItems().add(this);
        }
    }

    public void giveTo(Player p)
    {
        boolean canAdd = p.getInventory().firstEmpty() != -1;
        if (canAdd) {
            p.getInventory().addItem(getItemStack());
            p.updateInventory();
        }
    }




    @Override
    public void onRightClick(Player p)
    {

    }

    @Override
    public void onLeftClick(Player p)
    {

    }


    public static PhysicalItem getPhysicalItem(ItemStack item)
    {
        if(item==null || !item.hasItemMeta() || !item.getItemMeta().hasLore())return null;
        return getAllItems().stream().filter(physicalItem -> CommonUtil.itemMatchesWithMeta(item, physicalItem.getItemStack())).findFirst().orElse(null);
    }

}

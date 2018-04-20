package com.verenitymc.xutil.listener;

import com.verenitymc.xutil.item.PhysicalItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


/**
 * Created by Mat
 * Used for handling the left and right clicking of physical items
 */
public class PlayerInteractListener implements Listener {


    @EventHandler
    public void  onPhysicalInteract(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        ItemStack inHand = e.getItem();
        if(inHand!=null || (inHand.getType() != Material.AIR))
        {
            PhysicalItem physicalItem = PhysicalItem.getPhysicalItem(inHand);
            if(physicalItem != null)
            {
                Action action = e.getAction();
                if(action == Action.RIGHT_CLICK_AIR || (action == Action.RIGHT_CLICK_BLOCK)) //Decipher action, might be made a bit more specific
                {
                    physicalItem.onRightClick(p);
                }
                else if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)
                {
                    physicalItem.onLeftClick(p);
                }
            }
        }
    }


}

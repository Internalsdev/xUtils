package com.verenitymc.xutil.menu.action;

import org.bukkit.entity.Player;

/**
 * Created by Mat
 * Click action interface handler.
 * Been updated to have different actions for different click types.
 */
public interface ActionClick {

    //void onClick(); // for general click action, however player data can be gotten from the menu anyways when accessing this so I'll comment it out for the time being

    void onRightClick(Player p); //For when you want to act to a specific player on a right click.

    void onLeftClick(Player p); //For when you wnat to act on the left click.


}

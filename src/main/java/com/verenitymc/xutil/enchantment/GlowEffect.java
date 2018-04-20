package com.verenitymc.xutil.enchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Mat
 * The glow effect enchantment, no lore or anything, however WILL count as
 * a lore line, it will just be empty, can't figure out how to combat this though.
 *
 * ENCHANTMENT ID IS 70, DON'T USE AGAIN.
 */
public class GlowEffect extends Enchantment {


    public GlowEffect( int id )
    {
        super(id);
    }

    @Override
    public boolean canEnchantItem(ItemStack item)
    {
        return true; //Let it be added to everything.
    }

    @Override
    public boolean conflictsWith(Enchantment other)
    {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget()
    {
        return null;
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public String getName()
    {
        return "Glow";
    }

    @Override
    public int getStartLevel()
    {
        return 1;
    }



}

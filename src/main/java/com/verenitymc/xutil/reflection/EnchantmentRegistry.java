package com.verenitymc.xutil.reflection;

import com.verenitymc.xutil.Core;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Mat
 * Two static methods for registering the normal "glow"
 */
public class EnchantmentRegistry {


    /**
     * Register the glow effect, got a feeling we'll need randomly glowing effects
     */
    public static void registerGlow()
    {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            } //shouldn't trigger
            try {
                Enchantment.registerEnchantment(Core.getGlowEffect());
            } catch (IllegalArgumentException e) {//if this is thrown it means the id is already taken, IT HAS BEEN SET AS 70, DON'T USE AGAIN}}

            }
        } catch (Exception e){e.printStackTrace();}
    }


    public static void unregisterGlow()
    {
        try {
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);

            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);

            if(byId.containsKey(70)) //REMEMBER THIS GOD DAMN ID
                byId.remove(70);

            if(byName.containsKey(Core.getGlowEffect().getName()))
                byName.remove(Core.getGlowEffect().getName());
        } catch (Exception ignored) { }
    }


}

package com.verenitymc.xutil.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.verenitymc.xutil.Core;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mat (initially)
 * Put any methods you might find that you use a lot / might
 * have to use a lot on here, this class was initially made
 * to stop the repetition of code.
 */
public class CommonUtil {


    /***
     * Check if two items match, they must have itemMeta to be compared
     * @param item
     * @param toCompare
     * @return
     */
    public static boolean itemMatchesWithMeta(ItemStack item, ItemStack toCompare)
    {
        if(item == null || toCompare == null) return false;
        if(item.getType() == toCompare.getType()) //Check for same material
        {
            if(item.getData().getData() == toCompare.getData().getData()) //check data is the same
            {
                ItemMeta itemMeta = item.getItemMeta();
                ItemMeta toCompareMeta = toCompare.getItemMeta();
                if(itemMeta != null && (toCompareMeta != null))
                {
                    if(itemMeta.hasDisplayName() && (toCompareMeta.hasDisplayName()))
                    {
                        return itemMeta.getDisplayName().equalsIgnoreCase(toCompareMeta.getDisplayName());
                    }
                }
            }
        }
        return false;
    }


    /**
     * Copy word folder
     * @param source - File to copy
     * @param target - Target directory to copy to.
     */

    public static void copyWorld(File source, File target){
        try {
            ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
            if(!ignore.contains(source.getName())) {
                if(source.isDirectory()) {
                    if(!target.exists())
                        target.mkdirs();
                    String files[] = source.list();
                    for (String file : files) {
                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);
                        copyWorld(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /***
     * Delete the world from path.
     * @param path - The path to remove from.
     * @return - null.
     */

    public static boolean deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
    }


    /*
        Unloading and loading the worlds.
     */
    public static boolean unloadWorld(String worldName) {
        if (Bukkit.getWorld(worldName) != null) {
            Bukkit.getServer().unloadWorld(Bukkit.getWorld(worldName), true);
            return true;
        }
        return false;
    }
    public static World loadWorld(String worldName) {
        WorldCreator worldCreater = new WorldCreator(worldName);
        worldCreater.createWorld();
        return Bukkit.getWorld(worldName);
    }


    /**
     * Easy method to just send actionbar message (Accepts colours too)
     * @param p - Player to send to
     * @param msg - The message
     */
    public static void sendActionBar(Player p, String msg) {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte)2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
    }


    public static void sendToServer(Player p, String serverName)
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("connect");
        out.writeUTF(serverName);
        p.sendPluginMessage(Core.getPlugin(), "BungeeCord", out.toByteArray());
    }


}

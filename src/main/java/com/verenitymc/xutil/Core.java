package com.verenitymc.xutil;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.verenitymc.xutil.command.TestCommand;
import com.verenitymc.xutil.enchantment.GlowEffect;
import com.verenitymc.xutil.io.FileManager;
import com.verenitymc.xutil.listener.*;
import com.verenitymc.xutil.menu.core.MenuPool;
import com.verenitymc.xutil.minigame.Minigame;
import com.verenitymc.xutil.network.ServerData;
import com.verenitymc.xutil.reflection.EnchantmentRegistry;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Created by Mat on 16/12/2017.
 * Main class
 *
 * This whole library does not just make easy menus, but also as an easy framework for custom items
 * And the easily handling of events.
 *
 * Pretty much everything is documented, there's also an example usage in com.verenitymc.xutil.example so you can see what it can do.
 * More features can be added but at the moment this is doing well, was thinking of pages etc.
 *
 */
public class Core extends JavaPlugin implements PluginMessageListener {

    public static void log(String msg){ System.out.println("[xUtils] > " + msg); }

    private static Core core;

    public static Core getPlugin()
    {
        return core;
    }

    private Listener[] listenerList = {new InventoryClickListener(), new InventoryCloseListener(), new PlayerInteractListener(),
    new PlayerJoinListener(), new PlayerQuitListener(), new LobbyActionListener()};

    private static MenuPool menuPool;
    private static Enchantment glowEffect;
    private static FileManager fileManager;
    private static ServerData serverData;
    private static Minigame minigame;

    public void onEnable()
    {
        core = this;
        init();
    }

    public void onDisable()
    {
        EnchantmentRegistry.unregisterGlow();

    }


    private void init()
    {
        serverData = new ServerData();
        registerListeners();
        registerChannels();
        loadFiles();
        registerCommands();
        registerGlowEffect();
        menuPool = new MenuPool();
    }

    private void loadFiles()
    {
        fileManager = new FileManager();
    }

    private void registerCommands()
    {
        this.getCommand("test").setExecutor(new TestCommand());
    }

    private void registerListeners()
    {
        for(Listener listener : listenerList)
        {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }


    private void registerChannels()
    {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }

    private void registerGlowEffect()
    {
        glowEffect = new GlowEffect(70); //ID is 70, THIS SHOULD NOT BE RE REGISTERED AGAIN
        EnchantmentRegistry.registerGlow(); //Add it to the enchantment nms fields so it can be used.
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message)
    {
        if(!(channel.equals("BungeeCord"))){return;}
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if(subchannel.equalsIgnoreCase("connect")){

        }

    }

    /**
     * The pool in which we'll be holding all the menus
     * @return
     */
    public static MenuPool getMenuPool()
    {
        return menuPool;
    }


    /**
     * The glow effect enchantment
     * @return the glow effect enchantment
     */
    public static Enchantment getGlowEffect()
    {
        return glowEffect;
    }

    /**
     * The File manager, holds common methods to get files categorised as XFile.
     * @return The global file manager.
     */
    public static FileManager getFileManager(){return fileManager;}

    /**
     * Get the server data object.
     * @return ServerData for this server.
     */
    public static ServerData getServerData()
    {
        return serverData;
    }

    /**
     * Get the designated minigame for the plugin.
     * @return The minigame.
     */
    public static Minigame getMinigame() { return minigame; }

    public static void setMinigame(Minigame game){ minigame = game; }

}

package com.verenitymc.xutil.network;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.minigame.enums.GameState;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Mat
 *
 * Server data class, used to get data about the server, this can be used for registering
 * Server data on to databases or sending solid data about the server over the network.
 *
 */
public class ServerData {

    private String ip;
    private String port;

    private GameState gameState;


    public ServerData()
    { try {
        this.ip = InetAddress.getLocalHost().getHostAddress().toString();
        this.port = Core.getPlugin().getServer().getPort()+"";
        this.gameState = GameState.STARTING;
    }catch(UnknownHostException e){
        Core.log("COULD NOT FIND IP FOR THE SERVER");
        e.printStackTrace();}

    }

    public String getIP()
    {
        return this.ip;
    }

    public String getPort()
    {
        return this.port;
    }

    public String getDatabaseToken()
    {
        return getIP() + ":" + getPort();
    } //The token to serialize the data of the server.

    public String getMOTD()
    {
        return Core.getPlugin().getServer().getMotd();
    }



    public void setGameState(GameState state)
    {
        this.gameState = state;
    }

    public void setGameStateSync(GameState state){
        this.gameState = state;
    }

    public GameState getGameState()
    {
        return this.gameState;
    }




}

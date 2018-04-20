package com.verenitymc.xutil.minigame;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.minigame.enums.GameState;
import com.verenitymc.xutil.minigame.lobby.Lobby;
import com.verenitymc.xutil.minigame.map.Map;
import com.verenitymc.xutil.minigame.team.Team;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mat
 * The base Minigame class.
 * Will be used to handle the whole minigame.
 */
public class Minigame
{

    private Map map; //The current game map

    private MinigameSettings minigameSettings;

    private List<Team> teams;

    private GameState gameState;

    private Lobby lobby;

    public Minigame()
    {
        minigameSettings = new MinigameSettings(this);
        this.teams = new ArrayList<>();
        gameState = GameState.STARTING;
        this.lobby = new Lobby(this);
        Core.setMinigame(this);
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState)
    {
        this.gameState = gameState;
    }

    public List<Team> getTeams()
    {
        return teams;
    }

    public MinigameSettings getConfiguration()
    {
        return minigameSettings;
    }


    public void setMap(Map map){this.map = map;}

    public Map getMap()
    {
        return this.map;
    }

    /**
     * Get the lobby of the minigame, needs to be given a location!
     * @return
     */
    public Lobby getLobby() {return this.lobby;}


}

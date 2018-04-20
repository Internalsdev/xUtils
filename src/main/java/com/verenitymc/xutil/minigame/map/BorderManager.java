package com.verenitymc.xutil.minigame.map;

/**
 * Created by Mat
 *
 * Acts as a class to manipulate and shrink the size of the worldborder.
 *
 * there can be one for different worlds.
 *
 * Rememeber that the center of the border HAS TO BE SET, if there is no set centre
 * then the border will not shrink!
 *
 * Might add some stuff for circular borders etc.
 *
 * This will be especially useful for the
 *
 */
public class BorderManager {

    private Map map;

    public BorderManager(Map map)
    {
        this.map = map;
    }

    public Map geMap()
    {
        return this.map;
    }



}

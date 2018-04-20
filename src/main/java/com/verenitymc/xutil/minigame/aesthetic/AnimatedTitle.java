package com.verenitymc.xutil.minigame.aesthetic;

import com.verenitymc.xutil.Core;
import com.verenitymc.xutil.util.Title;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Mat
 *
 * A class wrapper to send a player an animated title message.
 */
public class AnimatedTitle {


    int count;

    private Player p;

    private String title;
    private String subtitle;

    private int iterations;
    private int lettersPerIterationOnSubtitle;

    private ChatColor titleColour;
    private ChatColor subtitleColour;

    public AnimatedTitle(Player p, String title, String subtitle, ChatColor titleColour, ChatColor subtitleColour)
    {
        count = 0;
        this.p = p;
        this.title = title;
        this.subtitle = subtitle;

        this.titleColour = titleColour;
        this.subtitleColour = subtitleColour;

        iterations = title.length();

        lettersPerIterationOnSubtitle = subtitle.length();

    }

    public void send() {

        runAnimatedTitle();

    }

    void runAnimatedTitle()
    {

        new BukkitRunnable()
        {
            public void run()
            {
                Title t = new Title();
                if(count>=(iterations+1))
                {
                    t.setStayTime(20*5);
                    t.setFadeInTime(0);
                    t.setTitle(titleColour + title);
                    t.setSubtitle(subtitleColour + subtitle);
                    cancel();
                }
                else
                {
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0 ; i < count ; i++)
                    {
                        sb.append(title.charAt(i));
                    }
                    String currentMessage = sb.toString();

                    int index = (count+1) * lettersPerIterationOnSubtitle;

                    if(index<=iterations)
                    {
                        String currentRankName = subtitle.substring(0, index);
                        t.setStayTime(3);
                        t.setFadeInTime(0);
                        t.setFadeOutTime(0);
                        t.setTitle(titleColour + currentMessage);
                        t.setSubtitle(subtitleColour + currentRankName);
                        t.send(p);
                    }
                    else
                    {
                        t.setStayTime(3);
                        t.setFadeInTime(0);
                        t.setFadeOutTime(3);
                        t.setTitle(titleColour + currentMessage);
                        t.setSubtitle(subtitleColour + subtitle);
                        t.send(p);
                    }

                    count++;
                }

            }
        }.runTaskTimer(Core.getPlugin(), 5L, 5L);
    }

}

package com.verenitymc.xutil.minigame.aesthetic;

import com.verenitymc.xutil.Core;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitRunnable;
import particleeffect.ParticleEffect;

import java.util.List;

/**
 * Created by Mat
 *
 * Create a particle trail while a projectile is in the air
 */
public class ProjectileTrail {


    private Projectile projectile;
    private List<Player> viewers;
    private ParticleEffect effect;
    private int count;

    public ProjectileTrail(Projectile projectile, ParticleEffect effect, List<Player> viewers)
    {
        this.projectile = projectile;
        this.effect = effect;
        this.viewers = viewers;
        this.count = 0;
        start();
    }

    private void start()
    {
        new BukkitRunnable()
        {
            public void run()
            {
                if(!projectile.isOnGround() && (!projectile.isDead() && (count<30 && (projectile!=null))))
                {
                    effect.display(0f, 0f, 0f, 0f, 2, projectile.getLocation(), viewers);
                    count++;
                } else {
                    count=0;
                    projectile = null;
                    viewers = null;
                    cancel();
                }
            }
        }.runTaskTimer(Core.getPlugin(), 0L, 1L);
    }



}

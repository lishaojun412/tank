package com.mashibing.tank;

import static com.mashibing.tank.Bullet.WIDTH;
import static com.mashibing.tank.Tank.HEIGHT;

public class FourFireStrategy implements FireStrategy {



    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        for (Dir dir : Dir.values()) {
            tank.tankFrame.bullets.add(new Bullet(bX, bY, dir, tank.SPEED * 2, tank.getGroup(), tank.tankFrame));
        }

        if (tank.getGroup() == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();

    }

}

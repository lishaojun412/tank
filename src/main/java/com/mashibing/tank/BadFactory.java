package com.mashibing.tank;

public class BadFactory extends AbstractFactory {


    @Override
    public Tank createTank(TankFrame tankFrame) {


        String initBadTank = PropertyMgr.getProperty("initBadTanks");

        for (int i = 0; i < Integer.valueOf(initBadTank); i++) {
            tankFrame.tanks.add(new Tank(50 + 80 * i, 300, Dir.DOWN, 5, Group.BAD, tankFrame));
        }

        return null;
    }

    @Override
    public void createBullet(Tank tank) {

        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tank.tankFrame.bullets.add(new Bullet(bX, bY, tank.dir, tank.SPEED * 2, tank.getGroup(), tank.tankFrame));

        if (tank.getGroup() == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();

    }
}

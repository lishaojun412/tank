package com.mashibing.tank;

public abstract class AbstractFactory {

    public abstract Tank createTank(TankFrame tankFrame);

    public abstract void createBullet(Tank tank);

}

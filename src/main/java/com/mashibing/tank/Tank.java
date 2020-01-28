package com.mashibing.tank;

import lombok.Data;

import java.awt.*;
import java.util.Random;

@Data
public class Tank {

    public static int WIDTH = ResourceMgr.badTankU.getWidth();
    public static int HEIGHT = ResourceMgr.badTankU.getHeight();
    public boolean live = true;
    TankFrame tankFrame;

    private int x, y;

    Dir dir;

    final int SPEED;

    private boolean moving = true;

    private Group group;

    FireStrategy fs;

    Random random = new Random();

    public Tank(int x, int y, Dir dir, int SPEED, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.SPEED = SPEED;
        this.tankFrame = tankFrame;
        this.group = group;

        if (this.group == Group.GOOD) {
            fs = new FourFireStrategy();
        } else {
            fs = new DefaultFireStrategy();
        }
    }


    public void paint(Graphics g) {

        if (!live) {
            tankFrame.tanks.remove(this);
            return;
        }

        g.setColor(Color.GREEN);

        switch (dir) {
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
/*            case LU:
                break;
            case LD:
                break;
            case RU:
                break;
            case RD:
                break;*/
            default:
                break;
        }

        moving();
    }

    private void moving() {


        if (moving) {
            switch (dir) {
/*                case LU:
                    x -= SPEED;
                    y -= SPEED;
                    break;
                case LD:
                    x -= SPEED;
                    y += SPEED;
                    break;
                case RU:
                    x += SPEED;
                    y -= SPEED;
                    break;
                case RD:
                    x += SPEED;
                    y += SPEED;
                    break;*/
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                default:
                    break;
            }
        }


        if (random.nextInt(10) > 8 && this.getGroup().equals(Group.BAD)) {
            this.fire();
        }

        if (this.getGroup().equals(Group.BAD) && random.nextInt(100) > 95) {
            randomDir();
        }

        boundsCheck();
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;


    }

    AbstractFactory goodFactory = new GoodFactory();
    AbstractFactory badFactory = new BadFactory();

    public void fire() {
        if (this.group == Group.GOOD)
            goodFactory.createBullet(this);
        if (this.group == Group.BAD)
            badFactory.createBullet(this);
//        fs.fire(this);
    }
}

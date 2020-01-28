package com.mashibing.tank;

import java.awt.*;

public class Bullet {

    public static int WIDTH = ResourceMgr.bulletU.getWidth();
    public static int HEIGHT = ResourceMgr.bulletU.getHeight();

    private int x, y;

    private Dir dir;

    private final int SPEED;

    private boolean live = true;

    private TankFrame tankFrame;

    private Group group;


    public Bullet(int x, int y, Dir dir, int SPEED, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.SPEED = SPEED;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getSPEED() {
        return SPEED;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {

        switch (dir) {
/*            case LU:
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
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                y += SPEED;
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                x -= SPEED;
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                x += SPEED;
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            default:
                break;
        }

        g.setColor(Color.yellow);
//        g.fillOval(x, y, 10, 10);

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.live = false;
            tankFrame.bullets.remove(this);
        }

    }

    public void collideWith(Tank tank) {

        if (this.group == tank.getGroup()) return;

        Rectangle rectangle1 = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

        if (rectangle1.intersects(rectangle2)) {
            this.live = false;
            tank.live = false;
            int eX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

            tankFrame.explodes.add(new Explode(eX, eY, tankFrame));

            new Thread(() -> new Audio("audio/explode.wav").play()).start();
        }

    }
}

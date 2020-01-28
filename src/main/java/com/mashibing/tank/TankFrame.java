package com.mashibing.tank;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    AbstractFactory goodFactory = new GoodFactory();
    static final int GAME_WIDTH = 1080;
    static final int GAME_HEIGHT = 960;
    private Tank myTank = goodFactory.createTank(this);

    List<Tank> tanks = new ArrayList<>();

    List<Bullet> bullets = new ArrayList<>();

    List<Explode> explodes = new ArrayList<>();

//    Bullet bullet = new Bullet(20, 20, Dir.DOWN, 10);

    public TankFrame() throws HeadlessException {

        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("myTank war");
        setVisible(true);

        this.addKeyListener(new TankKeyAdapter());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 循环调用
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
        g.setColor(c);

        myTank.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

    }

    /**
     * 持续监听
     */
    class TankKeyAdapter extends KeyAdapter {

        boolean bU = false;
        boolean bD = false;
        boolean bL = false;
        boolean bR = false;

        @Override
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();

            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                default:
                    break;

            }

//            new Thread(()->new Audio("audio/tank_move.wav").play()).start();

            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();

            switch (keyCode) {
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }


        void setMainTankDir() {
            if (!bU && !bD && !bL && !bR) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);

                if (bU) {
                    myTank.setDir(Dir.UP);
                }
                if (bD) {
                    myTank.setDir(Dir.DOWN);
                }
                if (bL) {
                    myTank.setDir(Dir.LEFT);
                }
                if (bR) {
                    myTank.setDir(Dir.RIGHT);
                }
                /*if (bL && bU) {
                    myTank.setDir(Dir.LU);
                }
                if (bL && bD) {
                    myTank.setDir(Dir.LD);
                }
                if (bR && bU) {
                    myTank.setDir(Dir.RU);
                }
                if (bR && bD) {
                    myTank.setDir(Dir.RD);
                }*/
            }
        }
    }
}

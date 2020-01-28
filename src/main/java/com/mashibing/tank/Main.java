package com.mashibing.tank;

import java.awt.*;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        TankFrame tankFrame = new TankFrame();

/*        String initBadTank = PropertyMgr.getProperty("initBadTanks");

        for (int i = 0; i < Integer.valueOf(initBadTank); i++) {
            tankFrame.tanks.add(new Tank(50 + 80 * i, 300, Dir.DOWN, 5, Group.BAD, tankFrame));
        }*/

        new BadFactory().createTank(tankFrame);


        while (true) {
            tankFrame.repaint();
            Thread.sleep(50);
        }
    }
}




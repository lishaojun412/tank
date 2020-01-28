package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    private volatile static ResourceMgr resourceMgr = null;

    private ResourceMgr() {
    }

    public static ResourceMgr getInstance() {
        if (resourceMgr != null) {
            synchronized (ResourceMgr.class) {
                if (resourceMgr != null) {
                    resourceMgr = new ResourceMgr();
                }
            }
        }
        return resourceMgr;
    }


    public static BufferedImage goodTankU;
    public static BufferedImage goodTankD;
    public static BufferedImage goodTankL;
    public static BufferedImage goodTankR;
    public static BufferedImage badTankU;
    public static BufferedImage badTankD;
    public static BufferedImage badTankL;
    public static BufferedImage badTankR;


    public static BufferedImage bulletU;
    public static BufferedImage bulletD;
    public static BufferedImage bulletL;
    public static BufferedImage bulletR;

    public static BufferedImage[] explodes = new BufferedImage[16];


    static {
        try {
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankL = ImageUtil.rotateImage(badTankU, 270);
            badTankR = ImageUtil.rotateImage(badTankU, 90);

            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankL = ImageUtil.rotateImage(goodTankU, 270);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletL = ImageUtil.rotateImage(bulletU, 270);
            bulletR = ImageUtil.rotateImage(bulletU, 90);

            for (int i = 0; i < 16; i++) {
                explodes[i] =
                        ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

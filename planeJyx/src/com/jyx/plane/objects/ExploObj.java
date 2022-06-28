package com.jyx.plane.objects;

import java.awt.*;

public class ExploObj extends GameObj {

    static Image[] pic = new Image[6];

    int explodeCount = 0;

    static {
        for (int i = 0; i < pic.length; i++) {
            pic[i] = Toolkit.getDefaultToolkit().getImage("imgs/explode/e"+(i+1)+".png");
        }
    }

    public ExploObj(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics gImage) {

        if (explodeCount < 6){
            super.img = pic[explodeCount];
            super.paintSelf(gImage);
            explodeCount++;
        }
    }
}

package com.jyx.plane.objects;

import com.jyx.plane.GameStart;
import com.jyx.plane.utils.GameUtils;

import java.awt.*;

public class XShellObj extends GameObj {


    @Override
    public Image getImg() {
        return super.getImg();
    }

    public XShellObj() {
        super();
    }

    public XShellObj(Image img, int x, int y, int width, int height, double speed, GameStart frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y +=speed;


        //我方子弹的越界消失 条件 y < 0  改变后的坐标 (-100,100)
        if (y> 1000){
            this.x = -100;
            this.y = 100;
            GameUtils.removeList.add(this);
        }

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}

package com.jyx.plane.objects;

import com.jyx.plane.GameStart;
import com.jyx.plane.utils.GameUtils;

import java.awt.*;

public class BossObj extends GameObj {

    int bossLife = 5;

    public BossObj(Image img, int x, int y, int width, int height, double speed, GameStart frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        if (x > 950 || x < -50){
            speed = -speed;
        }
        x += speed;
        for (PShellObj PShellObj : GameUtils.PShellObjList){
            if (this.getRec().intersects(PShellObj.getRec())){
                PShellObj.setX(-100);
                PShellObj.setY(100);
                GameUtils.removeList.add(PShellObj);
                bossLife--;
            }
            if (bossLife <= 0){
                GameStart.state = 4;
            }
        }
        //血条的白色背景
        gImage.setColor(Color.white);
        gImage.fillRect(20,40,100,10);
        //血条的绘制
        gImage.setColor(Color.red);
        gImage.fillRect(20,40,bossLife * 100 / 5,10);

        //X敌机发射炮弹

        if(GameStart.count%120 == 0){
            XShellObj xShell = new XShellObj(GameUtils.xShellImg,x+40,y+100,7,11,7,frame);
            System.out.println("22222222");
            GameUtils.gameObjList.add(xShell);
        }

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}

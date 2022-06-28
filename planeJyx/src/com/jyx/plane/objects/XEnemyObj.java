package com.jyx.plane.objects;

import com.jyx.plane.GameStart;
import com.jyx.plane.utils.GameUtils;

import java.awt.*;

public class XEnemyObj extends GameObj {

    @Override
    public Image getImg() {
        return super.getImg();
    }

    public XEnemyObj() {
        super();
    }

    public XEnemyObj(Image img, int x, int y, int width, int height, double speed, GameStart frame) {
        super(img, x, y, width, height, speed, frame);
    }
    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        //敌我飞机碰撞检测
        if (this.getRec().intersects(this.frame.planeObj.getRec())){
            GameStart.state = 3;
        }
        //敌机的越界消失
        if (y > 1000){
            this.y = 100;
            this.x = -100;
            GameUtils.removeList.add(this);
        }
        //敌机与我方炮弹碰撞检测
        for (PShellObj PShellObj : GameUtils.PShellObjList) {
            if (this.getRec().intersects(PShellObj.getRec())){
                ExploObj exploObj = new ExploObj(x,y);
                GameUtils.exploObjList.add(exploObj);
                GameUtils.removeList.add(exploObj);
                PShellObj.setX(-100);
                PShellObj.setY(100);
                this.y = 100;
                this.x = -100;
                GameUtils.removeList.add(PShellObj);
                GameUtils.removeList.add(this);
                GameStart.num++;
            }
        }

        //X敌机发射炮弹
        if(y%120 == 0){
            XShellObj xShell = new XShellObj(GameUtils.xShellImg,x+18,y+50,7,11,7,frame);
            GameUtils.xShellObjList.add(xShell);
        }
    }

}

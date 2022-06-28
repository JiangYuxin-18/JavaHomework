package com.jyx.plane.objects;

import com.jyx.plane.GameStart;
import com.jyx.plane.utils.GameUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlaneObj extends GameObj {

    //飞行方向初始化
    int direction = -1;
    final int UP =0;
    final int DOWN =1;
    final int LEFT =2;
    final int RIGHT =3;

    @Override
    public Image getImg() {
        return super.getImg();
    }

    public PlaneObj() {
        super();
    }

    public PlaneObj(Image img, int x, int y, int width, int height, double speed, GameStart frame) {
        super(img, x, y, width, height, speed, frame);

    }
    @Override
    public Rectangle getRec() {
        return super.getRec();
    }


    //运动方法
    public void move() {
        switch(direction) {
            case UP://up
                if(y>50){
                y-= speed ;
            }
                break;
            case RIGHT://right
                if(x<950){
                    x+=speed;
                }
                break;
            case DOWN://down
                if(y<950){
                    y+=speed;
                }
                break;
            case LEFT://left
                if(x>0){
                    x-=speed;
                }
                break;
        }
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);

        //方向键盘监听
       this.frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    direction=RIGHT;
                } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    direction=LEFT;
                } else if(e.getKeyCode() == KeyEvent.VK_UP){
                    direction=UP;
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    direction=DOWN;
                }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    GameUtils.PShellObjList.add(new PShellObj(GameUtils.shellImg, PlaneObj.super.x + 17, PlaneObj.super.y - 16, 14, 29, 5, frame));
                }
            }
        });
       //持续运动
        move();


        //我方飞机与BOSS飞机碰撞检测
        if (this.frame.bossObj != null && this.getRec().intersects(this.frame.bossObj.getRec())){
            GameStart.state = 3;
        }

        //我方飞机与敌方炮弹碰撞，造成伤害检测并改变机身样式
        for (XShellObj xShellObj : GameUtils.xShellObjList){
            if (this.getRec().intersects(xShellObj.getRec())){
                /*System.out.println("111");*/
                xShellObj.setY(100);
                xShellObj.setX(-100);
                GameUtils.removeList.add(xShellObj);
                GameStart.life--;
                //寻找并移除原飞机
                for(int i=0;i<GameUtils.gameObjList.size();i++){
                    if(GameUtils.gameObjList.get(i)==this.frame.planeObj){
                        GameUtils.gameObjList.remove(i);
                        //替换破坏后的飞机
                        if(GameStart.life==2){
                            this.frame.planeObj = new PlaneObj(GameUtils.planeImg2,x,y,20,30,7,frame);
                            GameUtils.gameObjList.add(this.frame.planeObj);
                        }
                        else if(GameStart.life==1){
                            this.frame.planeObj = new PlaneObj(GameUtils.planeImg3,x,y,20,30,7,frame);
                            GameUtils.gameObjList.add(this.frame.planeObj);
                        }else if (GameStart.life <= 0){
                            GameStart.life=3;
                            GameStart.state = 3;
                        }
                    }
                }
         /*       System.out.println(GameUtils.gameObjList.remove(1));
                GameStart.life--;
                if(GameStart.life==2){
                    this.frame.planeObj = new PlaneObj(GameUtils.planeImg2,x,y,20,30,7,frame);
                    GameUtils.gameObjList.add(this.frame.planeObj);
                }
                else if(GameStart.life==1){
                    this.frame.planeObj = new PlaneObj(GameUtils.planeImg3,x,y,20,30,7,frame);
                    GameUtils.gameObjList.add(this.frame.planeObj);
                }*/
                /*System.out.println(GameStart.life);*/
/*                PlaneObj planeObj = new PlaneObj(GameUtils.planeImg,500,700,20,30,7,frame);
                GameUtils.gameObjList.add(planeObj);*/
            }
        }
        //我方飞机血条背景
        gImage.setColor(Color.white);
        gImage.fillRect(880,970,100,10);
        //我方飞机血条绘制（三条命）
        gImage.setColor(Color.red);
        gImage.fillRect(880,970,GameStart.life * 100 / 3,10);
    }

}

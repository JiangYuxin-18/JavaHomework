package com.jyx.plane;


import com.jyx.plane.objects.*;
import com.jyx.plane.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameStart extends JFrame {


    /*
    窗口属性
      1、窗口高度
      2、窗口宽度
      3、缓存图片
      4、重绘次数
      */
    public int width = 1000;
    public int height = 1000;
    public Image offScreenImage = null;
    public static int count = 1;

    /*
    游戏属性
      1、游戏状态：0.准备 1.游戏中 2.暂停 3.通关失败 4.通关成功
      2、游戏得分
      3、我方飞机生命值
      */
    public static int state = 0;
    public static int num = 0;
    public static int life = 3;

    /*
    初始化对象
     1、背景
     2、我方飞机
     3、敌方boss
    */
    public BgObj bgObj = new BgObj(GameUtils.bgImg,0,-4000,2);
    public PlaneObj planeObj = new PlaneObj(GameUtils.planeImg1,470,900,20,30,7,this);
    public BossObj bossObj = null;


    //游戏启动
    public void start(){
        //窗口设置
        this.setVisible(true);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setTitle("射击游戏之飞机大战");
        //新增游戏集合
        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);

        //启动事件（左键单机）
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1 && state == 0){
                    state = 1;
                }
            }
        });
        //暂停事件（S键）
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 83){
                    switch (state){
                        case 1:
                            state = 2;
                            break;
                        case 2:
                            state = 1;
                            break;
                            default:
                    }
                }
            }
        });

        //游戏中反复绘制设置
        while (true){
            if (state == 1){
                create();
                repaint();
            }
            try {
                //重绘间隔
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //游戏重启
    void restart(){
        GameUtils.gameObjList.clear();
        GameUtils.gameObjList.add(bgObj);
        this.planeObj = new PlaneObj(GameUtils.planeImg1,470,900,20,30,7,this);
        GameUtils.gameObjList.add(this.planeObj);
        num =0;
        state =1 ;
    }

    //绘制游戏画面
    @Override
    public void paint(Graphics g) {
        //双缓存图片
        if (offScreenImage == null){
            offScreenImage = createImage(width,height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0,0,width,height);

        //游戏状态
        //未开始
        if (state == 0){
            gImage.drawImage(GameUtils.bgImg,0,0,null);
            gImage.drawImage(GameUtils.planeImg3,330,615,null);
            gImage.drawImage(GameUtils.titleImg,250,120,null);
            GameUtils.drawWord(gImage,"点击开始游戏",Color.yellow,40,400,650);

            repaint();
        }

        //运行中
        if (state == 1){
            GameUtils.gameObjList.addAll(GameUtils.exploObjList);
            for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }
            GameUtils.gameObjList.removeAll(GameUtils.removeList);
        }

        //游戏失败
        if (state == 3){
            gImage.drawImage(GameUtils.explodeImg,planeObj.getX()-5,planeObj.getY()+15,null);
            GameUtils.drawWord(gImage,"任务失败",Color.RED,50,400,500);
            //失败重启事件（左键）
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == 1 && state == 3){
                        restart();
                }
            }});

        }

        //游戏成功
        if (state == 4){
            gImage.drawImage(GameUtils.explodeImg,bossObj.getX() + 30,bossObj.getY(),null);
            GameUtils.drawWord(gImage,"任务完成",Color.green,50,400,500);
        }
        GameUtils.drawWord(gImage," 击败敌机："+num,Color.red,40,700,100);
        g.drawImage(offScreenImage,0,0,null);
        count++;
        /*System.out.println(GameUtils.gameObjList.size());*/
    }

    //添加对象设置
    void create() {
        // BOSS敌机
        if (num > 20 && bossObj == null){
            bossObj = new BossObj(GameUtils.bossImg,250,35,155,100,5,this);
            GameUtils.gameObjList.add(bossObj);
        }
        // K敌机（未装载机炮的敌机）
        if (count % 150 == 0){
            GameUtils.KEnemyObjList.add(new KEnemyObj(GameUtils.enemyImg,(int)(Math.random()*21)*46,0,49,36,5,this));
            GameUtils.gameObjList.add(GameUtils.KEnemyObjList.get(GameUtils.KEnemyObjList.size() -1));
        }
        // X敌机（装载机炮的敌机）
        if (count % 50 == 0){
            GameUtils.xenemyObjList.add(new XEnemyObj(GameUtils.xEnemyImg,(int)(Math.random()*21)*46,0,49,36,3,this));
            GameUtils.gameObjList.add(GameUtils.xenemyObjList.get(GameUtils.xenemyObjList.size() -1));
        }
        // 敌机炮弹
        if (count % 20 == 0 && GameUtils.xShellObjList.size()>0){
            GameUtils.gameObjList.add(GameUtils.xShellObjList.get(GameUtils.xShellObjList.size() - 1));
        }
        // 我方炮弹
        if (GameUtils.PShellObjList.size()>0 && count % 10==0){
            GameUtils.gameObjList.add(GameUtils.PShellObjList.get(GameUtils.PShellObjList.size() - 1));
        }
        // BOSS必杀炸弹
        if (count % 50 == 0 && bossObj != null){
            GameUtils.boomObjList.add(new BoomObj(GameUtils.bulletImg,bossObj.getX()+76,bossObj.getY()+85,15,25,3,this));
            GameUtils.gameObjList.add(GameUtils.boomObjList.get(GameUtils.boomObjList.size() - 1));
        }
    }


    public static void main(String[] args) {
        GameStart gameStart = new GameStart();
        gameStart.start();
    }
}

package com.jyx.plane.utils;


import com.jyx.plane.objects.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameUtils {

    //绘制字符串工具
    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("黑体",Font.BOLD,size));
        gImage.drawString(str,x,y);
    }

    //导入图片工具
    //背景
    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    //标题
    public static Image titleImg = Toolkit.getDefaultToolkit().getImage("imgs/title.png");
    //我方战斗机（三种状态）
    public static Image planeImg1 = Toolkit.getDefaultToolkit().getImage("imgs/plane/plane3.png");
    public static Image planeImg2 = Toolkit.getDefaultToolkit().getImage("imgs/plane/plane2.png");
    public static Image planeImg3 = Toolkit.getDefaultToolkit().getImage("imgs/plane/plane1.png");
    //BOSS敌机
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss.png");
    //K敌机图片
    public static Image enemyImg = Toolkit.getDefaultToolkit().getImage("imgs/kenemy.png");
    //X敌机图片
    public static Image xEnemyImg = Toolkit.getDefaultToolkit().getImage("imgs/xenemy.png");
    //我方炮弹
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("imgs/ishell.png");
    //敌方炮弹
    public static Image xShellImg = Toolkit.getDefaultToolkit().getImage("imgs/xshell.png");
    //BOSS炸弹
    public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("imgs/boom.png");
    //爆炸静态
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("imgs/explode/e6.png");


    //对象集合
    //游戏元素
    public static List<GameObj> gameObjList = new ArrayList<>();
    //移除元素
    public static List<GameObj> removeList = new ArrayList<>();
    //K敌机
    public static List<KEnemyObj> KEnemyObjList = new ArrayList<>();
    //X敌机
    public static List<XEnemyObj> xenemyObjList = new ArrayList<>();
    //我方炮弹
    public static List<PShellObj> PShellObjList = new ArrayList<>();
    //敌方炮弹
    public static List<XShellObj> xShellObjList = new ArrayList<>();
    //BOSS炸弹
    public static List<BoomObj> boomObjList = new ArrayList<>();
    //爆炸
    public static List<ExploObj> exploObjList = new ArrayList<>();

}

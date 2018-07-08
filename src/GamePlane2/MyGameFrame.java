package GamePlane2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame {

    Image planeimage = GameUtil.getImage("images/plane.png");
    Image bgimage = GameUtil.getImage("images/bg.jpg");

     Plane plane = new Plane(planeimage,250,250);
     Shell[] shells = new Shell[50];//炮弹数组

    Explode boom ;
    Date starttime = new Date();
    Date endTime;
    int period;//生存时间

        @Override
        public void paint(Graphics g) {//paint系统自动调用

            Color c = g.getColor();//保存画笔
            g.drawImage(bgimage,0,0,null);
           plane.drawSelf(g);//画飞机

            for (int i=0;i<shells.length;i++) {
                shells[i].draw(g);
                //飞机和炮弹的碰撞检测
                boolean peng = shells[i].getRect().intersects(plane.getRect());//炮弹矩形框与飞机矩形框相交
                if (peng) {
                    plane.live = false;
                    if (boom == null) {
                        boom = new Explode(plane.x, plane.y);
                        endTime = new Date();
                        period = (int) ((endTime.getTime() - starttime.getTime()) / 1000);
                    }
                    boom.draw(g);
                }
                //计时功能
                if (!plane.live) {
                    Font f = new Font("宋体",Font.BOLD,30);
                    g.setFont(f);
                    g.setColor(Color.white);
                    if (period<10){
                        g.drawString("菜鸟",230, 220);
                    }else if (10<period||period<20){
                        g.drawString("高手",230, 220);
                    }else if (20<period){
                        g.drawString("大神",230, 220);
                    }
                    g.drawString("游戏时间：" + period + "秒", 160, 250);
                }
            }
            g.setColor(c);
        }

        //反复重画窗口
        class PaintThread extends Thread{
            @Override
            public void run() {
                while (true){
                  repaint();//重画
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
         //键盘监听
        class KeyMonitor extends KeyAdapter{
            @Override
            public void keyPressed(KeyEvent e) {
                plane.addDirection(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.minusDirection(e);
            }
        }

    //初始化窗口
    public void launchFrame(){
        this.setTitle("飞机大战");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        this.setLocation(300,300);


        this.addWindowListener(new WindowAdapter() {//窗口关闭
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
            new PaintThread().start();//启动重画线程
            addKeyListener(new KeyMonitor());//给窗口增加键盘监听

        //初始化50个炮弹
        for (int i=0;i<shells.length;i++){
            shells[i] = new Shell();
        }
    }

    //解决awt闪屏问题，添加双缓冲技术
    private Image offScreenImage = null;
        public void update(Graphics g){
            if (offScreenImage == null)
                offScreenImage =this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
            Graphics gOff =offScreenImage.getGraphics();
            paint(gOff);
            g.drawImage(offScreenImage,0,0,null);
        }


    public static void main(String[] args) {
        MyGameFrame myGameFrame = new MyGameFrame();
        myGameFrame.launchFrame();

    }
}

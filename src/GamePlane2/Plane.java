package GamePlane2;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{

    boolean left,up,right,down;
    boolean live = true;

    @Override
    public void drawSelf(Graphics g) {


        if (live) {
            g.drawImage(img, (int) x, (int) y, width, height, null);

            if (left) {
                x -= speed;
                if (x < 0) {
                    x = 0;
                }
            }
            if (right) {
                x += speed;
                if (x > Constant.GAME_WIDTH-width) {
                    x = Constant.GAME_WIDTH-width;
                }
            }
            if (up) {
                y -= speed;
                if (y < 30) {
                    y = 30;
                }
            }
            if (down) {
                y += speed;
                if (y > Constant.GAME_HEIGHT-height) {
                    y = Constant.GAME_HEIGHT-height;
                }
            }
//            if (x>Constant.GAME_WIDTH||x<0){
//                x = speed;
//                System.out.println("跑外边去了");
//            }
//            if (y>Constant.GAME_HEIGHT||y<0){
//                y = speed;
//                System.out.println("跑外边去了");
//            }


        }else {

        }


    }



    public Plane(Image img,double x,double y){
        this.img = img;
        this.x = x;
        this .y = y;
        this.speed = 8;
        this.width = 30;
        this.height = 30;
    }
    //按下某键增加方向
    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }
    //按下某键，取消相应的方向
    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
}

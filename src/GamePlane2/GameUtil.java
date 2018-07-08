package GamePlane2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class GameUtil {

public GameUtil(){

}
//图片获取方法
public static Image getImage(String path){
    BufferedImage bufferedImage = null;
    try {
        URL url = GameUtil.class.getClassLoader().getResource(path);
        bufferedImage = ImageIO.read(url);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return bufferedImage;
}
}

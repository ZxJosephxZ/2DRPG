package OBject;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends SuperObject{
    GamePanel gp;
    public ObjectKey(GamePanel gp)
    {
        this.gp = gp;
        name = "Key";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ/llave.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

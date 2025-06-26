package OBject;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Boots extends SuperObject{
    GamePanel gp;
    public Object_Boots(GamePanel gp)
    {
        this.gp = gp;
        name = "Boot";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ/bota.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}

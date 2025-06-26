package OBject;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Door extends SuperObject{
    GamePanel gp;
    public Object_Door(GamePanel gp)
    {
        this.gp = gp;
        name = "Door";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ/puerta.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}

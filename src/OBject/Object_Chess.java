package OBject;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Chess extends SuperObject{
    GamePanel gp;
    public Object_Chess(GamePanel gp)
    {
        this.gp = gp;
        name = "Chess";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/OBJ/cofre.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

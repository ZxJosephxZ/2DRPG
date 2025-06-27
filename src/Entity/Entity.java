package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utils.Constants.PlayerConstants.*;
import static Utils.Constants.PlayerConstants.RIGHT;

public class Entity {
    public KeyHandler KeyH;
    public BufferedImage animations[][];
    public int worldX, worldY;
    public int speed;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, getSolidAreaDefaultY;
    public boolean collisionOn = false;
    public int playerAction = IDLE;
    public int aniTick, aniIndex, aniSpeed = 15;
    public int pressOff = 0;
    public int actionLockCount=0;
    int dialogueIndex = 0;
    String dialogues[] = new String[20];
    GamePanel gp;

    public Entity(GamePanel gp, KeyHandler KeyH)
    {
        this.KeyH = KeyH;
        this.gp = gp;

    }
    public BufferedImage importImage(String pathimg)
    {
        BufferedImage imgPlayer = null;
        InputStream is = getClass().getResourceAsStream(pathimg);
        try{
            imgPlayer = ImageIO.read(is);
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            try{
                is.close();
                
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            return imgPlayer;
        }
    }
    public BufferedImage setUp(BufferedImage sprite)
    {
        UtilityTool uTool = new UtilityTool();
        sprite = uTool.scaleImage(sprite,gp.tileSize,gp.tileSize);
        return sprite;
    }

    public void updateAnimationTick()
    {
        aniTick++;
        if (aniTick >= aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction))
            {
                aniIndex = 0;
            }
        }
    }
    public void speak(){}
    public void setAction(){};
    public void update(){
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkOject(this, false);
        gp.collisionChecker.checkPlayer(this);
        if (collisionOn == false && pressOff != 1)
        {
            switch(playerAction){
                case DOWN:
                    worldY -= speed;
                    break;
                case IDLE:
                    worldY += speed;
                    break;
                case LEFT:
                    worldX -= speed;
                    break;
                case RIGHT:
                    worldX += speed;
                    break;
            }
        }
    };

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        {
            updateAnimationTick();
            g2.drawImage(animations[playerAction][aniIndex], screenX,screenY,null);
        }

    }
}

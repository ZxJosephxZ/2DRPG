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

public class Player extends Entity{

    BufferedImage imgPlayer;

    //int aniTick, aniIndex, aniSpeed = 15;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler KeyH)
    {
        super(gp, KeyH);
        this.KeyH = KeyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle(16,16,15,18);
        solidAreaDefaultX = solidArea.x;
        getSolidAreaDefaultY = solidArea.y;
        imgPlayer = importImage("/Player/spriteCompletoPlayer.png");
        loadAnimations();
        setDefaultValues();
    }

    public void setDefaultValues()
    {
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
    }

    private void loadAnimations()
    {
        animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length;j++)
        {
            for (int i = 0; i < animations[j].length;i++)
            {
                animations[j][i] = setUp(imgPlayer.getSubimage(i*32,j*32,32,32));
            }
        }
    }

    public void update()
    {
        pressOff = 0;
        if (KeyH.upPressed == true)
        {
             playerAction = DOWN;
        }
        else if (KeyH.downPressed == true)
        {
            playerAction = IDLE;
        }
        else if (KeyH.leftPressed == true)
        {
            playerAction = LEFT;
        }
        else if (KeyH.rightPressed == true)
        {
            playerAction = RIGHT;
        }
        else
        {
            pressOff = 1;
        }
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        int objIndex = gp.collisionChecker.checkOject(this,true);
        pickUpObject(objIndex);
        int npcIndex = gp.collisionChecker.CheckEntity(this, gp.npc);
        interactNpc(npcIndex);
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
    }

    public void interactNpc(int i)
    {
        if (i != 999)
        {
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
        }
    }

    public void pickUpObject(int i)
    {
        if (i != 999)
        {

        }
    }

    public void draw(Graphics2D g2)
    {
        updateAnimationTick();
        g2.drawImage(animations[playerAction][aniIndex], screenX,screenY,null);
    }

}

package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utils.Constants.PlayerConstants.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler KeyH;
    BufferedImage imgPlayer;
    BufferedImage animations[][];
    int aniTick, aniIndex, aniSpeed = 15;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler KeyH)
    {
        this.gp = gp;
        this.KeyH = KeyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle(16,16,15,18);
        importImage();
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
                animations[j][i] = imgPlayer.getSubimage(i*32,j*32,32,32);
            }
        }
    }

    private void importImage()
    {
        InputStream is = getClass().getResourceAsStream("/Player/spriteCompletoPlayer.png");
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
        }
    }

    public void update()
    {
        pressOff = 0;
        if (KeyH.upPressed == true)
        {
           // if (!collisionOn)
          //  {
         //       worldY -= speed;
         //  }
             playerAction = DOWN;
        }
        else if (KeyH.downPressed == true)
        {
           // if (!collisionOn)
          //  {
         //       worldY += speed;
           // }

            playerAction = IDLE;
        }
        else if (KeyH.leftPressed == true)
        {
          //  if (!collisionOn)
          //  {
         //       worldX -= speed;
         //   }

            playerAction = LEFT;
        }
        else if (KeyH.rightPressed == true)
        {
           // if (!collisionOn)
            //{
             //   worldX += speed;
            //}
            playerAction = RIGHT;
        }
        else
        {
            pressOff = 1;
        }
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
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

    private void updateAnimationTick()
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

    public void draw(Graphics2D g2)
    {
        updateAnimationTick();
        g2.drawImage(animations[playerAction][aniIndex], screenX,screenY,48,48,null);

    }

}

package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import static Utils.Constants.PlayerConstants.*;

public class NPC_OldMan extends Entity{

    BufferedImage imgNpc;
    public NPC_OldMan(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        speed = 1;
        imgNpc = importImage("/Npc/npc2.png");
        loadAnimations();
        setDialogue();
    }

    public void setDialogue()
    {
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island to find the treasure?";
        dialogues[2] = "I used to be a great wizard but now... I'm a bit too old for taking an adventure.";
        dialogues[3] = "Well, good luck on you.";
    }

    public void speak(){
        if (dialogues[dialogueIndex] == null)
        {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch(gp.player.playerAction){
            case DOWN:
                playerAction = IDLE;
                break;
            case IDLE:
                playerAction = DOWN;
                break;
            case LEFT:
                playerAction = RIGHT;
                break;
            case RIGHT:
                playerAction = LEFT;
                break;
        }
    }

    public void setAction()
    {
        actionLockCount++;
        if ( actionLockCount == 120){
        Random random = new Random();
        int i = random.nextInt(100)+1;
        if (i <= 25)
        {
            playerAction = DOWN;
        }
        if (i > 25 && i <= 50)
        {
            playerAction = IDLE;
        }
        if (i > 50 && i <= 75)
        {
            playerAction = LEFT;
        }
        if (i > 75 && i <= 100)
        {
            playerAction = RIGHT;
        }
        actionLockCount = 0;
        }
    }

    private void loadAnimations()
    {
        animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length;j++)
        {
            for (int i = 0; i < animations[j].length;i++)
            {
                animations[j][i] = setUp(imgNpc.getSubimage(i*32,j*32,32,32));
            }
        }
    }
}

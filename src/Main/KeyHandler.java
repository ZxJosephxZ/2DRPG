package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //debug
    public boolean checkDrawTime = false;
    GamePanel gp;
    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.playState)
        {
            if (code == KeyEvent.VK_W)
            {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S)
            {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A)
            {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D)
            {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_T)
            {
                if (checkDrawTime == false)
                {
                    checkDrawTime = true;
                }
                else if (checkDrawTime == true)
                {
                    checkDrawTime = false;
                }
            }
            if (code == KeyEvent.VK_P)
            {
                gp.gameState = gp.pauseState;
            }
        }
        else if (gp.gameState == gp.pauseState)
        {
            if (code == KeyEvent.VK_P)
            {
                gp.gameState = gp.playState;
            }
        }
        else if (gp.gameState == gp.dialogueState)
        {
            if (code == KeyEvent.VK_ENTER)
            {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
    }
}

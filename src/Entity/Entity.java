package Entity;

import java.awt.*;

import static Utils.Constants.PlayerConstants.IDLE;

public class Entity {
    public int worldX, worldY;
    public int speed;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int playerAction = IDLE;
    public int pressOff = 0;
}

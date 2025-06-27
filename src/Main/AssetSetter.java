package Main;
import Entity.NPC_OldMan;


public class AssetSetter {
    GamePanel gp;
    KeyHandler keyH;
    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
        keyH = new KeyHandler(gp);
    }

    public void setObject()
    {

    }

    public void setNpc()
    {
        gp.npc[0] = new NPC_OldMan(gp, keyH);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
}

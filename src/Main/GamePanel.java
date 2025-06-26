package Main;

import Entity.Player;
import Tile.TileManager;
import OBject.SuperObject;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Pantalla
    final int originalTileSize = 16; //32*32px
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //escalado si quieres *scale
    public final int maxScreenCol = 16; //ancho de escalado
    public final int maxScreenRow = 12; //largo de escalado
    public final int screenWidth = maxScreenCol * tileSize; //768px
    public final int screenHeight = maxScreenRow * tileSize; //576px

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    KeyHandler KeyH = new KeyHandler(this);

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    private final int FPS = 120;
    private final int UPS = 100;

    Sound se = new Sound();
    Sound music = new Sound();
    TileManager tileM = new TileManager(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, KeyH);
    public SuperObject obj[] = new SuperObject[10];
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    Thread gameThread;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        aSetter.setObject();
        playMusic(0);
        gameState = playState;
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update()
    {
        if (gameState == playState)
        {
            player.update();
        }
        if (gameState == pauseState)
        {

        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        long drawStart = 0;
        if (KeyH.checkDrawTime == true)
        {
            drawStart = System.nanoTime();
        }
        tileM.draw(g2);
        for (int i = 0; i < obj.length;i++)
        {
            if (obj[i] != null)
            {
                obj[i].draw(g2,this);
            }
        }
        player.draw(g2);
        ui.draw(g2);
        //debug
        if (KeyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
            g2.dispose();
        }
    }

    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSe(int i)
    {
        se.setFile(i);
        se.play();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        while (true)
        {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += ( currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1)
            {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1)
            {
                repaint();
                frames++;
                deltaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000)
            {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " +frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}

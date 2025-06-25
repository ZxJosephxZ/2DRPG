package Main;

import Entity.Player;
import Tile.TileManager;

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
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    KeyHandler KeyH = new KeyHandler();
    Thread gameThread;

    private final int FPS = 120;
    private final int UPS = 100;

    TileManager tileM = new TileManager(this);

    public Player player = new Player(this, KeyH);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update()
    {
        player.update();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
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

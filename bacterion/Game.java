/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;
import pure_engine.KeyManager;
import pure_engine.MouseManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class Game implements Runnable  {

    private BufferStrategy bs;          // to have several buffers when displaying
    private Graphics g;                 // to paint objects
    private Display display;            // to display in the game
    private KeyManager keyManager;      // to manage the keyboard
    private MouseManager mouseManager;  // to manage the mouse and click
    private Thread thread;              // thread to create the game
    private boolean running;            // to set the game
    
    private String title;               // title of the window
    private String nombreArchivo;       // nombre que tendrá el archivo de guardado
    private int width;                  // width of the window
    private int height;                 // height of the window
    private Player player;              // el objeto que moveremos en el juego
    private double elicRandom;          // index de qué tan seguido saldrán nuevos metabolitos
    private LinkedList<Elicitador> elicitadores;    // elicitadores que caerán y nuestra bacteria absorbe
    private LinkedList<Antibiotico> antibioticos;   // antibioticos que caerán y nuestros receptores perciben
    private LinkedList<Receptor> receptores;        // receptores que detectarán los antibióticos
    private EstresBarra barra;          // barra donde guardaremos la información respecto al estrés
    private int levelSelected;          // the level selected. 1: easy  2: medium   3: hard
    private Animation bact0, bact1, bact2; // to display the animated bacterias in chooseMenu
    
    private boolean pause;
    private boolean finished;
    private boolean startScreen;
    private boolean chooseMenu;
    
    // to set a delay for the pause button.
    // PAUSE_INTERVAL is the limit (number of frames), pauseStun is the counter.
    private final byte PAUSE_INTERVAL = 10;
    private byte pauseStun;
    
    // to animate the background in the main menu
    int bg1X, bg2X, bgMoveDelay, bgMoveDelayCounter;
    
    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        this.nombreArchivo = "SaveFile.txt";
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
        startScreen = true;
        chooseMenu = false;
        levelSelected = 1;
        display = new Display(title, getWidth(), getHeight());
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        Constants.init();
        
        player = new Player(this, width/2 -(Constants.PLAYER_WIDTH/2), height/2-(Constants.PLAYER_HEIGHT/2));
        elicRandom = Constants.RANDOM_INDEX;
        elicitadores = new LinkedList<>();
        antibioticos = new LinkedList<>();
        receptores = Constants.initReceptores(this);
        finished = false;
        bact0 = new Animation(Assets.bacteria0, 100);
        bact1 = new Animation(Assets.bacteria1, 100);
        bact2 = new Animation(Assets.bacteria2, 100);
        pauseStun = 0;
        
        bg1X = 0;
        bg2X = Constants.GAME_WIDTH;
        bgMoveDelay = 1;
        bgMoveDelayCounter = 0;
        
        barra = new EstresBarra(this,20,height-32,5*player.getEstres(),Constants.BARRA_HEIGHT,0);
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set title of the game
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Used to control keys
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    // Return el player de nuestro juego
    public Player getPlayer(){
        return player;
    }
    
    // Finaliza el juego
    // (Tun tun tu tuuuuuuuuuuun TUUUn TUUUUn TUN)
    public void endGame(){
        this.finished = true;
    }

    /**
     * Control movement of all instances of the game
     */
    private void tick() {
        keyManager.tick();
        
        // To pause and unpause.
        pauseStun++;
        if (keyManager.p) {
            if (pauseStun > PAUSE_INTERVAL) {
                pause = !pause;
                pauseStun = 0;
            }
        }
        
        // To save.
        if (keyManager.g) {
            try {
                grabarArchivo();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        // To load.
        if (keyManager.c) {
            try {
                leeArchivo();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            pause = true;
        }
        
        if (keyManager.r) {
            this.init();
            pause = true;
        }
        
        if(finished)
            return;
        
        if(pause)
            return;
        
        if(mouseManager.isIzquierdo()){
            if(player.hasAntibiotico()){
                Antibiotico anti = player.getAntibiotico();
                anti.disparar(player.getMidX(), player.getMidY(), 
                        mouseManager.getY()-player.getMidY(),mouseManager.getX()-player.getMidX());
                antibioticos.add(anti);
            }
        }
        
        if(mouseManager.isDerecho()){
            for(Receptor recep : receptores){
                if(recep.getCircShape().contains(mouseManager.getPoint())){
                    try {
                        java.awt.Desktop.getDesktop().browse(recep.getURI());
                    } catch (URISyntaxException | IOException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        elicRandom += Constants.RANDOM_INCREASE;
        double rand = Math.random();
        int randDir; //1 arriba, 2 abajo, 3izq, 4 der
        if(rand<elicRandom){
            randDir = (int)(Math.random() * 4 + 1);
            //(game, x, y, width, height, speed);
            if (randDir == 1) { //arriba
                elicitadores.add(new Elicitador(this,(int)(Math.random()*width),-15,10,10,3,1));
            } else if (randDir == 2){ //abajo
                elicitadores.add(new Elicitador(this,(int)(Math.random()*width),getHeight()+5,10,10,3,2));
            } else if (randDir == 3) { //izquierda
                elicitadores.add(new Elicitador(this,0,(int)(Math.random()*height),10,10,3,3));
            } else { //derecha
                elicitadores.add(new Elicitador(this,getWidth()+5,(int)(Math.random()*height),10,10,3,4));
            }
        }
        
        for(Elicitador elic : elicitadores){
            if(!elic.isExploded() && elic.getCircShape().intersects(player.getRectShape())){
                player.estresar();
                elic.explode();
            }
        }
        for(Receptor recep : receptores){
            for(Antibiotico anti : antibioticos){
                if(anti.getCircShape().intersects(recep.getRectShape()) && !recep.isExploded() && recep.getTipoInt() == anti.getTipoInt()){
                    anti.explode();
                    recep.explode();
                }
            }
        }
        
        player.tick();
        for(Elicitador met : elicitadores){
            if(!met.isExploded()){
                met.tick();
            }
        }
        for(Antibiotico anti : antibioticos){
            if(!anti.isExploded()){
                anti.tick();
            }
        }
        int[] cargas = new int[4];
        for(Receptor recep : receptores){
            recep.tick();
            if(!recep.isExploded()){
                switch(recep.getTipo()){
                    case E_COLI:
                        cargas[0]++;
                        break;
                    case B_SUBTILIS:
                        cargas[1]++;
                        break;
                    case P_AERUGINOSA:
                        cargas[2]++;
                        break;
                    case S_PNEUMONIAE:
                        cargas[3]++;
                        break;
                }
            }
        }
        
        boolean theEnd = true;
        for(int i=0; i<cargas.length; i++){
            if(player.cargaEsto(i)&&cargas[i]>0){
                theEnd = false;
            }
        }
        if(theEnd){
            finished = true;
        }
        
        barra.setWidth((int)(width*0.009*player.getEstres()));
        barra.tick();
    }
    
    // Guarda la información del objeto en un string
    public String toString(){
        return "";
    }
    
    // Carga la información del objeto desde un string
    public void loadFromString(String[] datos){
    }
    
    // Se encarga de guardar en un archivo toda la informacion de nuestra partida
    public void grabarArchivo() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo));
        fileOut.println(this.toString());
        fileOut.println(player.toString());
        fileOut.close();
    }
    
    // Lee toda la información que guardamos sobre la partida y la carga
    public void leeArchivo() throws IOException {
                                                          
        BufferedReader fileIn;
        try {
            fileIn = new BufferedReader(new FileReader(nombreArchivo));
        } catch (FileNotFoundException e){
            File archivo = new File(nombreArchivo);
            PrintWriter fileOut = new PrintWriter(archivo);
            fileOut.println("100,demo");
            fileOut.close();
            fileIn = new BufferedReader(new FileReader(nombreArchivo));
        }
        loadFromString(fileIn.readLine().split("\\s+"));
        player.loadFromString(fileIn.readLine().split("\\s+"));
        fileIn.close();
    }

    private void render() {
        if (startScreen) {
            renderStartscreen();
        } else {
            renderStarted();
        }
            
    }
    
    private void renderStartscreen() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            
            g.drawImage(Assets.backgroundStartScreen, bg1X, 0, width, height, null);
            g.drawImage(Assets.backgroundStartScreen, bg2X, 0, width, height, null);
            
            if (bgMoveDelayCounter++ >= bgMoveDelay) {
                bg1X--;
                bg2X--;
                bgMoveDelayCounter = 0;
            }
            
            // Make the background images wrap around.
            if (bg1X <= -1 * width)
                bg1X = width;
            if (bg2X <= -1 * width)
                bg2X = width;
            
            Rectangle mouseRect = mouseManager.getPerimeter();
            
            if (chooseMenu) {
                g.drawImage(Assets.chooseMenu, 0, 0, 640, 640, null);
                
                RoundRectangle2D.Double easyRect = new RoundRectangle2D.Double(26, height/3 - 13, 188, 230, 69, 69);
                RoundRectangle2D.Double mediumRect = new RoundRectangle2D.Double(226, height/3 - 13, 188, 230, 69, 69);
                RoundRectangle2D.Double hardRect = new RoundRectangle2D.Double(426, height/3 - 13, 188, 230, 69, 69);
                
                g.drawImage(bact0.getCurrentFrame(), 50, height/3 + 35, player.getWidth() + 40, player.getHeight(), null);
                g.drawImage(bact1.getCurrentFrame(), 250, height/3 + 35, player.getWidth() + 40, player.getHeight(), null);
                g.drawImage(bact2.getCurrentFrame(), 450, height/3 + 35, player.getWidth() + 40, player.getHeight(), null);
                
                bact0.tick();
                bact1.tick();
                bact2.tick();
                
                g.setColor(Color.lightGray);
                if (easyRect.intersects(mouseRect)) {
                    g.drawRoundRect(26, height/3 - 13, 188, 230, 69, 69);
                    if (mouseManager.isIzquierdo()) {
                        levelSelected = 1;
                        chooseMenu = false;
                    }
                } else if (mediumRect.intersects(mouseRect)) {
                    g.drawRoundRect(226, height/3 - 13, 188, 230, 69, 69);
                    if (mouseManager.isIzquierdo()) {
                        levelSelected = 2;
                        chooseMenu = false;
                    }
                } else if (hardRect.intersects(mouseRect)) {
                    g.drawRoundRect(426, height/3 - 13, 188, 230, 69, 69);
                    if (mouseManager.isIzquierdo()) {
                        levelSelected = 3;
                        chooseMenu = false;
                    }
                }
                mouseManager.setIzquierdo(false);
            } else {
                g.drawImage(Assets.titleStartScreen, width/2-200, height/4, 401, 57, null);
                g.drawImage(Assets.jugarStartScreen, width/2-100, height*3/5, 196, 49, null);
                Rectangle rectJugar = new Rectangle (0, height*3/5, 640, 49);
                g.drawImage(Assets.eligeBactStartScreen, width/2-250, height*4/5, 505, 47, null);
                Rectangle eligeBact = new Rectangle (0, height*4/5, 640, 47);

                if (rectJugar.intersects(mouseManager.getPerimeter())) {
                    g.drawImage(Assets.cursorStartScreen, 0, height * 3 / 5, 640, 49, null);
                    if (mouseManager.isIzquierdo())
                        startScreen = false;
                } else if (eligeBact.intersects(mouseManager.getPerimeter())) {
                    g.drawImage(Assets.cursorStartScreen, 0, height*4/5, 640, 49, null);
                    if (mouseManager.isIzquierdo())
                        chooseMenu = true;
                }
            }
            
            // Fixes stutter on Linux.
            Toolkit.getDefaultToolkit().sync();
                
            bs.show();
            g.dispose();
        }

            
    }
    
    /**
     * render de game, display images
     */
    private void renderStarted() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            for(Elicitador met : elicitadores){
                met.render(g);
            }
            for(Antibiotico anti : antibioticos){
                anti.render(g);
            }
            for(Receptor recep : receptores){
                recep.render(g);
            }
            barra.render(g);

            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("Antibioticos: ", 80, 30);
            g.drawString(""+player.getAntibioticosSize(), 200, 30);
            g.setColor(Color.white);

            if (pause) {
                g.drawImage(Assets.pauseScreen, 0, 0, 640, 640, null);
                g.drawImage(Assets.volver, 5, 320, 660, 50, null);
                Rectangle rectVolver = new Rectangle(0, 320, 640, 50);
                if (rectVolver.intersects(mouseManager.getPerimeter())) {
                    g.drawImage(Assets.cursorStartScreen, 0, 320, 640, 50, null);
                    if (mouseManager.isIzquierdo()) {
                        pause = false;
                        pauseStun = 0;
                        startScreen = true;
                    }
                }
            }
              
            if(finished) {
                if(player.isAlive()){
                    g.drawImage(Assets.gameWin, 0, 0, 640, 640, null);
                } else {
                    g.drawImage(Assets.gameOver, 0, 0, 640, 640, null);
                }
            }
            
            // Fixes stutter on Linux.
	        Toolkit.getDefaultToolkit().sync();
                
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * Run the game 
     */
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            System.exit(0);
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
    
}

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
import java.sql.*;

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
    int shootStun;
    private int levelSelected;          // the level selected. 1: easy  2: medium   3: hard
    private Animation bact0, bact1, bact2; // to display the animated bacterias in chooseMenu
    
    private boolean pause;
    private boolean finished;
    private boolean startScreen;
    private boolean chooseMenu;
    private boolean instrucciones;
    
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
     * To restart all important variables before a new game.
     */
    void restartVariables() {
        player = new Player(this, width/2 -(Constants.PLAYER_WIDTH/2), height/2-(Constants.PLAYER_HEIGHT/2));
        elicRandom = Constants.RANDOM_INDEX;
        elicitadores = new LinkedList<>();
        antibioticos = new LinkedList<>();
        receptores = Constants.initReceptores(this);
        finished = false;
        pauseStun = 0;

        bg1X = 0;
        bg2X = Constants.GAME_WIDTH;
        bgMoveDelay = 1;
        bgMoveDelayCounter = 0;

        shootStun = Constants.SHOOT_STUN;
        
        Assets.back_sound_01.stop();
        Assets.back_sound_02.stop();
        Assets.back_sound_03.stop();
        Assets.back_sound_start_screen.stop();
        
        switch(levelSelected){
            case 1:
                Assets.back_sound_01.play();
                break;
            case 2:
                Assets.back_sound_02.play();
                break;
            case 3:
                Assets.back_sound_03.play();
                break;
        }

        barra = new EstresBarra(this,20,height-32,5*player.getEstres(),Constants.BARRA_HEIGHT,0);
     }

    /**
     * initializing the display window of the game
     */
    private void init() {
        startScreen = true;
        chooseMenu = false;
        instrucciones = false;
        levelSelected = 1;
        display = new Display(title, getWidth(), getHeight());
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        Constants.init();
        
        Assets.back_sound_01.stop();
        Assets.back_sound_02.stop();
        Assets.back_sound_03.stop();
        Assets.back_sound_start_screen.stop();

        bact0 = new Animation(Assets.bacteria0, 100);
        bact1 = new Animation(Assets.bacteria1, 100);
        bact2 = new Animation(Assets.bacteria2, 100);

        restartVariables();
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
    
    /**
     * To get the level the player selected
     *
     * @return an <code>int</code> value with the level selected
     */
    public int getLevel(){
        return levelSelected;
    }
    
    // Finaliza el juego
    public void endGame(){
        this.finished = true;
    }
    
    // We want the game to tick only if our actual
    // game has started. Else, just render the start screen
    private void tick() {
        if (!startScreen) {
            tickStarted();
        }       
    }
    
    
    /**
     * Control movement of all instances of the game
     */
    private void tickStarted() {
        keyManager.tick();
        
        // To pause and unpause.
        if (pauseStun++ > PAUSE_INTERVAL)
        	pauseStun = PAUSE_INTERVAL + 1;
        if (keyManager.pReleased) {
            if (pauseStun > PAUSE_INTERVAL) {
                pause = !pause;
                pauseStun = 0;
            }
        }
        
        // To save.
        if (keyManager.g && !finished) {
            try {
                grabarArchivo();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        // To load.
        if (keyManager.c) {
            this.restartVariables();
            startScreen = false;
            chooseMenu = false;
            instrucciones = false;
            try {
                leeArchivo();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            pause = true;
        }
        
        // To restart the game
        if (keyManager.r) {
            restartVariables();
            Assets.back_sound_01.stop();
            Assets.back_sound_02.stop();
            Assets.back_sound_03.stop();
            startScreen = true;
            pause = false;
        }
        
        // Only "advance" the game is we do not
        // have any of this conditions
        if(finished||pause||startScreen)
            return;
        
        shootStun--;
        if(mouseManager.isIzquierdo()) {
            if(player.hasAntibiotico() && shootStun <= 0) {
                shootStun = Constants.SHOOT_STUN;
                if (player.hasAntibiotico()) {
                    Antibiotico anti = player.getAntibiotico();
                    anti.disparar(player.getMidX()+5, player.getMidY()+5, mouseManager.getY()-player.getMidY(),mouseManager.getX()-player.getMidX());
                    antibioticos.add(anti);
                }
            }
        }
        
        // Load wikipedia pages for the receptors
        if(mouseManager.isDerecho()){
            for(Receptor recep : receptores){
                if(recep.getCircShape().contains(mouseManager.getPoint())){
                    pause = true;
                    try {
                        java.awt.Desktop.getDesktop().browse(recep.getURI());
                    } catch (URISyntaxException | IOException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            mouseManager.setDerecho(false);
        }

        // Creation of the elicitors, spawned random
        elicRandom += Constants.RANDOM_INCREASE;
        double rand = Math.random();
        int randDir; //1 arriba, 2 abajo, 3izq, 4 der
        if(rand<elicRandom){
            randDir = (int)(Math.random() * 4 + 1);
            //(game, x, y, width, height, speed);
            if (randDir == 1) { //arriba
                elicitadores.add(new Elicitador(this,(int)(Math.random()* (width - 160) + 60),-15,10,10,3,1));
            } else if (randDir == 2){ //abajo
                elicitadores.add(new Elicitador(this,(int)(Math.random()*(width - 160) + 60),getHeight()+5,10,10,3,2));
            } else if (randDir == 3) { //izquierda
                elicitadores.add(new Elicitador(this,0,(int)(Math.random()*(height - 160) + 50),10,10,3,3));
            } else { //derecha
                elicitadores.add(new Elicitador(this,getWidth()+5,(int)(Math.random()*(height - 160) + 50),10,10,3,4));
            }
        }
        
        // Check for intersections with the player, elicitadores or receptores
        for(Elicitador elic : elicitadores){
            if(!elic.isExploded() && elic.getCircShape().intersects(player.getRectShape())){
                player.estresar();
                elic.explode();
                Assets.grab.play();
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
        
        // Tick all of the objects on our game
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
        
        // See how many receptors we have left
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
        
        // Do we need to end the game now?
        boolean theEnd = true;
        for(int i=0; i<cargas.length; i++){
            if(player.cargaEsto(i)&&cargas[i]>0){
                theEnd = false;
            }
        }
        if(theEnd){
            finished = true;
        }
        
        // Update stress bar
        barra.setWidth((int)(width*0.009*player.getEstres()));
        barra.tick();
    }

    /**
     * Stores the information of the game
     * @return 
     */
    public String toString(){
        return levelSelected+"";
    }

    /**
     * Reads the file and changes this instance of the game to emulate it
     * @param datos the string it recieves
     */
    public void loadFromString(String[] datos){
        this.levelSelected = Integer.parseInt(datos[0]);
    }

    /**
     * It calls for the creation of a file full of strings
     * containing the information of all our save state
     * @throws IOException if the file can not be written
     */
    public void grabarArchivo() throws IOException {
        PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo));
        fileOut.println(this.toString());
        fileOut.println(player.toString());
        for(Receptor r : receptores){
            fileOut.println(r.toString());
        }
        fileOut.close();
        //TODO if user is connected, log his info to the database
    }

    /**
     * Calls for the read and fragmentation of our save file
     * and calls for every object to read form its part and
     * emulate the game
     * @throws IOException if the file cannot be read
     */
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
        for(Receptor r : receptores){
            r.loadFromString(fileIn.readLine().split("\\s+"));
        }
        fileIn.close();
    }

    /**
     * chooses between the normal render of the game
     * or the one specialized to the start screen
     */
    private void render() {
        if (startScreen) {
            renderStartscreen();
        } else {
            renderStarted();
        }
            
    }
    
    /**
     * renders the startscreen
     */
    private void renderStartscreen() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            
            g.drawImage(Assets.backgroundStartScreen, bg1X, 0, width, height, null);
            g.drawImage(Assets.backgroundStartScreen, bg2X, 0, width, height, null);
            
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 3));
            g.drawString(":", 0, 0);
            
            
            Assets.back_sound_01.stop();
            Assets.back_sound_02.stop();
            Assets.back_sound_03.stop();
            
            Assets.back_sound_start_screen.play();
            
            if (bgMoveDelayCounter++ >= bgMoveDelay) {
                bg1X--;
                bg2X--;
                bgMoveDelayCounter = 0;
            }
            
            keyManager.tick();
            if (keyManager.c) {
                this.restartVariables();
                startScreen = false;
                chooseMenu = false;
                instrucciones = false;
                try {
                    leeArchivo();
                    Assets.start.play();
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                pause = true;
            }
            
            // Make the background images wrap around.
            if (bg1X <= -1 * width)
                bg1X = width;
            if (bg2X <= -1 * width)
                bg2X = width;
            
            Rectangle mouseRect = mouseManager.getPerimeter();
            
            if (chooseMenu) {
                g.drawImage(Assets.chooseMenu, 0, 0, 640, 640, null);
                g.drawImage(Assets.ls, width/2-60, height/3 + 280, player.getWidth() + 40, player.getHeight(), null);
                
                RoundRectangle2D.Double easyRect = new RoundRectangle2D.Double(26, height/3 - 13, 188, 230, 69, 69);
                RoundRectangle2D.Double mediumRect = new RoundRectangle2D.Double(226, height/3 - 13, 188, 230, 69, 69);
                RoundRectangle2D.Double hardRect = new RoundRectangle2D.Double(426, height/3 - 13, 188, 230, 69, 69);
                RoundRectangle2D.Double ls =
                        new RoundRectangle2D.Double(width/2-60, height/3 + 280, player.getWidth() + 40, player.getHeight(), 69, 69);
                
                bact0.tick();
                bact1.tick();
                bact2.tick();
                
                g.drawImage(bact0.getCurrentFrame(), 50, height/3 + 35, player.getWidth() + 40, player.getHeight(), null);
                g.drawImage(bact1.getCurrentFrame(), 250, height/3 + 35, player.getWidth() + 40, player.getHeight(), null);
                g.drawImage(bact2.getCurrentFrame(), 450, height/3 + 35, player.getWidth() + 40, player.getHeight(), null);
                
                g.setColor(Color.lightGray);
                if (easyRect.intersects(mouseRect)) {
                    g.drawRoundRect(26, height/3 - 13, 188, 230, 69, 69);
                    if (mouseManager.isIzquierdo()) {
                        Assets.choose.play();
                        levelSelected = 1;
                        for(Receptor recep : receptores){
                            recep.changeDirLev13();
                        }
                        chooseMenu = false;
                    }
                } else if (mediumRect.intersects(mouseRect)) {
                    g.drawRoundRect(226, height/3 - 13, 188, 230, 69, 69);
                    if (mouseManager.isIzquierdo()) {
                        Assets.choose.play();
                        levelSelected = 2;
                        for(Receptor recep : receptores){
                            recep.changeDirLev2();
                        }
                        chooseMenu = false;
                    }
                } else if (hardRect.intersects(mouseRect)) {
                    g.drawRoundRect(426, height/3 - 13, 188, 230, 69, 69);
                    if (mouseManager.isIzquierdo()) {
                        Assets.choose.play();
                        levelSelected = 3;
                        for(Receptor recep : receptores){
                            recep.changeDirLev13();
                        }
                        chooseMenu = false;
                    }
                } else if (ls.intersects(mouseRect)) {
                    if (mouseManager.isIzquierdo()) {
                        LogInSignUp field = new LogInSignUp();
                        field.setVisible(true);
                        Assets.choose.play();
                        levelSelected = 3;
                        for(Receptor recep : receptores){
                            recep.changeDirLev13();
                        }
                        chooseMenu = false;
                    }
                } 
                mouseManager.setIzquierdo(false);
            } else if (instrucciones) {
                g.drawImage(Assets.backgroundStartScreenTuto, 0, 0, 640, 640, null);
                Rectangle volver = new Rectangle (0, height*9/10, 640, 100);
                if ((volver.intersects(mouseManager.getPerimeter())) && (mouseManager.isIzquierdo())) {
                	mouseManager.setIzquierdo(false);
                    instrucciones = false;
                }
            } else {
                g.drawImage(Assets.titleStartScreen, width/2-300, 50, 600, 100, null);
                g.drawImage(Assets.jugarStartScreen, width/2-100, height*3/6, 196, 49, null);
                
                bact0.tick();
                bact1.tick();
                bact2.tick();
                
                switch(levelSelected){
                    case 1:
                        g.drawImage(bact0.getCurrentFrame(), width/2-75, height*1/4, 150, 150, null);
                        break;
                    case 2:
                        g.drawImage(bact1.getCurrentFrame(), width/2-75, height*1/4, 150, 150, null);
                        break;
                    case 3:
                        g.drawImage(bact2.getCurrentFrame(), width/2-75, height*1/4, 150, 150, null);
                        break;
                }
                Rectangle rectJugar = new Rectangle (0, height*3/6, 640, 49);
                g.drawImage(Assets.eligeBactStartScreen, width/2-250, height*4/6, 505, 47, null);
                Rectangle eligeBact = new Rectangle (0, height*4/6+20, 640, 47);
                g.drawImage(Assets.instrucciones, width/2-216, height*5/6, 428, 50, null);
                Rectangle instr = new Rectangle (0, height*5/6, 640, 50);

                if (rectJugar.intersects(mouseManager.getPerimeter())) {
                    g.drawImage(Assets.cursorStartScreen, 0, height * 3 / 6, 640, 49, null);
                    if (mouseManager.isIzquierdo()) {
                        Assets.start.play();
                        mouseManager.setIzquierdo(false);
                        restartVariables();
                        startScreen = false;
                    }
                } else if (eligeBact.intersects(mouseManager.getPerimeter())) {
                    g.drawImage(Assets.cursorStartScreen, 0, height*4/6, 640, 49, null);
                    if (mouseManager.isIzquierdo()) {
                    	mouseManager.setIzquierdo(false);
                        chooseMenu = true;
                    }
                } else if (instr.intersects(mouseManager.getPerimeter())) {
                    g.drawImage(Assets.cursorStartScreen, 0, height*5/6, 640, 49, null);
                    if (mouseManager.isIzquierdo()) {
                    	mouseManager.setIzquierdo(false);
                    	instrucciones = true;
                    }
                }
            }
            
            // Fixes stutter on Linux.
            Toolkit.getDefaultToolkit().sync();
                
            bs.show();
            g.dispose();
        }

            
    }
    
    /**
     * renders the core game
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

            if (pause) {
                g.drawImage(Assets.pauseScreen, 0, 0, 640, 640, null);
                g.drawImage(Assets.volver, 5, 320, 660, 50, null);
                Rectangle rectVolver = new Rectangle(0, 320, 640, 50);
                if (rectVolver.intersects(mouseManager.getPerimeter())) {
                    g.drawImage(Assets.cursorStartScreen, 0, 320, 640, 50, null);
                    if (mouseManager.isIzquierdo()) {
                        pause = false;
                        pauseStun = 0;
                        mouseManager.setIzquierdo(false);
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

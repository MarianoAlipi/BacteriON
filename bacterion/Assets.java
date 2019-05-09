/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import pure_engine.ImageLoader;
import pure_engine.SoundClip;
import pure_engine.SpriteSheet;
import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.SwingUtilities;

/**
 *
 * @author Diego
 */
public class Assets {
    
    public static BufferedImage bacteria0[];
    public static BufferedImage spritesBacteria0;
    public static BufferedImage bacteria0Cargada0[];
    public static BufferedImage bacteria0Cargada1[];
    public static BufferedImage bacteria0Cargada2[];
    public static BufferedImage bacteria0Cargada3[];
    public static BufferedImage bacteria0Cargada4[];
    
    public static BufferedImage bacteria1[];
    public static BufferedImage spritesBacteria1;
    public static BufferedImage bacteria1Cargada0[];
    public static BufferedImage bacteria1Cargada1[];
    public static BufferedImage bacteria1Cargada2[];
    public static BufferedImage bacteria1Cargada3[];
    public static BufferedImage bacteria1Cargada4[];
    
    public static BufferedImage bacteria2[];
    public static BufferedImage spritesBacteria2;
    public static BufferedImage bacteria2Cargada0[];
    public static BufferedImage bacteria2Cargada1[];
    public static BufferedImage bacteria2Cargada2[];
    public static BufferedImage bacteria2Cargada3[];
    public static BufferedImage bacteria2Cargada4[];
    
    public static BufferedImage background;
    
    public static BufferedImage backgroundStartScreen;
    public static BufferedImage backgroundStartScreenTuto;
    public static BufferedImage titleStartScreen;
    public static BufferedImage jugarStartScreen;
    public static BufferedImage eligeBactStartScreen;
    public static BufferedImage cursorStartScreen;
    public static BufferedImage chooseMenu;
    public static BufferedImage instrucciones;
    public static BufferedImage ls;
    
    public static BufferedImage elicitador;
    public static BufferedImage antibiotico;
    
    public static BufferedImage spritesReceptor;
    public static BufferedImage receptorAzul[];
    public static BufferedImage receptorRojo[];
    public static BufferedImage receptorAmarillo[];
    public static BufferedImage receptorNaranja[];
    public static BufferedImage receptorRosa[];
    
    public static BufferedImage barraBackground;
    public static BufferedImage barraRelajado;
    public static BufferedImage barraEstresado;
    public static BufferedImage barraMortal;
    
    public static BufferedImage antibRojo;
    public static BufferedImage antibRosa;
    public static BufferedImage antibAmarillo;
    public static BufferedImage antibAzul;
    public static BufferedImage antibNaranja;
    
    public static BufferedImage pauseScreen;
    public static BufferedImage gameOver;
    public static BufferedImage gameWin;
    public static BufferedImage volver;
    
    public static SoundClip shoot;
    public static SoundClip die;
    public static SoundClip grab;
    public static SoundClip start;
    public static SoundClip added;
    public static SoundClip choose;
    
    public static MediaPlayer back_sound_01;
    public static MediaPlayer back_sound_02;
    public static MediaPlayer back_sound_03;

    /**
     * initializing the images of the game
     */
    public static void init() {
        
        initBacteria0();
        initBacteria1();
        initBacteria2();
        
        initReceptores();
        
        background = ImageLoader.loadImage("/images/background.png");
        gameOver = ImageLoader.loadImage("/images/game_over.png");
        gameWin = ImageLoader.loadImage("/images/game_win.png");
        backgroundStartScreen = ImageLoader.loadImage("/startscreen/background.png");
        backgroundStartScreenTuto = ImageLoader.loadImage("/startscreen/background_tuto_02.png");
        titleStartScreen = ImageLoader.loadImage("/startscreen/bacterionTitle.png");
        jugarStartScreen = ImageLoader.loadImage("/startscreen/jugar.png");
        eligeBactStartScreen = ImageLoader.loadImage("/startscreen/elegirBacteria.png");
        cursorStartScreen = ImageLoader.loadImage("/startscreen/cursor.png");
        chooseMenu = ImageLoader.loadImage("/startscreen/chooseMenu.png");
        instrucciones = ImageLoader.loadImage("/startscreen/instrucciones.png");
        ls = ImageLoader.loadImage("/startscreen/ls.png");
        
        elicitador = ImageLoader.loadImage("/images/elicitador_placeholder.png");
        antibiotico = ImageLoader.loadImage("/images/antibiotico_placeholder.png");
        
        antibRojo = ImageLoader.loadImage("/images/antib_rojo.png");
        antibNaranja = ImageLoader.loadImage("/images/antib_naranja.png");
        antibAzul = ImageLoader.loadImage("/images/antib_azul.png");
        antibAmarillo = ImageLoader.loadImage("/images/antib_amarillo.png");
        antibRosa = ImageLoader.loadImage("/images/antib_rosa.png");
        
        barraBackground = ImageLoader.loadImage("/images/estres_barra_background.png");
        barraRelajado = ImageLoader.loadImage("/images/barra_no_estresado_placeholder.png");
        barraEstresado = ImageLoader.loadImage("/images/barra_estresado_placeholder.png");
        barraMortal = ImageLoader.loadImage("/images/barra_mortal_placeholder.png");
        
        pauseScreen = ImageLoader.loadImage("/images/pauseScreen.png");
        volver = ImageLoader.loadImage("/images/volver.png");
        
        shoot = new SoundClip("/sounds/disparoBacteria.wav");
        die = new SoundClip("/sounds/muerteReceptor.wav");
        grab = new SoundClip("/sounds/agarraReceptor.wav");
        start = new SoundClip("/sounds/start.wav");
        added = new SoundClip("/sounds/antibioticoNuevo.wav");
        choose = new SoundClip("/sounds/chooseBacteria.wav");
        
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JFXPanel(); // initializes JavaFX environment
                back_sound_01 = new MediaPlayer(new Media(Assets.class.getResource("/sounds/back_sound_01.mp3").toString()));
                back_sound_02 = new MediaPlayer(new Media(Assets.class.getResource("/sounds/back_sound_02.mp3").toString()));
                back_sound_03 = new MediaPlayer(new Media(Assets.class.getResource("/sounds/back_sound_03.mp3").toString()));
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * loads and cuts the sprites/spritesheets for the bacteria used in level 1
     */
    private static void initBacteria0() {
        bacteria0Cargada0 = new BufferedImage[3];
        spritesBacteria0 = ImageLoader.loadImage("/bacterias/bacteria0_cargada_0.png");
        SpriteSheet spritesheetBacteria = new SpriteSheet(spritesBacteria0);
        for (int i = 0; i < 3; i++) {
            bacteria0Cargada0[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria0Cargada1 = new BufferedImage[3];
        spritesBacteria0 = ImageLoader.loadImage("/bacterias/bacteria0_cargada_1.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria0);
        for (int i = 0; i < 3; i++) {
            bacteria0Cargada1[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria0Cargada2 = new BufferedImage[3];
        spritesBacteria0 = ImageLoader.loadImage("/bacterias/bacteria0_cargada_2.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria0);
        for (int i = 0; i < 3; i++) {
            bacteria0Cargada2[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria0Cargada3 = new BufferedImage[3];
        spritesBacteria0 = ImageLoader.loadImage("/bacterias/bacteria0_cargada_3.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria0);
        for (int i = 0; i < 3; i++) {
            bacteria0Cargada3[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria0Cargada4 = new BufferedImage[3];
        spritesBacteria0 = ImageLoader.loadImage("/bacterias/bacteria0_cargada_4.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria0);
        for (int i = 0; i < 3; i++) {
            bacteria0Cargada4[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        spritesBacteria0 = ImageLoader.loadImage("/bacterias/bacteria0.png");
        //SpriteSheet spritesheetBacteria = new SpriteSheet(spritesBacteria);
        spritesheetBacteria = new SpriteSheet(spritesBacteria0);
        bacteria0 = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            bacteria0[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }        
    }
    
    /**
     * loads and cuts the sprites/spritesheets for the bacteria used in level 2
     */
    private static void initBacteria1() {
        bacteria1Cargada0 = new BufferedImage[3];
        spritesBacteria1 = ImageLoader.loadImage("/bacterias/bacteria1_cargada_0.png");
        SpriteSheet spritesheetBacteria = new SpriteSheet(spritesBacteria1);
        for (int i = 0; i < 3; i++) {
            bacteria1Cargada0[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria1Cargada1 = new BufferedImage[3];
        spritesBacteria1 = ImageLoader.loadImage("/bacterias/bacteria1_cargada_1.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria1);
        for (int i = 0; i < 3; i++) {
            bacteria1Cargada1[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria1Cargada2 = new BufferedImage[3];
        spritesBacteria1 = ImageLoader.loadImage("/bacterias/bacteria1_cargada_2.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria1);
        for (int i = 0; i < 3; i++) {
            bacteria1Cargada2[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria1Cargada3 = new BufferedImage[3];
        spritesBacteria1 = ImageLoader.loadImage("/bacterias/bacteria1_cargada_3.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria1);
        for (int i = 0; i < 3; i++) {
            bacteria1Cargada3[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria1Cargada4 = new BufferedImage[3];
        spritesBacteria1 = ImageLoader.loadImage("/bacterias/bacteria1_cargada_4.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria1);
        for (int i = 0; i < 3; i++) {
            bacteria1Cargada4[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        spritesBacteria1 = ImageLoader.loadImage("/bacterias/bacteria1.png");
        //SpriteSheet spritesheetBacteria = new SpriteSheet(spritesBacteria);
        spritesheetBacteria = new SpriteSheet(spritesBacteria1);
        bacteria1 = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            bacteria1[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
    }
    
    /**
     * loads and cuts the sprites/spritesheets for the bacteria used in level 3
     */
    private static void initBacteria2() {
        bacteria2Cargada0 = new BufferedImage[3];
        spritesBacteria2 = ImageLoader.loadImage("/bacterias/bacteria2_cargada_0.png");
        SpriteSheet spritesheetBacteria = new SpriteSheet(spritesBacteria2);
        for (int i = 0; i < 3; i++) {
            bacteria2Cargada0[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria2Cargada1 = new BufferedImage[3];
        spritesBacteria2 = ImageLoader.loadImage("/bacterias/bacteria2_cargada_1.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria2);
        for (int i = 0; i < 3; i++) {
            bacteria2Cargada1[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria2Cargada2 = new BufferedImage[3];
        spritesBacteria2 = ImageLoader.loadImage("/bacterias/bacteria2_cargada_2.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria2);
        for (int i = 0; i < 3; i++) {
            bacteria2Cargada2[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria2Cargada3 = new BufferedImage[3];
        spritesBacteria2 = ImageLoader.loadImage("/bacterias/bacteria2_cargada_3.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria2);
        for (int i = 0; i < 3; i++) {
            bacteria2Cargada3[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        bacteria2Cargada4 = new BufferedImage[3];
        spritesBacteria2 = ImageLoader.loadImage("/bacterias/bacteria2_cargada_4.png");
        spritesheetBacteria = new SpriteSheet(spritesBacteria2);
        for (int i = 0; i < 3; i++) {
            bacteria2Cargada4[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }
        
        spritesBacteria2 = ImageLoader.loadImage("/bacterias/bacteria2.png");
        //SpriteSheet spritesheetBacteria = new SpriteSheet(spritesBacteria);
        spritesheetBacteria = new SpriteSheet(spritesBacteria2);
        bacteria2 = new BufferedImage[3];
        for (int i = 0; i < 3; i++) {
            bacteria2[i] = spritesheetBacteria.crop(0, i*46,57, 46);
        }        
    }
    
    /**
     * loads and cuts the sprites/spritesheets for the receptors
     */
    private static void initReceptores() {
        SpriteSheet spritesheetReceptores;
        spritesheetReceptores = new SpriteSheet(ImageLoader.loadImage("/images/biosensor_azul.png"));
        receptorAzul = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorAzul[i] = spritesheetReceptores.crop(i*32, 0, 32, 31);
            receptorAzul[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
        
        spritesheetReceptores = new SpriteSheet(ImageLoader.loadImage("/images/biosensor_rojo.png"));
        receptorRojo = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorRojo[i] = spritesheetReceptores.crop(i*32, 0, 32, 31); 
            receptorRojo[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
        
        spritesheetReceptores = new SpriteSheet(ImageLoader.loadImage("/images/biosensor_naranja.png"));
        receptorNaranja = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorNaranja[i] = spritesheetReceptores.crop(i*32, 0, 32, 31); 
            receptorNaranja[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
        
        spritesheetReceptores = new SpriteSheet(ImageLoader.loadImage("/images/biosensor_amarillo.png"));
        receptorAmarillo = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorAmarillo[i] = spritesheetReceptores.crop(i*32, 0, 32, 31); 
            receptorAmarillo[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
        
        spritesheetReceptores = new SpriteSheet(ImageLoader.loadImage("/images/biosensor_rosa.png"));
        receptorRosa = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorRosa[i] = spritesheetReceptores.crop(i*32, 0, 32, 31); 
            receptorRosa[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
    }
}

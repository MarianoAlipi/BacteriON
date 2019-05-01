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

/**
 *
 * @author Diego
 */
public class Assets {
    
    public static BufferedImage bacteria;
    public static BufferedImage bacteriaCargada;
    public static BufferedImage background;
    public static BufferedImage backgroundStartScreen;
    public static BufferedImage titleStartScreen;
    public static BufferedImage jugarStartScreen;
    public static BufferedImage eligeBactStartScreen;
    public static BufferedImage elicitador;
    public static BufferedImage antibiotico;
    //public static BufferedImage receptor;
    public static BufferedImage spritesReceptor;
    public static BufferedImage receptorAzul[];
    public static BufferedImage receptorRojo[];
    public static BufferedImage receptorAmarillo[];
    public static BufferedImage receptorNaranja[];
    public static BufferedImage receptorMuerto;
    public static BufferedImage barraRelajado;
    public static BufferedImage barraEstresado;
    public static BufferedImage pauseScreen;

    /**
     * initializing the images of the game
     */
    public static void init() {
        bacteria = ImageLoader.loadImage("/images/bacteria.png");
        bacteriaCargada = ImageLoader.loadImage("/images/bacteria_cargada.png");
        background = ImageLoader.loadImage("/images/background.png");
        backgroundStartScreen = ImageLoader.loadImage("/startscreen/background.png");
        titleStartScreen = ImageLoader.loadImage("/startscreen/bacterionTitle.png");
        jugarStartScreen = ImageLoader.loadImage("/startscreen/jugar.png");
        eligeBactStartScreen = ImageLoader.loadImage("/startscreen/elegirBacteria.png");
        
        
        elicitador = ImageLoader.loadImage("/images/elicitador_placeholder.png");
        antibiotico = ImageLoader.loadImage("/images/antibiotico_placeholder.png");
        receptorMuerto = ImageLoader.loadImage("/images/receptor_muerto_placeholder.png");
        
        spritesReceptor = ImageLoader.loadImage("/images/biosensores_azules.png");
        SpriteSheet spritesheetReceptores = new SpriteSheet(spritesReceptor);
        receptorAzul = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorAzul[i] = spritesheetReceptores.crop(i*32, 0, 32, 31);
            receptorAzul[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
        
        spritesReceptor = ImageLoader.loadImage("/images/biosensores_rojos.png");
        spritesheetReceptores = new SpriteSheet(spritesReceptor);
        receptorRojo = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorRojo[i] = spritesheetReceptores.crop(i*32, 0, 32, 31); 
            receptorRojo[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
        
        spritesReceptor = ImageLoader.loadImage("/images/biosensores_naranjas.png");
        spritesheetReceptores = new SpriteSheet(spritesReceptor);
        receptorNaranja = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorNaranja[i] = spritesheetReceptores.crop(i*32, 0, 32, 31); 
            receptorNaranja[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
        
        spritesReceptor = ImageLoader.loadImage("/images/biosensores_amarillos.png");
        spritesheetReceptores = new SpriteSheet(spritesReceptor);
        receptorAmarillo = new BufferedImage[12];
        for (int i = 0; i < 6; i++) {
            receptorAmarillo[i] = spritesheetReceptores.crop(i*32, 0, 32, 31); 
            receptorAmarillo[i+6] = spritesheetReceptores.crop(i*32, 31, 32, 31);
        }
        
        
        
        
        barraRelajado = ImageLoader.loadImage("/images/barra_no_estresado_placeholder.png");
        barraEstresado = ImageLoader.loadImage("/images/barra_estresado_placeholder.png");
        
        pauseScreen = ImageLoader.loadImage("/images/pauseScreen.png");
    }
}

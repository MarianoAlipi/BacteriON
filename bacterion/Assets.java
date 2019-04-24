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
    public static BufferedImage elicitador;
    public static BufferedImage antibiotico;
    public static BufferedImage receptor;
    public static BufferedImage receptorMuerto;
    public static BufferedImage barraRelajado;
    public static BufferedImage barraEstresado;

    /**
     * initializing the images of the game
     */
    public static void init() {
        bacteria = ImageLoader.loadImage("/images/bacteria.png");
        bacteriaCargada = ImageLoader.loadImage("/images/bacteria_cargada.png");
        background = ImageLoader.loadImage("/images/background.png");
        
        elicitador = ImageLoader.loadImage("/images/elicitador_placeholder.png");
        antibiotico = ImageLoader.loadImage("/images/antibiotico_placeholder.png");
        receptor = ImageLoader.loadImage("/images/receptor_placeholder.png");
        receptorMuerto = ImageLoader.loadImage("/images/receptor_muerto_placeholder.png");
        
        barraRelajado = ImageLoader.loadImage("/images/barra_no_estresado_placeholder.png");
        barraEstresado = ImageLoader.loadImage("/images/barra_estresado_placeholder.png");
    }
}

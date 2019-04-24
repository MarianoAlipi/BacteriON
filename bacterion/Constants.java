/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import pure_engine.ImageLoader;

/**
 *
 * @author Diego
 */
public class Constants {
    
    public static int PLAYER_HEIGHT;
    public static int PLAYER_WIDTH;
    public static int PLAYER_SPEED;
    public static boolean[] BACT0_CARGAS;
    public static int ESTRES_STUN;
    public static int ESTRES_BAJO;
    public static int ESTRES_ALTO;
    public static int ESTRES_MORTAL;
    public static int ELICIT_HEIGHT;
    public static int ELICIT_WIDTH;
    public static int ELICIT_SPEED;
    public static int ANTIB_HEIGHT;
    public static int ANTIB_WIDTH;
    public static int ANTIB_SPEED;
    public static int RECEP_HEIGHT;
    public static int RECEP_WIDTH;
    public static int RECEP_SPEED;
    public static double RANDOM_INDEX;
    public static double RANDOM_INCREASE;
    /**
     * initializing the images of the game
     */
    public static void init() {
        PLAYER_WIDTH = 100;
        PLAYER_HEIGHT = 100;
        PLAYER_SPEED = 7;
        boolean[] cargas0 = {true,false,false,false};
        BACT0_CARGAS = cargas0;
        ESTRES_STUN = 30;
        ESTRES_BAJO = 30;
        ESTRES_ALTO = 70;
        ESTRES_MORTAL = 100;
        ELICIT_WIDTH = 10;
        ELICIT_HEIGHT = 10;
        ELICIT_SPEED = 5;
        ANTIB_WIDTH = 10;
        ANTIB_HEIGHT = 10;
        ANTIB_SPEED = 10;
        RECEP_WIDTH = 10;
        RECEP_HEIGHT = 10;
        RECEP_SPEED = 0;
        RANDOM_INDEX = 0.03;
        RANDOM_INCREASE = 0.0000005;
    }
    
    public static LinkedList<Receptor> initReceptores(Game g){
        LinkedList<Receptor> receptores = new LinkedList<>();
        receptores.add(new Receptor(g,015,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        receptores.add(new Receptor(g,015,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        receptores.add(new Receptor(g,015,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        receptores.add(new Receptor(g,030,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        receptores.add(new Receptor(g,030,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        receptores.add(new Receptor(g,030,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        receptores.add(new Receptor(g,045,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        receptores.add(new Receptor(g,045,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        receptores.add(new Receptor(g,045,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.TYPE0));
        return receptores;
    }
}

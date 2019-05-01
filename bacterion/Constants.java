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
    public static String E_COLI_URL;
    public static String B_SUBTILIS_URL;
    public static String P_AERUGINOSA_URL;
    public static String S_PNEUMONIAE_URL;
    public static String S_DYSENTERIAE_URL;
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
        ESTRES_BAJO = 40;
        ESTRES_ALTO = 65;
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
        RANDOM_INCREASE = 0.000005;
        E_COLI_URL = "https://es.wikipedia.org/wiki/Escherichia_coli";
        B_SUBTILIS_URL = "https://es.wikipedia.org/wiki/Bacillus_subtilis";
        P_AERUGINOSA_URL = "https://es.wikipedia.org/wiki/Pseudomonas_aeruginosa";
        S_PNEUMONIAE_URL = "https://es.wikipedia.org/wiki/Streptococcus_pneumoniae";
        S_DYSENTERIAE_URL = "https://es.wikipedia.org/wiki/Shigella_dysenteriaehttps://es.wikipedia.org/wiki/Escherichia_coli";
    }
    
    public static LinkedList<Receptor> initReceptores(Game g){
        LinkedList<Receptor> receptores = new LinkedList<>();
        
        //arriba izq
        receptores.add(new Receptor(g,015,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,015,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,015,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,030,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,030,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,030,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,045,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,045,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,045,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        //arriba enmedio
        receptores.add(new Receptor(g,290,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        receptores.add(new Receptor(g,290,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        receptores.add(new Receptor(g,290,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        receptores.add(new Receptor(g,305,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        receptores.add(new Receptor(g,305,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        receptores.add(new Receptor(g,305,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        receptores.add(new Receptor(g,320,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        receptores.add(new Receptor(g,320,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        receptores.add(new Receptor(g,320,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
        //arriba der
        receptores.add(new Receptor(g,585,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,585,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,585,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,600,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,600,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,600,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,615,015,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,615,030,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,615,045,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        //enmedio izq
        receptores.add(new Receptor(g,015,290,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,015,305,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,015,320,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,030,290,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,030,305,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,030,320,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,045,290,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,045,305,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,045,320,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        //enmedio der
        receptores.add(new Receptor(g,585,290,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        receptores.add(new Receptor(g,585,305,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        receptores.add(new Receptor(g,585,320,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        receptores.add(new Receptor(g,600,290,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        receptores.add(new Receptor(g,600,305,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        receptores.add(new Receptor(g,600,320,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        receptores.add(new Receptor(g,615,290,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        receptores.add(new Receptor(g,615,305,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        receptores.add(new Receptor(g,615,320,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
        //abajo izq
        receptores.add(new Receptor(g,015,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,015,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,015,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,030,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,030,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,030,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,045,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,045,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        receptores.add(new Receptor(g,045,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
        //abajo enmedio
        receptores.add(new Receptor(g,290,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,290,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,290,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,305,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,305,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,305,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,320,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,320,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        receptores.add(new Receptor(g,320,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
        //abajo der
        receptores.add(new Receptor(g,585,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,585,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,585,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,600,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,600,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,600,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,615,585,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,615,600,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        receptores.add(new Receptor(g,615,615,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
        
        return receptores;
    }
}

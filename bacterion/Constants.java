/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class Constants {
    
    public static int GAME_WIDTH;
    public static int GAME_HEIGHT;
    public static int PLAYER_HEIGHT;
    public static int PLAYER_WIDTH;
    public static int PLAYER_SPEED;
    public static int SHOOT_STUN;
    public static int MOV_OFFSET;
    public static boolean[] BACT0_TARGET;
    public static int BARRA_WIDTH;
    public static int BARRA_HEIGHT;
    public static int ESTES_INICIAL;
    public static int ESTRES_INCREASE;
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
        GAME_WIDTH = 640;
        GAME_WIDTH = 640;
        PLAYER_WIDTH = 100;
        PLAYER_HEIGHT = 100;
        PLAYER_SPEED = 7;
        SHOOT_STUN = 10;
        BARRA_WIDTH = 576;
        BARRA_HEIGHT = 16;
        MOV_OFFSET = 50;
        boolean[] cargas0 = {true,false,false,false,true};
        BACT0_TARGET = cargas0;
        ESTES_INICIAL = 20;
        ESTRES_INCREASE = 5;
        ESTRES_STUN = 20;
        ESTRES_BAJO = 35;
        ESTRES_ALTO = 65;
        ESTRES_MORTAL = 100;
        ELICIT_WIDTH = 10;
        ELICIT_HEIGHT = 10;
        ELICIT_SPEED = 5;
        ANTIB_WIDTH = 10;
        ANTIB_HEIGHT = 10;
        ANTIB_SPEED = 10;
        RECEP_WIDTH = 20;
        RECEP_HEIGHT = 20;
        RECEP_SPEED = 0;
        RANDOM_INDEX = 0.03;
        RANDOM_INCREASE = 0.000005;
        E_COLI_URL = "https://es.wikipedia.org/wiki/Escherichia_coli";
        B_SUBTILIS_URL = "https://es.wikipedia.org/wiki/Bacillus_subtilis";
        P_AERUGINOSA_URL = "https://es.wikipedia.org/wiki/Pseudomonas_aeruginosa";
        S_PNEUMONIAE_URL = "https://es.wikipedia.org/wiki/Streptococcus_pneumoniae";
        S_DYSENTERIAE_URL = "https://es.wikipedia.org/wiki/Shigella_dysenteriae";
    }
    
    public static LinkedList<Receptor> initReceptores(Game g){
        LinkedList<Receptor> receptores = new LinkedList<>();
        
        int xOffset, yOffset;
        int separacion = 15;
        int dimens = 2;
        //arriba izq
        xOffset = 15;
        yOffset = 15;
        for(int i=0; i<dimens; i++){
            for(int j=0; j<dimens; j++){
                receptores.add(new Receptor(g,xOffset+separacion*i,yOffset+separacion*j,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
            }
        }
        
        //arriba centro
        xOffset = 290;
        yOffset = 15;
        for(int i=0; i<dimens; i++){
            for(int j=0; j<dimens; j++){
                receptores.add(new Receptor(g,xOffset+separacion*i,yOffset+separacion*j,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
            }
        }
        
        //arriba derecha
        xOffset = 560;
        yOffset = 15;
        for(int i=0; i<dimens; i++){
            for(int j=0; j<dimens; j++){
                receptores.add(new Receptor(g,xOffset+separacion*i,yOffset+separacion*j,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
            }
        }
        
        //centro izq
        xOffset = 15;
        yOffset = 290;
        for(int i=0; i<dimens; i++){
            for(int j=0; j<dimens; j++){
                receptores.add(new Receptor(g,xOffset+separacion*i,yOffset+separacion*j,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.B_SUBTILIS));
            }
        }
        
        //centro derecha
        xOffset = 560;
        yOffset = 290;
        for(int i=0; i<dimens; i++){
            for(int j=0; j<dimens; j++){
                receptores.add(new Receptor(g,xOffset+separacion*i,yOffset+separacion*j,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_PNEUMONIAE));
            }
        }
        
        //abajo izq
        xOffset = 15;
        yOffset = 540;
        for(int i=0; i<dimens; i++){
            for(int j=0; j<dimens; j++){
                receptores.add(new Receptor(g,xOffset+separacion*i,yOffset+separacion*j,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.E_COLI));
            }
        }
        
        //abajo centro
        xOffset = 290;
        yOffset = 540;
        for(int i=0; i<dimens; i++){
            for(int j=0; j<dimens; j++){
                receptores.add(new Receptor(g,xOffset+separacion*i,yOffset+separacion*j,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.P_AERUGINOSA));
            }
        }
        
        //abajo derecha
        xOffset = 560;
        yOffset = 540;
        for(int i=0; i<dimens; i++){
            for(int j=0; j<dimens; j++){
                receptores.add(new Receptor(g,xOffset+separacion*i,yOffset+separacion*j,Constants.RECEP_WIDTH,Constants.RECEP_HEIGHT,Constants.RECEP_SPEED,AntiType.S_DYSENTERIAE));
            }
        }
        
        return receptores;
    }
}

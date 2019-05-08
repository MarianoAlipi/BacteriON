/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.awt.Graphics;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class Receptor extends Item{
    
    private boolean exploded;
    private AntiType tipo;
    private int pos; //1 top left, 2 bottom left, 3 top right, 4 bottom right 
    private Animation animationReceptor;
    private URI URI;
    private int count = 0;
    private int dir; //1 arriba, 2 abajo, 3 izq, 4 der
    private int dirOG;
    
    public Receptor(Game game, int x, int y, int width, int height, int speed, AntiType tipo, int pos, int dir){
        super(game, x, y, width, height, speed);
        this.tipo = tipo;
        this.pos = pos;
        switch(tipo){
            case E_COLI: this.animationReceptor = new Animation(Assets.receptorAzul, height); break;
            case B_SUBTILIS: this.animationReceptor = new Animation(Assets.receptorRojo, height); break;
            case P_AERUGINOSA: this.animationReceptor = new Animation(Assets.receptorAmarillo, height); break;
            case S_PNEUMONIAE: this.animationReceptor = new Animation(Assets.receptorNaranja, height); break;
            case S_DYSENTERIAE: this.animationReceptor = new Animation(Assets.receptorRosa, height); break;
            default: this.animationReceptor = new Animation(Assets.receptorNaranja, height); break;
        }
        try {
            switch(tipo){
                case E_COLI: this.URI = new java.net.URI(Constants.E_COLI_URL); break;
                case B_SUBTILIS: this.URI = new java.net.URI(Constants.B_SUBTILIS_URL); break;
                case P_AERUGINOSA: this.URI = new java.net.URI(Constants.P_AERUGINOSA_URL); break;
                case S_PNEUMONIAE: this.URI = new java.net.URI(Constants.S_PNEUMONIAE_URL); break;
                case S_DYSENTERIAE: this.URI = new java.net.URI(Constants.S_DYSENTERIAE_URL); break;
                default: this.URI = new java.net.URI("http://urlshida.com"); break;
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dir = dir;
        dir = dirOG;
        if (game.getLevel() == 2) {
            changeDirLev2();
        } 
    }
    
    public AntiType getTipo(){
        return tipo;
    }
    
    public void changeDirLev2(){
        dir = (int) (Math.random() * 4 -1);
        if (dir > 0) {
            dir = 1;
        } else {
            dir = -1;
        }
    }
    
    public void changeDirLev13(){
        dir = dirOG;
    }
    
    public int getTipoInt(){
        switch(tipo){
            case E_COLI: return 0;
            case B_SUBTILIS: return 1;
            case P_AERUGINOSA: return 2;
            case S_PNEUMONIAE: return 3;
            case S_DYSENTERIAE: return 4;
            default: return -1;
        }
    }
    
    public URI getURI() throws URISyntaxException{
        return this.URI;
    }
    
    public boolean isExploded(){
        return exploded;
    }
    
    public void explode(){
        Assets.die.play();
        exploded = true;
        switch(game.getLevel()){
            case 1:
                if(!Constants.BACT0_CARGAS[this.getTipoInt()]){
                    setX(-20);
                    setY(-20);
                }
                break;
            case 2:
                if(!Constants.BACT1_CARGAS[this.getTipoInt()]){
                    setX(-20);
                    setY(-20);
                }
                break;
            case 3:
                if(!Constants.BACT2_CARGAS[this.getTipoInt()]){
                    setX(-20);
                    setY(-20);
                }
                break;
        }
    }
    
    /**
     * Control receptor movement
     */
    @Override
    public void tick() {
        this.animationReceptor.tick();
        switch (game.getLevel()) {
            case 1:
                //no hacen nada
                break;
            case 2:
                tick2();
                break;
            case 3:
                tick3();
                break;
        }  
    }
    
    public String toString(){
        return (x+" "+y+" "+exploded);
    }
    
    public void loadFromString(String[] datos){
        this.x = Integer.parseInt(datos[0]);
        this.y = Integer.parseInt(datos[1]);
        this.exploded = Boolean.parseBoolean(datos[2]);
    }
    
    
    /**
     * Control receptor movement when the level Selected is 2 (medium)
     */
    public void tick2() {
        if (count > 25){
            count = 0;
            dir *= -1;
        }
        setX(getX()+(1*dir));
        setY(getY()+(1*dir));
        count ++;
    }
    
    /**
     * Control receptor movement when the level Selected is 1 (hard)
     */
    public void tick3() {
        //1 arriba, 2 abajo, 3 izq, 4 der   //DIR
        //1 top left, 2 bottom left, 3 top right, 4 bottom right //POS
        //checa si tienen que cambiar de lado
        switch (dir) {
            case 1:
                //si va para arriba
                if ((pos == 1 && getY() <= 15) || (pos == 3 && getY() <= 15) || //si está en la top left o right y ya llegó arriba
                    (pos == 2 && getY() <= 30) || (pos == 4 && getY() <= 30)) { //si está en el bottom left o right y ya llegó arriba
                    dir = 3;
                }   break;
            case 2:
                //si va para abajo
                if ((pos == 1 && getY() >= 540) || (pos == 3 && getY() >= 540) || //si está en la top left o right y ya llegó abajo
                    (pos == 2 && getY() >= 555) || (pos == 4 && getY() >= 555)) { //si está en el bottom left o right y ya llegó abajo
                    dir = 4;
                }   break;
            case 3:
                //si va para la izq
                if ((pos == 1 && getX() <= 15) || (pos == 2 && getX() <= 15) || //si está en la top o bottom left y ya llegó a la izq
                    (pos == 3 && getX() <= 30) || (pos == 4 && getX() <= 30)) { //si está en el top o bottom right y ya llegó a la izq
                    dir = 2;
                }   break;
            case 4:
                //si va para la derecha
                if ((pos == 1 && getX() >= 560) || (pos == 2 && getX() >= 560) || //si está en la top o bottom left y ya llegó a la izq
                    (pos == 3 && getX() >= 575) || (pos == 4 && getX() >= 575)) { //si está en el top o bottom right y ya llegó a la izq
                    dir = 1;
                }   break;
            default:
                break;
        }
        
        switch (dir) {
            case 1:
                //si va para arriba
                setY(getY()-1);
                break;
            case 2:
                //si va para abajo
                setY(getY()+1);
                break;
            case 3:
                //si va para la izq
                setX(getX()-1);
                break;
            case 4:
                //si va para la der
                setX(getX()+1);
                break;
            default:
                break;
        }
    }

    /**
     * render the image of the player 
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        if(!exploded){
            g.drawImage(animationReceptor.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}

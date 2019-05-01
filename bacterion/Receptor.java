/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import static bacterion.Assets.receptorAzul;
import java.awt.Graphics;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Diego
 */
public class Receptor extends Item{
    
    private boolean exploded;
    private AntiType tipo;
    private Animation animationReceptor;
    
    public Receptor(Game game, int x, int y, int width, int height, int speed, AntiType tipo, int color){
        super(game, x, y, width, height, speed);
        this.tipo = tipo;
        if (color == 1) {
            this.animationReceptor = new Animation(Assets.receptorAzul, height);
        } else if (color == 2) {
             this.animationReceptor = new Animation(Assets.receptorRojo, height);
        } else if (color == 3) {
            this.animationReceptor = new Animation(Assets.receptorAmarillo, height);
        } else {
            this.animationReceptor = new Animation(Assets.receptorNaranja, height);
        }
        
    }
    
    public AntiType getTipo(){
        return tipo;
    }
    
    public URI getURI() throws URISyntaxException{
        switch(tipo){
            case E_COLI: return new java.net.URI(Constants.E_COLI_URL);
            case B_SUBTILIS: return new java.net.URI(Constants.B_SUBTILIS_URL);
            case P_AERUGINOSA: return new java.net.URI(Constants.P_AERUGINOSA_URL);
            case S_PNEUMONIAE: return new java.net.URI(Constants.S_PNEUMONIAE_URL);
            case S_DYSENTERIAE: return new java.net.URI(Constants.S_DYSENTERIAE_URL);
            default: return new java.net.URI("http://urlshida.com");
        }
    }
    
    public boolean isExploded(){
        return exploded;
    }
    
    public void explode(){
        x = -width;
        y = -height;
        exploded = true;
    }
    
    /**
     * Control receptor movement
     */
    @Override
    public void tick() {
        this.animationReceptor.tick();
    }

    /**
     * render the image of the player 
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        if(!exploded){
            g.drawImage(animationReceptor.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.receptorMuerto, getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}

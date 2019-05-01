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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class Receptor extends Item{
    
    private boolean exploded;
    private AntiType tipo;
    private Animation animationReceptor;
    private URI URI;
    
    public Receptor(Game game, int x, int y, int width, int height, int speed, AntiType tipo){
        super(game, x, y, width, height, speed);
        this.tipo = tipo;
        switch(tipo){
            case E_COLI: this.animationReceptor = new Animation(Assets.receptorAzul, height);
            case B_SUBTILIS: this.animationReceptor = new Animation(Assets.receptorRojo, height);
            case P_AERUGINOSA: this.animationReceptor = new Animation(Assets.receptorAmarillo, height);
            case S_PNEUMONIAE: this.animationReceptor = new Animation(Assets.receptorNaranja, height);
            case S_DYSENTERIAE: this.animationReceptor = new Animation(Assets.receptorNaranja, height);
            default: this.animationReceptor = new Animation(Assets.receptorNaranja, height);
        }
        try {
            switch(tipo){
                case E_COLI: this.URI = new java.net.URI(Constants.E_COLI_URL);
                case B_SUBTILIS: this.URI = new java.net.URI(Constants.B_SUBTILIS_URL);
                case P_AERUGINOSA: this.URI = new java.net.URI(Constants.P_AERUGINOSA_URL);
                case S_PNEUMONIAE: this.URI = new java.net.URI(Constants.S_PNEUMONIAE_URL);
                case S_DYSENTERIAE: this.URI = new java.net.URI(Constants.S_DYSENTERIAE_URL);
                default: this.URI = new java.net.URI("http://urlshida.com");
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AntiType getTipo(){
        return tipo;
    }
    
    public URI getURI() throws URISyntaxException{
        return this.URI;
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

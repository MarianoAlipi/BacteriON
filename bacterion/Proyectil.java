/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.awt.Graphics;

/**
 *
 * @author Diego
 */
public class Proyectil extends Item{
    
    private boolean exploded;
    
    /**
     * Proyectil constructor
     * @param x
     * @param y
     * @param direction
     * @param width
     * @param height
     * @param game 
     */
    public Proyectil(Game game, int x, int y, int width, int height, int speed) {
        super(game, x, y, width, height, speed);
    }
    
    public boolean isExploded(){
        return exploded;
    }
    
    /**
     * Control bad movement
     */
    @Override
    public void tick() {
        setY(y-speed);
    }
    
    public void explode(){
        x = -width;
        y = -height;
        exploded = true;
    }
    
    /**
     * Render the image of bad (A Pokeball)
     * @param g 
     */
    @Override
    public void render(Graphics g) {
    }
}

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
public class Elicitador extends Item{
    
    private boolean exploded;
    int dir;
    /**
     * Proyectil constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game 
     * @param dir //1 = de arriba a abajo, 2 = de abajo a arriba, 3 = de izq a der, 4 = de der a izq
     */
    public Elicitador(Game game, int x, int y, int width, int height, int speed, int direction) {
        super(game, x, y, width, height, speed);
        dir = direction;
        if (game.getLevel() == 2) {
            this.speed = (int) (Math.random() * 10 +1);
        } 
    }
    
    public boolean isExploded(){
        return exploded;
    }
    
    /**
     * Control bad movement
     */
    @Override
    public void tick() {
        //1 = de arriba a abajo, 2 = de abajo a arriba, 3 = de izq a der, 4 = de der a izq
        if (game.getLevel() == 3) {
            if (dir == 1) {
                setY(y+speed);
                setX(x+(int)(Math.random() * 15 - 7));
            } else if (dir == 2) {
                setY(y-speed);
                setX(x+(int)(Math.random() * 15 - 7));
            } else if (dir == 3) {
                setX(x+speed);
                setY(y+(int)(Math.random() * 15 - 7));
            } else if (dir == 4) {
                setX(x-speed);
                setY(y+(int)(Math.random() * 15 - 7));
            }
        } 
        if (dir == 1) {
            setY(y+speed);
        } else if (dir == 2) {
            setY(y-speed);
        } else if (dir == 3) {
            setX(x+speed);
        } else if (dir == 4) {
            setX(x-speed);
        }
        
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
        g.drawImage(Assets.elicitador, getX(), getY(), getWidth(), getHeight(), null);
    }
}

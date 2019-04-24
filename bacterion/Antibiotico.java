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
public class Antibiotico extends Item{
    
    private boolean exploded;
    private AntiType tipo;
    private double xPend;
    private double yPend;
    
    /**
     * Proyectil constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game 
     * @param tipo 
     */
    public Antibiotico(Game game, int x, int y, int width, int height, AntiType tipo) {
        super(game, x, y, width, height, 0);
        this.tipo = tipo;
        this.speed = 0;
        this.xPend = 0;
        this.yPend = 0;
    }
    
    public boolean isExploded(){
        return exploded;
    }
    
    /**
     * Control bad movement
     */
    @Override
    public void tick() {
        setX((int)(x+yPend*speed));
        setY((int)(y+xPend*speed));
    }
    
    public void explode(){
        x = -width;
        y = -height;
        exploded = true;
    }
    
    public AntiType getTipo(){
        return tipo;
    }
    
    public void disparar(int x, int y, double xOri, double yOri){
        if(Math.abs(xOri)>Math.abs(yOri)){
            xPend = xOri/Math.abs(xOri);
            yPend = yOri/Math.abs(xOri);
        } else {
            yPend = yOri/Math.abs(yOri);
            xPend = xOri/Math.abs(yOri);
        }
        this.x = x;
        this.y = y;
        this.speed = Constants.ANTIB_SPEED;
    }
    
    /**
     * Render the image of bad (A Pokeball)
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.antibiotico, getX(), getY(), getWidth(), getHeight(), null);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Diego
 */
public class Antibiotico extends Item{
    
    private boolean exploded;
    private AntiType tipo;
    private BufferedImage antibSprite;
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
        
        switch(tipo){
            case E_COLI:
                this.antibSprite = Assets.antibAzul;
                break;
            case B_SUBTILIS:
                this.antibSprite = Assets.antibRojo;
                break;
            case P_AERUGINOSA:
                this.antibSprite = Assets.antibAmarillo;
                break;
            case S_PNEUMONIAE:
                this.antibSprite = Assets.antibNaranja;
                break;
            case S_DYSENTERIAE:
                this.antibSprite = Assets.antibRosa;
                break;
        }
    }
    
    /**
     * checks if the antibiotic has exploded
     * @return a <code>boolean</code> that says if it's exploded or alive
     */
    public boolean isExploded(){
        return exploded;
    }
    
    /**
     * Controls movement
     */
    @Override
    public void tick() {
        setX((int)(x+yPend*speed));
        setY((int)(y+xPend*speed));
    }
    
    /**
     * eliminates antibiotics when out of the screen
     */
    public void explode(){
        x = -width;
        y = -height;
        exploded = true;
    }
    
    /**
     * gets the antibiotics's type
     * @return a <code>AntiType</code> with the type
     */
    public AntiType getTipo(){
        return tipo;
    }
    
    /**
     * gets the antibiotics's type
     * @return a <code>int</code> with the type equivalent in a number
     */
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
    
    /**
     * controls shots 
     * @param x an <code>int</code> with the x position
     * @param y an <code>int</code> with the y position
     * @param xOri an <code>double</code> with the x orientation 
     * @param yOri an <code>double</code> with the y orientation
     */
    public void disparar(int x, int y, double xOri, double yOri){
        Assets.shoot.play();
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
     * Render the image of the antibiotics
     * @param g the <code>Graphics</code> where it's drawn
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(antibSprite, getX(), getY(), getWidth(), getHeight(), null);
    }
}

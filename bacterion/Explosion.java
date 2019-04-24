/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import pure_engine.Animation;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Diego
 */
public class Explosion extends Item{

    private int vidas;
    private Animation animation;
    public int counter = 0;

    /**
     * Player constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game 
     */
    public Explosion(Game game, int x, int y, int width, int height, int speed) {
        super(game, x, y, width, height, speed);
    }
    
    /**
     * Control the player movement 
     */
    @Override
    public void tick() {
        if (counter < 50) {
            this.animation.tick();
//            setX(getX());
//            setY(getY());
            counter++;
        } else{
//            setX(0);
//            setY(0);
        }
    }
    
    public Rectangle2D[] getBordes(){
        Rectangle2D[] bordes = new Rectangle2D[4];
        bordes[0] = new Rectangle2D.Double(getX()+getWidth(),getY(),1,getHeight());
        bordes[1] = new Rectangle2D.Double(getX(),getY()-1,getWidth(),1);
        bordes[2] = new Rectangle2D.Double(getX()-1,getY(),1,getHeight());
        bordes[3] = new Rectangle2D.Double(getX(),getY()+getHeight(),getWidth(),1);
        return bordes;
    }
    
    // Guarda la información del objeto en un string
    public String toString() {
        return (x+" "+y+" "+width + " " + height + " " + speed + " " + vidas);
    }
    
    // Se encarga de guardar en un archivo toda la informacion de nuestra partida
    public void loadFromString(String[] datos) {
        this.x = Integer.parseInt(datos[0]);
        this.y = Integer.parseInt(datos[1]);
        this.width = Integer.parseInt(datos[2]);
        this.height = Integer.parseInt(datos[3]);
        this.speed = Integer.parseInt(datos[4]);
        this.vidas = Integer.parseInt(datos[5]);
    }

    /**
     * render the image of the player 
     * @param g 
     */
    @Override
    public void render(Graphics g) {
    }
}
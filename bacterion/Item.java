/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Diego
 */
public abstract class Item {
    
    protected int x;        // to store x position
    protected int y;        // to store y position
    protected int width;    // to store the width of the object
    protected int height;   // to store the height of the object
    protected int speed;    // velocidad a la que se mueven los objetos
    protected Game game;    // game que todos los objetos guardan
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(Game game, int x, int y, int width, int height, int speed) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    /**
     * Get x value
     * @return x 
     */
    public int getX() {
        return x;
    }

    /**
     * Get y value
     * @return y 
     */
    public int getY() {
        return y;
    }

    /**
     * Get width value
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get height value
     * @return height 
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get width value
     * @return width
     */
    public int getMidX() {
        return x+width/2;
    }

    /**
     * Get height value
     * @return height 
     */
    public int getMidY() {
        return y+height/2;
    }

    /**
     * Set x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Set width value
     * @param width to modify
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set height value
     * @param height to modify
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    // Conseguir la forma del item (rectangular)
    public Rectangle getRectShape() {
        return new Rectangle (x, y, width, height);
    }
    
    // Conseguir la forma del item (circular)
    public Ellipse2D.Double getCircShape() {
        return new Ellipse2D.Double(x, y, width, height);
    }
    
    /**
     * Getter of Speed
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * Setter of speed
     * @param speed 
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * To update positions of the item for every tick
     */
    public abstract void tick();
    
    // Guarda la información del objeto en un string
    public String toString(){
        return (x+" "+y);
    }
    
    // Se encarga de guardar en un archivo toda la informacion de nuestra partida
    public void loadFromString(String[] datos) {
        this.x = Integer.parseInt(datos[0]);
        this.y = Integer.parseInt(datos[1]);
    }
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
}

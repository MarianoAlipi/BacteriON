/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Diego
 */
public class Enemigo extends Item{
    
    private Direction direccion;
    private boolean alive;
    
    /**
     * Enemigo constructor
     * @param x
     * @param y
     * @param direction
     * @param width
     * @param height
     * @param game 
     */
    public Enemigo(Game game, int x, int y, int width, int height, int speed) {
        super(game, x, y, width, height, speed);
        this.direccion = Direction.RIGHT;
        this.alive = true;
    }
    
    public Direction getDirection(){
        return this.direccion;
    }
    
    public void setDirection(Direction dir){
        this.direccion = dir;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    public void setAlive(boolean al){
        this.alive = al;
    }
    
    public void die(){
        this.speed = 0;
        this.x = -width;
        this.y = -height;
        alive = false;
    }

    /**
     * Control bad movement
     */
    @Override
    public void tick() {
        switch (direccion){
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }
    
    // Conseguir el perímetro de los enemigos
    public Rectangle getPerimetro() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    // Guarda la información del objeto en un string
    public String toString(){
        return (x+" "+y+" "+alive);
    }
    
    // Se encarga de guardar en un archivo toda la informacion de nuestra partida
    public void loadFromString(String[] datos) {
        this.x = Integer.parseInt(datos[0]);
        this.y = Integer.parseInt(datos[1]);
        this.alive = Boolean.parseBoolean(datos[2]);
    }
    
    /**
     * Render the image of bad (A Pokeball)
     * @param g 
     */
    @Override
    public void render(Graphics g) {
    }
    
}

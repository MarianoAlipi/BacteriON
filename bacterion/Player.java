/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bacterion;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Diego
 */
public class Player extends Item{
    
    // El estres se mide en porcentaje, 0 -  100
    private int estres;
    private int estresStun;
    private boolean alive;
    private LinkedList<Antibiotico> antibioticos;
    private int[] cargas;
    private int[] cargLimits;
    private boolean[] bactCargas;
    
    /**
     * Player constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game 
     * @param speed
     */
    public Player(Game game, int x, int y) {
        super(game, x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT, Constants.PLAYER_SPEED);
        cargas = new int[4];
        alive = true;
        estres = 10;
        estresStun = 20;
        antibioticos = new LinkedList<>();
        cargas = new int[4];
        cargLimits = new int[4];
        cargLimits[0] = 200;
        cargLimits[1] = 100;
        cargLimits[2] = 150;
        cargLimits[3] = 300;
        this.bactCargas = Constants.BACT0_CARGAS;
    }
    
    public boolean isAlive(){
        return this.alive;
    }
    
    public int getEstres(){
        return estres;
    }
    public void setEstres(int estres){
        this.estres = estres;
    }
    public void estresar(){
        estres+= 5;
    }
    
    public Antibiotico getAntibiotico(){
        return antibioticos.poll();
    }
    public boolean hasAntibiotico(){
        return antibioticos.peek()!=null;
    }
    
    public boolean cargaEsto(int index){
        return bactCargas[index];
    }
    
    /**
     * Control del movimiento del player y su 
     */
    @Override
    public void tick() {
        // moving player depending on flags
        if (game.getKeyManager().left && getX()>=50) {
           setX(getX() - speed);
        } else if (game.getKeyManager().right && getX()+getWidth()<=game.getWidth()-50) {
           setX(getX() + speed);
        }
        if (game.getKeyManager().up && getY()>=50) {
           setY(getY() - speed);
        } else if (game.getKeyManager().down && getY()+getHeight()<=game.getHeight()-50) {
           setY(getY() + speed);
        }
        
        if(estresStun>0){
            estresStun--;
        } else {
            estres--;
            estresStun = Constants.ESTRES_STUN;
        }
        
        if(estres>=Constants.ESTRES_BAJO && estres<=Constants.ESTRES_ALTO){
            for(int i=0; i<bactCargas.length; i++){
                if(bactCargas[i]){
                    cargas[i]++;
                }
            }
        }
        
        if(estres>=Constants.ESTRES_MORTAL){
            alive = false;
            game.endGame();
        }
        
        for(int i=0; i<cargas.length; i++){
            if(cargas[i]>=cargLimits[i]){
                switch(i){
                    case 0:
                        antibioticos.push(
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.TYPE0));
                        break;
                    case 1:
                        antibioticos.push(
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.TYPE1));
                        break;
                    case 2:
                        antibioticos.push(
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.TYPE2));
                        break;
                    case 3:
                        antibioticos.push(
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.TYPE3));
                        break;
                        
                }
                cargas[i] = 0;
            }
        }
    }
    
    // Guarda la información del objeto en un string
    public String toString() {
        return (x+" "+y);
    }
    
    // Se encarga de guardar en un archivo toda la informacion de nuestra partida
    public void loadFromString(String[] datos) {
        this.x = Integer.parseInt(datos[0]);
        this.y = Integer.parseInt(datos[1]);
    }

    /**
     * render the image of the player 
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        if(antibioticos.size()>0){
            g.drawImage(Assets.bacteriaCargada, getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(Assets.bacteria, getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}

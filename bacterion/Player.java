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
    private boolean[] bactTarget;
    private Animation animationBacteria;
    private Animation animationBacteriaCargada0;
    private Animation animationBacteriaCargada1;
    private Animation animationBacteriaCargada2;
    private Animation animationBacteriaCargada3;
    private Animation animationBacteriaCargada4;
    
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
        alive = true;
        estres = Constants.ESTES_INICIAL;
        estresStun = Constants.ESTRES_STUN;
        antibioticos = new LinkedList<>();
        cargas = new int[5];
        cargLimits = new int[5];
        cargLimits[0] = 200;
        cargLimits[1] = 100;
        cargLimits[2] = 150;
        cargLimits[3] = 300;
        cargLimits[4] = 250;
        this.animationBacteria = new Animation(Assets.bacteria, height);
        this.animationBacteriaCargada0 = new Animation(Assets.bacteriaCargada0, height);
        this.animationBacteriaCargada1 = new Animation(Assets.bacteriaCargada1, height);
        this.animationBacteriaCargada2 = new Animation(Assets.bacteriaCargada2, height);
        this.animationBacteriaCargada3 = new Animation(Assets.bacteriaCargada3, height);
        this.animationBacteriaCargada4 = new Animation(Assets.bacteriaCargada4, height);
        // Este array nos indica los sensores target de nuestra bacteria
        this.bactTarget = Constants.BACT0_TARGET;
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
        estres+= Constants.ESTRES_INCREASE;
    }
    
    public int getAntibioticosSize(){
        return antibioticos.size();
    }
    
    public Antibiotico getAntibiotico(){
        return antibioticos.poll();
    }
    public boolean hasAntibiotico(){
        return antibioticos.peek()!=null;
    }
    
    public boolean cargaEsto(int index){
        return bactTarget[index];
    }
    
    /**
     * Control del movimiento del player y su 
     */
    @Override
    public void tick() {
        this.animationBacteria.tick();
        this.animationBacteriaCargada0.tick();
        this.animationBacteriaCargada1.tick();
        this.animationBacteriaCargada2.tick();
        this.animationBacteriaCargada3.tick();
        this.animationBacteriaCargada4.tick();
        // moving player depending on flags
        if (game.getKeyManager().left && getX()>=Constants.MOV_OFFSET) {
           setX(getX() - speed);
        } else if (game.getKeyManager().right && getX()+getWidth()<=game.getWidth()-Constants.MOV_OFFSET-Constants.MOV_OFFSET/2) {
           setX(getX() + speed);
        }
        if (game.getKeyManager().up && getY()>=Constants.MOV_OFFSET) {
           setY(getY() - speed);
        } else if (game.getKeyManager().down && getY()+getHeight()<=game.getHeight()-Constants.MOV_OFFSET-Constants.MOV_OFFSET) {
           setY(getY() + speed);
        }
        
        if(estresStun>0){
            estresStun--;
        } else {
            estres--;
            estresStun = Constants.ESTRES_STUN;
        }
        
        if(estres>=Constants.ESTRES_BAJO && estres<=Constants.ESTRES_ALTO){
            for(int i=0; i<bactTarget.length; i++){
                if(bactTarget[i]){
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
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.E_COLI));
                        break;
                    case 1:
                        antibioticos.push(
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.B_SUBTILIS));
                        break;
                    case 2:
                        antibioticos.push(
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.P_AERUGINOSA));
                        break;
                    case 3:
                        antibioticos.push(
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.S_PNEUMONIAE));
                    case 4:
                        antibioticos.push(
                                new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.S_DYSENTERIAE));
                        break;
                        
                }
                cargas[i] = 0;
                Assets.added.play();
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
            switch(antibioticos.peek().getTipoInt()){
                    case 0:
                        g.drawImage(animationBacteriaCargada0.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); 
                        break;
                    case 1:
                        g.drawImage(animationBacteriaCargada1.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); 
                        break;
                    case 2:
                        g.drawImage(animationBacteriaCargada2.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); 
                        break;
                    case 3:
                        g.drawImage(animationBacteriaCargada3.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); 
                        break;  
                    case 4:
                        g.drawImage(animationBacteriaCargada4.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null); 
                        break;
                        
                }
        } else {
            g.drawImage(animationBacteria.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}

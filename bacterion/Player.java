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
    private int cargStun;
    private int lastAntib;
    private boolean[] bactTarget;
    
    private Animation animBact;
    private Animation animBactCarg[];
    
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
        lastAntib = 0;
        antibioticos = new LinkedList<>();
        
        // Con base en el nivel son los sprites de nuestra bacteria
        animBactCarg = new Animation[5];
        switch(game.getLevel()){
            case 1:
                bactTarget = Constants.BACT0_CARGAS;
                animBact = new Animation(Assets.bacteria0, height);
                animBactCarg[0] = new Animation(Assets.bacteria0Cargada0, height);
                animBactCarg[1] = new Animation(Assets.bacteria0Cargada1, height);
                animBactCarg[2] = new Animation(Assets.bacteria0Cargada2, height);
                animBactCarg[3] = new Animation(Assets.bacteria0Cargada3, height);
                animBactCarg[4] = new Animation(Assets.bacteria0Cargada4, height);
                break;
            case 2:
                bactTarget = Constants.BACT1_CARGAS;
                animBact = new Animation(Assets.bacteria1, height);
                animBactCarg[0] = new Animation(Assets.bacteria1Cargada0, height);
                animBactCarg[1] = new Animation(Assets.bacteria1Cargada1, height);
                animBactCarg[2] = new Animation(Assets.bacteria1Cargada2, height);
                animBactCarg[3] = new Animation(Assets.bacteria1Cargada3, height);
                animBactCarg[4] = new Animation(Assets.bacteria1Cargada4, height);
                break;
            case 3:
                bactTarget = Constants.BACT2_CARGAS;
                animBact = new Animation(Assets.bacteria2, height);
                animBactCarg[0] = new Animation(Assets.bacteria2Cargada0, height);
                animBactCarg[1] = new Animation(Assets.bacteria2Cargada1, height);
                animBactCarg[2] = new Animation(Assets.bacteria2Cargada2, height);
                animBactCarg[3] = new Animation(Assets.bacteria2Cargada3, height);
                animBactCarg[4] = new Animation(Assets.bacteria2Cargada4, height);
                break;
            default: break;
        }
    }
    
    /**
     * Checks if the player is alive
     * @return a <code> boolean </code> 
     */
    public boolean isAlive(){
        return this.alive;
    }
    
    /**
     * Gets the stress level of the player 
     * @return a <code> int </code> 
     */
    public int getEstres(){
        return estres;
    }
    
    /**
     * Sets the stress level of the player 
     * @param estres an <code> int </code> with the amount to set
     */
    public void setEstres(int estres){
        this.estres = estres;
    }
    
    /**
     * stresses the player 
     */
    public void estresar(){
        estres+= Constants.ESTRES_INCREASE;
    }
    
    /**
     * Gets the size of the antibiotics it has
     * @return an <code> int </code> with the size
     */
    public int getAntibioticosSize(){
        return antibioticos.size();
    }
    
    /**
     *Gets the next antibiotic
     * @return an <code> antibioticos </code> 
     */
    public Antibiotico getAntibiotico(){
        return antibioticos.poll();
    }
    
    /**
     * Sees if the bacteria has an antibiotic
     * @return a <code> bool </code> 
     */
    public boolean hasAntibiotico(){
        return antibioticos.peek()!=null;
    }
    
    /**
     * charges the bacteria target
     * @param an <code> int </code> with the index of what it will charge
     */
    public boolean cargaEsto(int index){
        return bactTarget[index];
    }
    
    /**
     * Control del movimiento del player y su 
     */
    @Override
    public void tick() {
        this.animBact.tick();
        for(Animation anim : animBactCarg){
            anim.tick();
        }
        
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
        
        if(estres>=Constants.ESTRES_BAJO){
            cargStun += (estres-Constants.ESTRES_BAJO)/10;
        }
        
        if(estres>=Constants.ESTRES_MORTAL){
            alive = false;
            game.endGame();
        }
        
        cargStun++;
        if(cargStun>=Constants.CARGA_STUN){
            cargStun = 0;
            int bact;
            if(antibioticos.size()>0){
                bact = antibioticos.peek().getTipoInt()+1;
            } else {
                bact = lastAntib+1;
            }
            while(!bactTarget[bact%bactTarget.length]){
                bact++;
            }
            lastAntib = bact%bactTarget.length;
            switch(bact%bactTarget.length){
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
            Assets.added.play();
        }
    }
    
    // Guarda la información del objeto en un string
    public String toString() {
        String output = "";
        output = (x+" "+y+" "+estres);
        for(Antibiotico anti : antibioticos){
            output += (" "+anti.getTipo());
        }
        return output;
    }
    
    // Se encarga de guardar en un archivo toda la informacion de nuestra partida
    public void loadFromString(String[] datos) {
        this.x = Integer.parseInt(datos[0]);
        this.y = Integer.parseInt(datos[1]);
        this.estres = Integer.parseInt(datos[2]);
        for(int a = datos.length-1; a>=3; a--){
            switch(datos[a]){
                case "E_COLI":
                    antibioticos.push(
                            new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.E_COLI));
                    break;
                case "B_SUBTILIS":
                    antibioticos.push(
                            new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.B_SUBTILIS));
                    break;
                case "P_AERUGINOSA":
                    antibioticos.push(
                            new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.P_AERUGINOSA));
                    break;
                case "S_PNEUMONIAE":
                    antibioticos.push(
                            new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.S_PNEUMONIAE));
                case "S_DYSENTERIAE":
                    antibioticos.push(
                            new Antibiotico(game,x,y,Constants.ANTIB_WIDTH,Constants.ANTIB_HEIGHT,AntiType.S_DYSENTERIAE));
                    break;

            }
        }
    }

    /**
     * render the image of the player 
     * @param g 
     */
    @Override
    public void render(Graphics g) { 
        if(antibioticos.size()>0){
            g.drawImage(animBactCarg[antibioticos.peek().getTipoInt()].getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(animBact.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}

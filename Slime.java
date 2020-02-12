/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;


import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author CSchafer
 */
public class Slime extends Sprite {
    //static constants
    private static final int WIDTH = 10;
    private static final int HEIGHT = 15;
    private static boolean virus = false;
    private int strength;
    
    
    public Slime(int speed, int x, int y, Color color) {
        super(speed, x, y, WIDTH, HEIGHT, color);
        this.strength = (int) (Math.random() * 20);
    }
    
     public void eat(Food food) {
        if (super.getBounds().intersects(food.getBounds()) && food.isAlive()) {
            super.setHeight(super.getHeight() + 5);
            super.setWidth(super.getWidth() + 5);
            food.die();
        }
    }
    
    @Override
    public void draw(Graphics g) {
        if(this.virus==true){
         g.setColor(Color.red);   
        }
        else{
            g.setColor(super.getColor());
        }
        
        g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    public int getStrength() {
        return strength;
    }
    
    public void Win(){
        this.strength++;
    }
    public void infect(){
        this.virus = true;
    }
    public boolean Infected(){
        return this.virus;
    }
    
}
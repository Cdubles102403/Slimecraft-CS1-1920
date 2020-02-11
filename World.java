/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slimecraft;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;


/**
 *
 * @author CSchafer
 */
public class World extends JPanel {
    private ArrayList<Blob> blobs = new ArrayList<>();  
    private ArrayList<Slime> slimes = new ArrayList<>();   
    private ArrayList<Glob> globs = new ArrayList<>();    
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();   
    Timer timer;
    
    public World() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/30);
        super.setSize(800, 600);
        for (int i = 0; i < 500; i++) {
            int x = (int) (Math.random() * 800 / 2);
            int y = (int) (Math.random() * 600);
            Blob blob = new Blob(x,y);
            sprites.add(blob);
            slimes.add(blob);
            blobs.add(blob);
        }
        
        for (int i = 0; i < 500; i++) {
            int x = (int) (Math.random() * 800 / 2 + 800 / 2);
            int y = (int) (Math.random() * 600);
            Glob glob = new Glob(x,y);
            sprites.add(glob);    
            slimes.add(glob);    
            globs.add(glob);    
        }
        
        for (int i = 0; i < 400; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            Food food = new Food(x,y);
            foods.add(food);
            sprites.add(food);
        }  
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Sprite sprite : sprites) {
            sprite.draw(g);
            sprite.update();
            for (Sprite other : sprites) {
                if (sprite != other) {
                    sprite.collide(other);
                }
            }
            
        }
        for (Slime slime : slimes) {
            for (Food food : foods) {
                slime.eat(food);
            }            
        }
        
        for (Blob blob : blobs) {
            for (Glob glob : globs) {
                blob.fight(glob);
            }
        }
        
        takeOutTheTrash();
    }
    
    private void takeOutTheTrash() {
        ArrayList<Sprite> trashMan = new ArrayList<>();
        for (Sprite sprite : sprites) {
            if (!sprite.isAlive())
                trashMan.add(sprite);
        }
        
        sprites.removeAll(trashMan);
        trashMan.clear();
        for (Food food : foods) {
            if (!food.isAlive())
                trashMan.add(food);
        }
        
        foods.removeAll(trashMan);
        trashMan.clear();
        for (Blob blob : blobs) {
            if (!blob.isAlive())
                trashMan.add(blob);
        }
        
        blobs.removeAll(trashMan);
        trashMan.clear();
        for (Glob glob : globs) {
            if (!glob.isAlive())
                trashMan.add(glob);
        }
        
        globs.removeAll(trashMan);
        trashMan.clear();        
    }
    
    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
}

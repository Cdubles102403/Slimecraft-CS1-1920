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
    private ArrayList<Glob> globs = new ArrayList<>();    
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();   
    private ArrayList<Slime> slimes = new ArrayList<>();
    Timer timer;
    
    public World() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/12);
        super.setSize(800, 600);
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800 / 2);
            int y = (int) (Math.random() * 600);
            Blob blob = new Blob(x,y);
            sprites.add(blob);
            slimes.add(blob);
            blobs.add(blob);
        }
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800 / 2 + 800 / 2);
            int y = (int) (Math.random() * 600);
            Glob glob = new Glob(x,y);
            sprites.add(glob);    
            slimes.add(glob);    
            globs.add(glob);    
        }
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 600);
            Food food = new Food(x,y);
            foods.add(food);
            sprites.add(food);
        }
        int infected = (int) Math.random()*slimes.size();
        slimes.get(infected).Infected();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Slime slime : slimes) {
            for (Food food : foods) {
                slime.eat(food);
            }            
        }
        
        ArrayList<Blob> newBlobs = new ArrayList<>();
        ArrayList<Glob> newGlobs = new ArrayList<>();
        
        for (Blob blob : blobs) {
            for (Glob glob : globs) {
                blob.fight(glob);
            }
            for (Blob otherBlob : blobs) {
                if (blob == otherBlob) continue;
                if (blob.collide(otherBlob) && Math.random() < .03)
                    newBlobs.add(blob.reproduce(otherBlob));
                if(blob.Infected()==true || otherBlob.Infected()==true){
                  newBlobs.get(newBlobs.size()-1).infect();  
                }
            }
        }
        for (Glob glob : globs) {
            for (Glob otherGlob : globs) {
                if (glob == otherGlob) continue;
                if (glob.collide(otherGlob) && Math.random() < .03)
                    newGlobs.add(glob.reproduce(otherGlob));
                if(glob.Infected()==true || otherGlob.Infected()==true){
                    newGlobs.get(newGlobs.size()-1).infect();
                }
            }
        }
        for (Sprite sprite : sprites) {
            sprite.draw(g);
            sprite.update();
            sprite.collideWorldBounds(800,600);
        }        
        
        takeOutTheTrash();
        addNewSlimes(newBlobs, newGlobs);
    }
    
    private void addNewSlimes(ArrayList<Blob> newBlobs, ArrayList<Glob> newGlobs) {
        blobs.addAll(newBlobs);
        globs.addAll(newGlobs);
        sprites.addAll(newBlobs);
        sprites.addAll(newGlobs);
        System.out.println(sprites.size());
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
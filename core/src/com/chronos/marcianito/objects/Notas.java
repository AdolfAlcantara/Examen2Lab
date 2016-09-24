package com.chronos.marcianito.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;
/**
 * Created by CHRONO on 9/18/2016.
 */
public class Notas {
    //Random ran = new Random();
    private Rectangle box_note;
    private Texture nota;
    private Sprite sprite_nota;
    private double velocityY= 120;

    public Notas(){
        box_note = new Rectangle(0.0f,0.0f,80.0f,80.0f);
        nota = new Texture("sprites/nota.png");
        sprite_nota = new Sprite(nota,0,0,80,80);
    }

    public void setPosition(int x, int y){
        box_note.x = x;
        box_note.y = y;
        sprite_nota.setPosition(x,y);
    }

    public void rain(float Delta){
        box_note.y -= velocityY*Delta;
        sprite_nota.setPosition(box_note.x,box_note.y);
    }

    public int hits(Rectangle r){
        if(box_note.overlaps(r)){
            return 1;
        }
        return -1;
    }

    public void draw(SpriteBatch batch){
        sprite_nota.draw(batch);
    }
}

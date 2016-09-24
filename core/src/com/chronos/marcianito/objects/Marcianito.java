package com.chronos.marcianito.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Marcianito {
	public Rectangle bottom;
	public Sprite sprite;
	public Texture texture;
    TextureRegion texture_baile;
	Animation mar_baile;
    float velocityY;
    public static int notasCogidas=0;
	public static int vidas=5;


	//Entry point
	public Marcianito(){
        mar_baile = new Animation(0.25f,texture_baile);

        bottom = new Rectangle(0.0f,0.0f,130.0f,160.0f);

		texture = new Texture(Gdx.files.internal("sprites/marcianito.png"));
		//texture_baile = new TextureRegion(Gdx.files.internal("sprite/marcianito_baile.gif"));
        sprite = new Sprite(texture,0,0,130,160);


        this.setPosition(0,0);
		velocityY=0;
	}
	public int hits(Rectangle r){
		if(bottom.overlaps(r)){
			return 1;
		}
		return -1;
	}
	
	public void action(int type, int x, int y){
		if(type == 1){
            velocityY=0;
            setPosition(bottom.x,y);
        }
	}
	
	public void update(float delta)
	{
		velocityY -= 50*delta;
		bottom.y += velocityY;
		sprite.setPosition(bottom.x, bottom.y);
	}
	
	public void setPosition(float x, float y){
		bottom.x = x;
		bottom.y = y;
		sprite.setPosition(x, y);
	}
	
	public void moveLeft(float delta){
		bottom.x -= 100*delta;
		sprite.setPosition(bottom.x,bottom.y);
	}
	
	public void moveRight(float delta){
		bottom.x += 100*delta;
		sprite.setPosition(bottom.x,bottom.y);
	}

	public void jump(){
		velocityY =15;
	}

	public void perderVida(){
		this.vidas -= 1;
	}
	public void cogerNota(){
		this.notasCogidas += 1;
	}
	public int getVidas(){
		return vidas;
	}
	public int getNotasCogidas(){
		return notasCogidas;
	}
	public void bailar(){

        if(Gdx.graphics.getDeltaTime()%2==0){
            //sprite.setTexture(texture_baile);
        }else{
            sprite.setTexture(texture);
        }

	}
	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}
}

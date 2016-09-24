package com.chronos.marcianito;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.chronos.marcianito.objects.Marcianito;
import com.chronos.marcianito.objects.Notas;
import java.util.Random;

public class MarcianitoGame extends ApplicationAdapter {
    Random ran = new Random();
	private OrthographicCamera camera;
    private SpriteBatch batch;
	private Marcianito player1;
    private Texture background;
    private Notas notas;
    Texture texture_baile;
    private Music cumbion;

    //Texto
    BitmapFont font;

    boolean gameOver = false;


	@Override

	public void create () {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,800,480);
		batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("sprites/star_background.gif"));
        font = new BitmapFont();
        font.getData().setScale(1.5f,1.5f);

        cumbion = Gdx.audio.newMusic(Gdx.files.internal("data/cumbion.mp3"));


        notas = new Notas();
        notas.setPosition(ran.nextInt(800),400);

		player1 = new Marcianito();
		player1.setPosition(200, 100);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
        batch.draw(background, 0, 0, 800, 480);
        CharSequence vidas = "Te quedan: " + player1.getVidas() + " vidas!";
        CharSequence notasCodigas = "Te faltan: " + (5 - player1.getNotasCogidas()) + " notas";
        font.draw(batch, vidas, 10, 50);
        font.draw(batch, notasCodigas, 10, 25);
        player1.draw(batch);

        if (player1.getNotasCogidas() < 5) {
            notas.draw(batch);
        }
        batch.end();

		//UPDATES
        player1.update(Gdx.graphics.getDeltaTime());
        Rectangle floor  = new Rectangle(0,0,800,10);

        notas.rain(Gdx.graphics.getDeltaTime());
        //LOGIC
        if (player1.hits(floor)!= -1) {
            player1.action(1,0,10);
        }
        if(notas.hits(floor)!= -1 && gameOver == false){
            notas.setPosition(ran.nextInt(720),400);
            player1.perderVida();
        }
        if(notas.hits(player1.bottom)!= -1){
            notas.setPosition(ran.nextInt(720),400);
            player1.cogerNota();
        }

        if(player1.notasCogidas >= 5){
            gameOver = true;
            cumbion.play();
            player1.bailar();
        }

		//CONTROLS
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player1.moveLeft(Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player1.moveRight(Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player1.jump();
        }
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Collection;


public class HelicopterGame extends Game {


    public static final int WORLD_WIDTH = 1200;
    public static final int WORLD_HEIGHT = 700;
    public static final String title = "Helicopter";
    private Helicopter[] helicopters;
    private Helicopter helicopter;
    private SingletonHelicopter singletonHelicopter, sh;
    private int numOfHelis = 11;
    private FlipHelicopter flipHelicopter;





    @Override
    public void create () {

        flipHelicopter = new FlipHelicopter();
        helicopters = new Helicopter[numOfHelis];

        singletonHelicopter = SingletonHelicopter.getInstance();
        sh = SingletonHelicopter.getInstance();

        helicopters[0] = new Helicopter();
        helicopters[0].addAlarmHelicopter(flipHelicopter);

        for (int i = 1; i < numOfHelis; i++) {
            helicopter = new Helicopter();
            for (int j=0; j<i; j++)
            if (Math.abs(helicopter.pos_x - helicopters[j].pos_x) < helicopter.heliWidth && Math.abs(helicopter.pos_y - helicopters[j].pos_y) < helicopter.heliHeigth) {
                helicopter = new Helicopter();
            }
            helicopters[i] = helicopter;
            helicopters[i].addAlarmHelicopter(flipHelicopter);

        }
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		for (int i = 0; i < numOfHelis; i ++){
			helicopters[i].bounceback();
			for (int j= 0; j < numOfHelis; j ++) {
				helicopters[i].collide(helicopters[j]);
			}
		}

    }

    @Override
    public void dispose () {
    }
}
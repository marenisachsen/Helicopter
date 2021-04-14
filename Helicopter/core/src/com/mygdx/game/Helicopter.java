package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Helicopter extends Rectangle {

        int pos_x;
        int pos_y;
        int dx;
        int dy;
        public int heliWidth = 130;
        public int heliHeigth = 52;
        public static final int WORLD_WIDTH = 1200;
        public static final int WORLD_HEIGHT = 700;


        private SpriteBatch batch;
        private Animation animation;
        Texture img1;
        Texture img2;
        Texture img3;
        Texture img4;
        int direction;
        int vertical_direction;
        private Array frames;
        public boolean invert = true;
        public float deltaTime;
        private Random rand = new Random();
        public BitmapFont font = new BitmapFont();

        public Helicopter(){

            batch = new SpriteBatch();
            img1 = new Texture("heli1.png");
            img2 = new Texture("heli2.png");
            img3 = new Texture("heli3.png");
            img4 = new Texture("heli4.png");

            direction = 1;
            vertical_direction = 1;
            dx = 2;
            dy = 2;

            pos_x = rand.nextInt(Helicopter.WORLD_WIDTH - heliWidth);
            pos_y = rand.nextInt(Helicopter.WORLD_HEIGHT - heliHeigth);


            frames = new Array<>(4);
            frames.add(img1, img2, img3, img4);
            animation = new Animation(1f/16f, frames);
        }






        public void bounceback() {

                deltaTime += Gdx.graphics.getDeltaTime();
                this.batch.begin();
                this.batch.draw((Texture) this.animation.getKeyFrame(deltaTime, true), this.pos_x, this.pos_y, this.heliWidth, this.heliHeigth, 0, 0, 155, 77, invert, false);

                if (this.pos_x > Helicopter.WORLD_WIDTH - this.heliWidth || this.pos_x < 0) {
                    fireHorizontalHelicopter();
                }


                this.pos_x += this.dx * direction;


                if (this.pos_y > Helicopter.WORLD_HEIGHT - this.heliHeigth || this.pos_y < 0) {
                    fireVerticalHelicopter();
                }

                this.pos_y += this.dy * vertical_direction;
                this.batch.end();
            }

    public void press() {

        batch.begin();
        batch.draw((Texture) animation.getKeyFrame(deltaTime,true),pos_x, pos_y, heliWidth,  heliHeigth,0, 0 ,170, 77, invert, false);

        int rightLimit, leftLimit, upLimit, downLimit;
        rightLimit = Helicopter.WORLD_WIDTH - heliWidth - pos_x;
        upLimit = Helicopter.WORLD_HEIGHT - heliHeigth - pos_y;
        leftLimit = pos_x;
        downLimit= pos_y;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && rightLimit > 0) {
            pos_x += dx;
            invert = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && leftLimit > 0) {
            pos_x -= dx;
            invert = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && upLimit > 0) {
            pos_y += dy;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && downLimit > 0) {
            pos_y -= dy;
        }

        batch.end();

    }


    public void collide(Helicopter h1) {
            int diff_x = Math.abs(h1.pos_x - this.pos_x);
            int diff_y = Math.abs(h1.pos_y-this.pos_y);
            if (diff_x < heliWidth && diff_y<heliHeigth) {
                h1.fireHelicopter();
                this.fireHelicopter();

            }
        }

    public void print () {
            this.batch.begin();
            this.font.draw(this.batch, "("+ this.pos_x + "," + this.pos_y+ ")", this.pos_x, this.pos_y);
            this.batch.end();
    }

    private Collection<AlarmHelicopter> alarmHelicopters = new ArrayList<AlarmHelicopter>();

    public void addAlarmHelicopter(AlarmHelicopter alarmHelicopter) {
        alarmHelicopters.add(alarmHelicopter);
    }

    public void removeHelicopter(AlarmHelicopter alarmHelicopter) {
        alarmHelicopters.remove(alarmHelicopter);
    }


    public void fireHelicopter() {
        for (AlarmHelicopter alarmHelicopter: alarmHelicopters) {
            alarmHelicopter.flip(this);
            alarmHelicopter.changeHorizontalDirection(this);
            alarmHelicopter.changeVerticalDirection(this);
        }
    }

    public void fireVerticalHelicopter() {
        for (AlarmHelicopter alarmHelicopter : alarmHelicopters) {
            alarmHelicopter.changeVerticalDirection(this);
        }
    }

    public void fireHorizontalHelicopter() {
            for (AlarmHelicopter alarmHelicopter : alarmHelicopters) {
                alarmHelicopter.flip(this);
                alarmHelicopter.changeHorizontalDirection(this);
            }
        }





    }



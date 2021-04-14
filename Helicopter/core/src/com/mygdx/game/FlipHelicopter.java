package com.mygdx.game;

import java.util.Collection;
import java.util.*;

public class FlipHelicopter implements AlarmHelicopter {


    public void flip(Helicopter helicopter) {
            helicopter.invert = !helicopter.invert;
    }


    public void changeVerticalDirection(Helicopter helicopter) {
        helicopter.vertical_direction = helicopter.vertical_direction *- 1;


    }


    public void changeHorizontalDirection(Helicopter helicopter) {
        helicopter.direction = helicopter.direction*-1;

    }
}




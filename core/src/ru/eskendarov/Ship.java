package ru.eskendarov;

import com.badlogic.gdx.math.Rectangle;

import lombok.Getter;

@Getter
abstract class Ship {

    private final Rectangle rectangle = new Rectangle();
    private final int size = 128 * 2 / 3;
    private final int halfSize = size / 2;

    abstract void initPosition();

}
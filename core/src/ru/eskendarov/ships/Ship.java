package ru.eskendarov.ships;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;
import ru.eskendarov.arsenal.Guns;

@Getter
@Setter
abstract class Ship {

    private final Rectangle rectangle = new Rectangle();
    private final int SIZE = 128 * 2 / 3;
    private final int HALF_SIZE = SIZE / 2;
    private Guns gun;
    private int health = 0;

    abstract void initPosition();

    abstract void damage();

    abstract void battle();

}
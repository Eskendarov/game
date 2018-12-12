package ru.eskendarov.ships;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import ru.eskendarov.screen.ScreenOption;

@Getter
abstract class SpaceCraft extends Boundaries {

    private float SIZE = (float) ScreenOption.WIDTH / 5;
    private float HALF_SIZE = SIZE / 2f;
    private final Rectangle position = new Rectangle();

}
package ru.eskendarov;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import lombok.Data;

@Data
class Stars {

    private final Array<Rectangle> stars = new Array<Rectangle>();
    private Rectangle star;
    private final int size = 5;
    private long lastStarTime;

    void starFall() {
        star = new Rectangle();
        star.x = MathUtils.random(Screen.WIDTH);
        star.y = Screen.HEIGHT;
        stars.add(star);
        lastStarTime = TimeUtils.nanoTime();
    }

}

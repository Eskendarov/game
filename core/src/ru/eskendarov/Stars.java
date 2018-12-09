package ru.eskendarov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import lombok.Getter;

@Getter
class Stars {

    private final Array<Rectangle> stars = new Array<Rectangle>();
    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final Resources resources = new Resources();
    private final int size = 5;
    private Rectangle star;
    private long lastStarTime;

    private void starFall() {
        star = new Rectangle();
        star.x = MathUtils.random(Screen.WIDTH);
        star.y = Screen.HEIGHT;
        stars.add(star);
        lastStarTime = TimeUtils.nanoTime();
    }

    void iterate() {
        final Iterator<Rectangle> iterator = stars.iterator();
        while (iterator.hasNext()) {
            final Rectangle stars = iterator.next();
            stars.y -= 110 * Gdx.graphics.getDeltaTime();
            if (stars.y < 0) iterator.remove();
        }
        /*
         * проверяем, сколько времени прошло, с тех пор как была создана
         * новая звезда и если необходимо, создаем еще одну новую звезду.
         * */
        if (TimeUtils.nanoTime() - lastStarTime > 10000000) {
            starFall();
        }
    }

}
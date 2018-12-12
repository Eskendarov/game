package ru.eskendarov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import lombok.Getter;
import ru.eskendarov.screen.ScreenOption;

import java.util.Iterator;

import static ru.eskendarov.Controller.spriteBatch;

@Getter
class Stars {

    private final Array<Rectangle> stars = new Array<>();
    private final float size = 5;
    private Rectangle star;
    private long lastStarTime;

    private void starFall() {
        star = new Rectangle();
        star.x = MathUtils.random(ScreenOption.WIDTH);
        star.y = ScreenOption.HEIGHT;
        stars.add(star);
        lastStarTime = TimeUtils.nanoTime();
    }

        void generate(final Resources resources) {
        final Iterator<Rectangle> iterator = stars.iterator();
        while (iterator.hasNext()) {
            final Rectangle stars = iterator.next();
            // Задаем звездам случайную скорость.
            stars.y -= MathUtils.random(50,300) * Gdx.graphics.getDeltaTime();
            if (stars.y < 0) iterator.remove();
        }
        /*
         * проверяем, сколько времени прошло, с тех пор как была создана
         * новая звезда и если необходимо, создаем еще одну новую звезду.
         * */
        if (TimeUtils.nanoTime() - lastStarTime > 10000000) {
            starFall();
        }
        for (final Rectangle starsRec : stars) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                starsRec.x += 20 * Gdx.graphics.getDeltaTime();
                if (starsRec.x >= ScreenOption.WIDTH) {
                    starsRec.x = 0;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                starsRec.x -= 20 * Gdx.graphics.getDeltaTime();
                if (starsRec.x <= 0) {
                    starsRec.x = ScreenOption.WIDTH;
                }
            }if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                starsRec.y -= 30 * Gdx.graphics.getDeltaTime();
            }
            spriteBatch.draw(resources.getStarImage(), starsRec.x, starsRec.y, size, size);
        }
    }

}
package ru.eskendarov.arsenal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import lombok.Getter;
import ru.eskendarov.Resources;
import ru.eskendarov.screen.ScreenOption;
import ru.eskendarov.ships.OwnCraft;

import java.util.Iterator;

import static ru.eskendarov.Controller.spriteBatch;

@Getter
public class Bullet extends OwnCraft {

    private final Array<Rectangle> bullets = new Array<>();
    private final float size = 50;
    private Rectangle bullet;
    private long lastStarTime;

    private void shelling() {
        bullet = new Rectangle();
        bullet.x = getPosition().x;
        bullet.y = getPosition().y + getSIZE();
        bullets.add(bullet);
        lastStarTime = TimeUtils.nanoTime();
    }

    public void fire(final Resources resources) {
        final Iterator<Rectangle> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            final Rectangle bullets = iterator.next();
            bullets.y += 200 * Gdx.graphics.getDeltaTime();
            if (bullets.y > ScreenOption.HEIGHT) iterator.remove();
        }
        if (TimeUtils.nanoTime() - lastStarTime > 10000000) {
            shelling();
        }
        for (final Rectangle bulletRec : bullets) {
            spriteBatch.draw(resources.getBulletImage(), bulletRec.x, bulletRec.y, size, size);
        }
    }
}

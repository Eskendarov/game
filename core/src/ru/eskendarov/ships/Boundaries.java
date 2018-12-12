package ru.eskendarov.ships;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;
import ru.eskendarov.screen.ScreenOption;

@Getter
@Setter
class Boundaries {

    private float leftBounds;
    private float rightBounds;
    private float topBounds;
    private float bottomBounds;

    void setBounds(final float sizeCraft, final Rectangle position, final TypeCraft typeCraft) {
        switch (typeCraft) {
            case OWN: {
                if (position.y >= ScreenOption.HEIGHT / 2 - sizeCraft) {
                    position.y = (float) ScreenOption.HEIGHT / 2 - sizeCraft;
                    topBounds = (float) ScreenOption.HEIGHT / 2 - sizeCraft;
                }
                if (position.y <= 20) {
                    position.y = 20;
                    bottomBounds = 20;
                }
                break;
            }
            case ENEMY: {
                if (position.y >= ScreenOption.HEIGHT - sizeCraft - 20) {
                    position.y = ScreenOption.HEIGHT - sizeCraft - 20;
                    topBounds = ScreenOption.HEIGHT - sizeCraft - 20;
                }
                if (position.y <= ScreenOption.HEIGHT / 2) {
                    position.y = ScreenOption.HEIGHT;
                    bottomBounds = ScreenOption.HEIGHT;
                }
                break;
            }
        }
        if (position.x < 0) {
            position.x = 0;
            leftBounds = 0;
        }
        if (position.x >= ScreenOption.WIDTH - sizeCraft) {
            position.x = ScreenOption.WIDTH - sizeCraft;
            rightBounds = ScreenOption.WIDTH - sizeCraft;
        }
    }

    enum TypeCraft {
        OWN,
        ENEMY
    }

}

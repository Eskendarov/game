package ru.eskendarov.ships;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import ru.eskendarov.screen.ScreenOption;

@Getter
public class OwnCraft extends SpaceCraft {

    public OwnCraft() {
        getPosition().setPosition(ScreenOption.HALF_WIDTH - getHALF_SIZE(),
                getHALF_SIZE() / 3);
        System.out.println("init position own ship");
    }

    public void setBounds() {
        super.setBounds(getSIZE(), getPosition(), TypeCraft.OWN);
    }

}
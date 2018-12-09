package ru.eskendarov.ships;

import lombok.Getter;
import ru.eskendarov.Screen;

@Getter
public class OwnShip extends Ship {

    public void initPosition() {
        this.getRectangle().x = Screen.HALF_WIDTH - super.getHALF_SIZE();
        this.getRectangle().y = super.getHALF_SIZE() / 3;
        System.out.println("initPosition own ship");
    }

    @Override
    void damage() {
        this.setHealth(100);
    }

    @Override
    void battle() {
    }

}
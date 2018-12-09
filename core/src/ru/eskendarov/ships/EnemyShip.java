package ru.eskendarov.ships;

import lombok.Getter;
import ru.eskendarov.Screen;
import ru.eskendarov.arsenal.Guns;

@Getter
public class EnemyShip extends Ship {

    public EnemyShip() {
        this.setHealth(100);
        this.setGun(Guns.MACHINE_GUN);
    }

    public void initPosition() {
        this.getRectangle().x = Screen.HALF_WIDTH - super.getHALF_SIZE();
        this.getRectangle().y = Screen.HEIGHT - super.getSIZE() - super.getHALF_SIZE() / 3;
        System.out.println("initPosition enemy ship");
    }

    @Override
    void damage() {
        this.setHealth(100);
    }

    @Override
    void battle() {

    }

}
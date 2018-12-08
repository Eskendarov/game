package ru.eskendarov;

import lombok.Getter;

@Getter
class EnemyShip extends Ship {

    void initPosition() {
        this.getRectangle().x = Screen.HALF_WIDTH - super.getHalfSize();
        this.getRectangle().y = Screen.HEIGHT - super.getSize() - super.getHalfSize() / 3;
        System.out.println("initPosition enemy ship");
    }

}
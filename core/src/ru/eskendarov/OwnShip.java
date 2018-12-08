package ru.eskendarov;

import lombok.Getter;

@Getter
class OwnShip extends Ship {

    void initPosition() {
        this.getRectangle().x = Screen.HALF_WIDTH - super.getHalfSize();
        this.getRectangle().y = super.getHalfSize() / 3;
        System.out.println("initPosition own ship");
    }

}
package ru.eskendarov.ships;

import lombok.Getter;
import ru.eskendarov.screen.ScreenOption;


@Getter
public class EnemyCraft extends SpaceCraft {

    public EnemyCraft() {
        this.getPosition().setPosition(ScreenOption.HALF_WIDTH - getHALF_SIZE(),
                this.getPosition().y = ScreenOption.HEIGHT - getSIZE() - getHALF_SIZE() / 3);
        System.out.println("init position enemy ship");
    }

    public void setBounds() {
        super.setBounds(getSIZE(),getPosition(),TypeCraft.ENEMY);
    }

}
package ru.eskendarov;

import com.badlogic.gdx.math.Vector3;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class Ship {

    protected static int size = 128;
    protected static int halfSize = size/2;
    protected static Vector3 touchPosition = new Vector3();

}

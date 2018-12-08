package ru.eskendarov;

import lombok.Data;

@Data
class Ship {

    private int size = 128 * 2 / 3;
    private int halfSize = size / 2;

}

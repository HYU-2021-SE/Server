package com.dionysos.winecellar.domain.winecellar.domain;

import java.util.Arrays;

public enum WinecellarType {
    W8(1, 8), W43(1, 43), W75(2, 75), W85(2, 85), W89(2, 89);

    private final Integer floor;
    private final Integer max;

    WinecellarType(Integer floor, Integer max) {
        this.floor = floor;
        this.max = max;
    }

    public static WinecellarType of(String serialNo) {
        return Arrays.stream(WinecellarType.values())
            .filter(type -> type.name().equals(serialNo))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid serial No"));
    }
}

package org.sobakaisti.mvt.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostStatus {

    ACTIVE(1),
    INACTIVE(0);

    private final int value;

    public static PostStatus byValue(int value) {
        return value > 0 ? ACTIVE : INACTIVE;
    }

    public static PostStatus byValue(boolean isActive) {
        return isActive ? ACTIVE : INACTIVE;
    }
}

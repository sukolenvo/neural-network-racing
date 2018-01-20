package com.sukolenvo.racing.core.track;

public class WallNotFoundException extends IllegalStateException {
    public WallNotFoundException(String message) {
        super(message);
    }
}

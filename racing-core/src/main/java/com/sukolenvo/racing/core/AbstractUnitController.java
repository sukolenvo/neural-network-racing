package com.sukolenvo.racing.core;

import com.sukolenvo.racing.core.track.Point;
import com.sukolenvo.racing.core.track.RacingTrack;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class AbstractUnitController<T extends Unit> {

    protected static final int STEP_LENGTH = 5;

    private final List<T> units = new ArrayList<>();
    @Getter
    private final RacingTrack racingTrack;

    public AbstractUnitController(int unitCount, RacingTrack racingTrack) {
        this.racingTrack = racingTrack;
        for (int i = 0; i < unitCount; i++) {
            units.add(createUnit());
        }
    }

    public abstract void makeStep();

    /**
     * Reset position of unit to the start of the track.
     */
    public abstract void reset();

    protected abstract T createUnit();

    public List<T> getUnits() {
        return Collections.unmodifiableList(units);
    }

    public boolean isFinished() {
        return units.stream().allMatch(Unit::isDisabled);
    }

    protected void resetUnitsToStart(List<T> units) {
        units.forEach(unit -> {
            Point initialPosition = getRacingTrack().getInitialPosition();
            unit.setX(initialPosition.getX());
            unit.setY(initialPosition.getY());
            unit.setDisabled(false);
            unit.setAngle(0);
        });
    }

    public boolean isTrackCompleted() {
        return units.stream().anyMatch(racingTrack::isTrackCompleted);
    }
}

package com.sukolenvo.racing.core;

import com.sukolenvo.racing.core.track.Point;
import com.sukolenvo.racing.core.track.RacingTrack;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author vadym
 */
@Slf4j
public class UnitController {

    private static final int STEP_DELAY_MS = 100;
    private static final int STEP_LENGTH = 5;

    private final List<Unit> units;
    private final RacingTrack racingTrack;

    public UnitController(List<Unit> units, RacingTrack racingTrack) {
        this.units = units;
        this.racingTrack = racingTrack;
    }

    public void start(Runnable interStepCallback) {
        while (!finished()) {
            try {
                Thread.sleep(STEP_DELAY_MS);
            } catch (InterruptedException e) {
                log.warn("Got exception while waiting next step", e);
            }
            makeStep();
            interStepCallback.run();
        }
    }

    private boolean finished() {
        return units.stream().allMatch(Unit::isDisabled);
    }

    private void makeStep() {
        units.stream().filter(unit -> !unit.isDisabled())
                .forEach(unit -> {
                    Point border = racingTrack.getWall(unit);
                    if (border.distanceTo(unit) < STEP_LENGTH) {
                        unit.moveTo(border);
                        unit.setDisabled(true);
                    } else {
                        unit.moveTo(unit.getPosition().moveTo(unit.getAngle(), STEP_LENGTH));
                        unit.updateDirection();
                    }
                });
    }
}

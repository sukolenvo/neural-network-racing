package com.sukolenvo.racing.core;

import com.sukolenvo.racing.core.track.RacingTrack;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public abstract class AbstractUnitController<T extends Unit> {

    private static final int STEP_DELAY_MS = 15;
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

    protected abstract T createUnit();

    public List<T> getUnits() {
        return Collections.unmodifiableList(units);
    }

    public void start(Runnable interStepCallback, Consumer<Integer> generationListener) {
        while (!finished()) {
            makeStep();
            interStepCallback.run();
            try {
                Thread.sleep(STEP_DELAY_MS);
            } catch (InterruptedException e) {
                log.warn("Got exception while waiting next step", e);
            }
        }
        emulationFinished();
    }

    protected void emulationFinished() {
        log.info("Emulation finished");
    }

    private boolean finished() {
        return units.stream().allMatch(Unit::isDisabled);
    }

    protected abstract void makeStep();
}

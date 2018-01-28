package com.sukolenvo.racing.core.simple;

import com.sukolenvo.racing.core.AbstractUnitController;
import com.sukolenvo.racing.core.track.Point;
import com.sukolenvo.racing.core.track.RacingTrack;
import lombok.extern.slf4j.Slf4j;

/**
 * Unit controller with random unit decision making.
 *
 * @author vadym
 */
@Slf4j
public class SimpleUnitController extends AbstractUnitController<SimpleUnitImpl> {

    public SimpleUnitController(int unitCount, RacingTrack racingTrack) {
        super(unitCount, racingTrack);
    }

    @Override
    protected SimpleUnitImpl createUnit() {
        return new SimpleUnitImpl(getRacingTrack().getInitialPosition());
    }

    @Override
    protected void makeStep() {
        getUnits().stream().filter(unit -> !unit.isDisabled())
                .forEach(unit -> {
                    Point border = getRacingTrack().getWall(unit);
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

package com.sukolenvo.racing.core.learning;

import com.sukolenvo.racing.core.AbstractUnitController;
import com.sukolenvo.racing.core.Unit;
import com.sukolenvo.racing.core.track.Point;
import com.sukolenvo.racing.core.track.RacingTrack;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Controller to create and manage @{@link LearningUnit}. It is going to use generic algorithm to train unit to pass
 * track.
 */
public class LearningUnitController extends AbstractUnitController<LearningUnit> {

    private final Random random = new Random();

    public LearningUnitController(int unitCount, RacingTrack racingTrack) {
        super(unitCount, racingTrack);
    }

    @Override
    protected LearningUnit createUnit() {
        return new LearningUnit(getRacingTrack().getInitialPosition());
    }

    @Override
    public void makeStep() {
        getUnits().stream().filter(unit -> !unit.isDisabled())
                .forEach(unit -> {
                    Point border = getRacingTrack().getWall(unit);
                    if (border.distanceTo(unit) < STEP_LENGTH) {
                        unit.moveTo(border);
                        unit.setDisabled(true);
                    } else {
                        unit.moveTo(unit.getPosition().moveTo(unit.getAngle(), STEP_LENGTH));
                        unit.updateDirection(getDistanceToWall(unit, -30),
                                getDistanceToWall(unit, -15),
                                getDistanceToWall(unit, 0),
                                getDistanceToWall(unit, 15),
                                getDistanceToWall(unit, 30));
                    }
                });
    }

    private double getDistanceToWall(Unit from, double deltaDirection) {
        Point wall = getRacingTrack().getWall(from.getX(), from.getY(), from.getAngle() + deltaDirection);
        return wall.distanceTo(from);
    }

    @Override
    public void reset() {
        if (isFinished()) {
            List<LearningUnit> units = getUnits().stream()
                    .sorted(Collections.reverseOrder(
                            Comparator.comparingDouble(unit -> getRacingTrack().fitnessFunction(unit.getPosition()))))
                    .collect(Collectors.toList());
            recombine(units);
            mutate(units);
        }
        resetUnitsToStart(getUnits());
    }

    /**
     * @param orderedUnits should be ordered by fitnessFunction DESC
     */
    private void recombine(List<LearningUnit> orderedUnits) {
        LearningUnit first = copy(orderedUnits.get(0));
        LearningUnit second = copy(orderedUnits.get(1));
        for (int i = 2; i < orderedUnits.size(); i += 2) {
            recombineUnits(first, second);
            orderedUnits.get(i).setUnitWeights(first.copyWeights());
            orderedUnits.get(i + 1).setUnitWeights(second.copyWeights());
        }
    }

    private LearningUnit copy(LearningUnit from) {
        LearningUnit learningUnit = new LearningUnit(new Point(0, 0));
        learningUnit.setUnitWeights(from.getUnitWeights());
        return learningUnit;
    }

    private void recombineUnits(LearningUnit first, LearningUnit second) {
        for (int i = 0; i < first.getUnitWeights().size(); i++) {
            for (int j = 0; j < first.getUnitWeights().get(i).size(); j++) {
                if (WeightHelper.rollSwap()) {
                    //TODO: create explicit methods unit.getWeight(int layer, int neuron)
                    //TODO: unit.setWeight(int layer, int neuron)
                    double fValue = first.getUnitWeights().get(i).get(j);
                    first.getUnitWeights().get(i).set(j, second.getUnitWeights().get(i).get(j));
                    first.getUnitWeights().get(i).set(j, fValue);
                }
            }
        }
    }

    private void mutate(List<LearningUnit> units) {
        for (int i = 2; i < units.size(); i++) {
            mutate(units.get(i), 30);
        }
    }

    private void mutate(LearningUnit unit, int mutationRate) {
        for (int i = 0; i < unit.getUnitWeights().size(); i++) {
            for (int j = 0; j < unit.getUnitWeights().get(i).size(); j++) {
                if (random.nextInt(100) < mutationRate) {
                    unit.getUnitWeights().get(i).set(j, WeightHelper.getRandowmWeight());
                }
            }
        }
    }
}

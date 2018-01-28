package com.sukolenvo.racing.core.learning;

import com.sukolenvo.racing.core.Unit;
import com.sukolenvo.racing.core.track.Point;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LearningUnitTest {

    @Test
    @Ignore
    public void updateDirection_max() throws Exception {
        LearningUnit unit = new LearningUnit(new Point(0, 0));
        unit.setUnitWeights(Arrays.asList(createLayer(5, 3, 1.),
                createLayer(3, 2, 1.),
                createLayer(2, 1, 1.)));
        unit.setAngle(2);
        unit.updateDirection(1., 1., 1., 1., 1.);

        assertEquals(2. + Unit.MAX_ANGLE_DELTA, unit.getAngle(), 0.01);
    }

    private List<Double> createLayer(int inputSize, int neuronsCount, double defaultValue) {
        List<Double> result = new ArrayList<>();
        //(input size + bias node) * neuronsCount
        int weightCount = (inputSize + 1) * neuronsCount;
        for (int i = 0; i < weightCount; i++) {
            result.add(defaultValue);
        }
        return result;
    }

    @Test
    public void updateDirection_keepDirection() throws Exception {
        LearningUnit unit = new LearningUnit(new Point(0, 0));
        unit.setUnitWeights(Arrays.asList(createLayer(5, 3, 0),
                createLayer(3, 2, 0),
                createLayer(2, 1, 0)));
        unit.setAngle(2);
        unit.updateDirection(1., 1., 1., 1., 1.);

        assertEquals(2., unit.getAngle(), 0.01);
    }
}
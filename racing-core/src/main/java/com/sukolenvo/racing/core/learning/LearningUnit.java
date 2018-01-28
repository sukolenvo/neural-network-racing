package com.sukolenvo.racing.core.learning;

import com.sukolenvo.racing.core.Unit;
import com.sukolenvo.racing.core.track.Point;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit based on neural network with input: distances to wall behind the unit with different angle.
 * Number of layers: 3
 * first layer: 4 neurons ((5 + 1) * 4 weights) [(input size + bias) * neurons count]
 * second layer: 3 neurons ((4 + 1) * 3 weights) [(input size + bias) * neurons count]
 * third layer (output): 1 neurons ((3 + 1) * 1 weights) [(input size + bias) * neurons count]
 * output reflects change of unit direction
 * <p>
 * Single neuron will be represented by [input] consequence weight in {@link #unitWeights}
 */
public class LearningUnit extends Unit {

    @Getter

    private List<List<Double>> unitWeights = new ArrayList<>();

    public LearningUnit(Point initialPosition) {
        setX(initialPosition.getX());
        setY(initialPosition.getY());
        createLayerWithRandomWeight(24);
        createLayerWithRandomWeight(15);
        createLayerWithRandomWeight(4);
    }

    public void setUnitWeights(List<List<Double>> unitWeights) {
        this.unitWeights = unitWeights;
    }

    private void createLayerWithRandomWeight(int weightCount) {
        List<Double> layer = new ArrayList<>();
        for (int i = 0; i < weightCount; i++) {
            layer.add(WeightHelper.getRandowmWeight());
        }
        unitWeights.add(layer);
    }

    /**
     * Accepts 5 wall points behind unit to generate angle change.
     *
     * @param left        wall in direction of unit minus 30 degrees
     * @param leftCenter  wall in direction of unit minus 15 degrees
     * @param center      wall in direction of unit
     * @param rightCenter wall in direction of unit plus 15 degrees
     * @param right       wall in  direction of unit plus 30 degrees
     */
    public void updateDirection(double left, double leftCenter, double center, double rightCenter, double right) {
        double weight = evaluateFinalWeight(left, leftCenter, center, rightCenter, right);
        angle += MAX_ANGLE_DELTA * 2 * weight - MAX_ANGLE_DELTA;
    }

    /**
     * Value for neuron is sum of multiplications of weights to neuron values at previous layer.
     */
    private double evaluateFinalWeight(double left, double leftCenter, double center, double rightCenter, double right) {
        List<Double> input = Arrays.asList(left, leftCenter, center, rightCenter, right, 1.0);
        for (List<Double> layerWeight : unitWeights) {
            input = computeNextLayer(layerWeight, input);
        }
        return input.get(0);
    }

    private List<Double> computeNextLayer(List<Double> layerWeight, List<Double> input) {
        int neuronCount = layerWeight.size() / input.size();
        List<Double> result = new ArrayList<>(neuronCount);
        for (int i = 0; i < neuronCount; i++) {
            double neuronValue = 0;
            for (int j = 0; j < input.size(); j++) {
                neuronValue += input.get(j) * layerWeight.get(i * input.size() + j);
            }
            result.add(activationFunction(neuronValue));
        }
        result.add(1.0);//bias
        return result;
    }

    private double activationFunction(double value) {
        return 1 / (1 + Math.exp(-value));
    }

    public List<List<Double>> copyWeights() {
        return unitWeights.stream().map(ArrayList::new).collect(Collectors.toList());
    }
}

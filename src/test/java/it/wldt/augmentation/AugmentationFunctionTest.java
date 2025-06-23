package it.wldt.augmentation;

import it.wldt.augmentation.factorial.FactorialAugmentationFunction;
import it.wldt.augmentation.factorial.ShadowingWithFactorial;
import it.wldt.core.engine.DigitalTwin;
import it.wldt.core.engine.DigitalTwinEngine;

public class AugmentationFunctionTest {

    public static void main(String[] args) {
        DigitalTwinEngine engine = new DigitalTwinEngine();

        // Create ShadowingWithFactorial instance
        ShadowingWithFactorial shadowingFunction = new ShadowingWithFactorial("shadowingFunction1", 5);

        AugmentationFunction factorial = new FactorialAugmentationFunction("factorialFunction1");

        // Create dt
        try {
            DigitalTwin dt = new DigitalTwin("dt00001", shadowingFunction);
            dt.addAugmentationFunction(factorial);
            engine.addDigitalTwin(dt);
            engine.startAll();
        } catch (Exception e) {
            System.err.println("Error creating Digital Twin: " + e.getMessage());
        }
    }
}

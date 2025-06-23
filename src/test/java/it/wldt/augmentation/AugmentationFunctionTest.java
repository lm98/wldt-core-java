package it.wldt.augmentation;

import it.wldt.adapter.digital.DigitalAdapter;
import it.wldt.adapter.physical.PhysicalAdapter;
import it.wldt.augmentation.simple.DefaultDigitalAdapter;
import it.wldt.augmentation.simple.DefaultPhysicalAdapter;
import it.wldt.augmentation.simple.SimpleShadowingFunction;
import it.wldt.augmentation.simple.SimpleAugmentationFunction;
import it.wldt.core.engine.DigitalTwin;
import it.wldt.core.engine.DigitalTwinEngine;

public class AugmentationFunctionTest {

    public static void main(String[] args) {
        DigitalTwinEngine engine = new DigitalTwinEngine();

        AugmentationFunction aug = new SimpleAugmentationFunction("simple-augmentation-function");
        DigitalAdapter<?> digitalAdapter = new DefaultDigitalAdapter<>("digital-adapter");
        PhysicalAdapter physicalAdapter = new DefaultPhysicalAdapter("physical-adapter");

        // Create dt
        try {
            DigitalTwin dt = new DigitalTwin("dt00001", new SimpleShadowingFunction());
            dt.addDigitalAdapter(digitalAdapter);
            dt.addPhysicalAdapter(physicalAdapter);
            dt.addAugmentationFunction(aug);
            engine.addDigitalTwin(dt);
            engine.startAll();
        } catch (Exception e) {
            System.err.println("Error creating Digital Twin: " + e.getMessage());
        }
    }
}

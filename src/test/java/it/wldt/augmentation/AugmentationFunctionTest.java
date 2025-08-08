package it.wldt.augmentation;

import it.wldt.adapter.digital.DigitalAdapter;
import it.wldt.adapter.physical.PhysicalAdapter;
import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.augmentation.factorial.FactorialAugmentationFunction;
import it.wldt.augmentation.factorial.event.FactorialEvents;
import it.wldt.core.engine.DigitalTwin;
import it.wldt.core.engine.DigitalTwinEngine;

public class AugmentationFunctionTest {

    public static void main(String[] args) {
        DigitalTwinEngine engine = new DigitalTwinEngine();

        DigitalAdapter<?> digitalAdapter = new DefaultDigitalAdapter<>("digital-adapter");
        PhysicalAdapter physicalAdapter = new DefaultPhysicalAdapter("physical-adapter");

        // Create dt
        try {
            DigitalTwin dt = new DigitalTwin("dt00001", new TestShadowingFunction());
            dt.addDigitalAdapter(digitalAdapter);
            dt.addPhysicalAdapter(physicalAdapter);

            AugmentationEvent<?> event = new FactorialEvents.FactorialRequest(1);
            dt.addAugmentationFunction(event.getType(), new FactorialAugmentationFunction());
            engine.addDigitalTwin(dt);
            engine.startAll();
        } catch (Exception e) {
            System.err.println("Error creating Digital Twin: " + e.getMessage());
        }
    }
}

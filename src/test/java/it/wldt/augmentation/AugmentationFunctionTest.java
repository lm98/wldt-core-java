package it.wldt.augmentation;

import it.wldt.adapter.digital.DigitalAdapter;
import it.wldt.adapter.physical.PhysicalAdapter;
import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.augmentation.event.EmptyAugmentationEvent;
import it.wldt.augmentation.simple.DefaultDigitalAdapter;
import it.wldt.augmentation.simple.DefaultPhysicalAdapter;
import it.wldt.augmentation.simple.SimpleShadowingFunction;
import it.wldt.core.engine.DigitalTwin;
import it.wldt.core.engine.DigitalTwinEngine;
import it.wldt.exception.EventBusException;

public class AugmentationFunctionTest {

    public static void main(String[] args) {
        DigitalTwinEngine engine = new DigitalTwinEngine();

        DigitalAdapter<?> digitalAdapter = new DefaultDigitalAdapter<>("digital-adapter");
        PhysicalAdapter physicalAdapter = new DefaultPhysicalAdapter("physical-adapter");

        // Create dt
        try {
            DigitalTwin dt = new DigitalTwin("dt00001", new SimpleShadowingFunction());
            dt.addDigitalAdapter(digitalAdapter);
            dt.addPhysicalAdapter(physicalAdapter);

            AugmentationEvent<?> event = new EmptyAugmentationEvent();
            dt.addAugmentationFunction(event.getType(), (e) -> {
                try {
                    return new EmptyAugmentationEvent();
                } catch (EventBusException ex) {
                    throw new RuntimeException(ex);
                }
            });
            engine.addDigitalTwin(dt);
            engine.startAll();
        } catch (Exception e) {
            System.err.println("Error creating Digital Twin: " + e.getMessage());
        }
    }
}

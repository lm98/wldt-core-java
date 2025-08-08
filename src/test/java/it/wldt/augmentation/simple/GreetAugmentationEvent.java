package it.wldt.augmentation.simple;

import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.exception.EventBusException;

import java.util.Map;

public class GreetAugmentationEvent extends AugmentationEvent<String> {

    public GreetAugmentationEvent(String type) throws EventBusException {
        super(type);
    }

    public GreetAugmentationEvent(String type, String body, Map<String, Object> metadata) throws EventBusException {
        super(type, body, metadata);
    }
}

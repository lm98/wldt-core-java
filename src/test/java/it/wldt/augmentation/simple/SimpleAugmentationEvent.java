package it.wldt.augmentation.simple;

import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.exception.EventBusException;

import java.util.Map;

public class SimpleAugmentationEvent extends AugmentationEvent<Void> {

    public SimpleAugmentationEvent(String type) throws EventBusException {
        super(type);
    }

    public SimpleAugmentationEvent(String type, Void body, Map<String, Object> metadata) throws EventBusException {
        super(type, body, metadata);
    }

    @Override
    protected String getBasicEventType() {
        return EVENT_BASIC_TYPE;
    }
}

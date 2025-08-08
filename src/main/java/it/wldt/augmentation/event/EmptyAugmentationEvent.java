package it.wldt.augmentation.event;

import it.wldt.exception.EventBusException;

public class EmptyAugmentationEvent extends AugmentationEvent<Void> {
    public EmptyAugmentationEvent() throws EventBusException {
        super("empty");
    }
}

package it.wldt.augmentation.event;

import it.wldt.exception.EventBusException;

public class EmptyAugmentationEvent extends AugmentationEvent<Void> {
    private static final String EMPTY_EVENT_TYPE = "dt.augmentation.event.empty";

    public EmptyAugmentationEvent() throws EventBusException {
        super("empty");
    }

    @Override
    protected String getBasicEventType() {
        return EMPTY_EVENT_TYPE;
    }
}

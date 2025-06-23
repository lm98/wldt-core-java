package it.wldt.augmentation.factorial;

import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.exception.EventBusException;

public class FactorialIntermediateAugmentationEvent extends AugmentationEvent<Integer> {
    static final String BASIC_EVENT_TYPE = "dt.augmentation.event.factorial.intermediate";

    public FactorialIntermediateAugmentationEvent(String type, Integer body) throws EventBusException {
        super(type, body);
    }

    public FactorialIntermediateAugmentationEvent(Integer body) throws EventBusException {
        super(BASIC_EVENT_TYPE, body);
    }

    @Override
    protected String getBasicEventType() {
        return BASIC_EVENT_TYPE;
    }
}

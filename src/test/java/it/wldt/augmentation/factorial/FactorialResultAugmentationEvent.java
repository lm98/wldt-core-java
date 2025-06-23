package it.wldt.augmentation.factorial;

import it.wldt.augmentation.event.AugmentationEvent;

public class FactorialResultAugmentationEvent extends AugmentationEvent<Integer> {
    static final String BASIC_EVENT_TYPE = "dt.augmentation.event.factorial.result";

    public FactorialResultAugmentationEvent(String type, Integer body) throws Exception {
        super(type, body);
    }

    public FactorialResultAugmentationEvent(Integer body) throws Exception {
        super(BASIC_EVENT_TYPE, body);
    }

    @Override
    protected String getBasicEventType() {
        return BASIC_EVENT_TYPE;
    }
}

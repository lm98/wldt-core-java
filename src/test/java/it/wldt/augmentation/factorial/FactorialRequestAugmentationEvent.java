package it.wldt.augmentation.factorial;

import it.wldt.augmentation.event.AugmentationEvent;

public class FactorialRequestAugmentationEvent extends AugmentationEvent<Integer> {
    static final String BASIC_EVENT_TYPE = "dt.augmentation.event.factorial.request";

    public FactorialRequestAugmentationEvent(String type, Integer body) throws Exception {
        super(type, body);
    }

    public FactorialRequestAugmentationEvent(Integer body) throws Exception {
        super(BASIC_EVENT_TYPE, body);
    }

    @Override
    protected String getBasicEventType() {
        return BASIC_EVENT_TYPE;
    }
}

package it.wldt.augmentation.factorial.event;

import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.exception.EventBusException;

public class FactorialEvents {
    public static class FactorialRequest extends AugmentationEvent<Integer> {
        private static final String TYPE = "factorial.request";
        public FactorialRequest(Integer body) throws EventBusException {
            super(TYPE, body);
        }
    }

    public static class FactorialResult extends AugmentationEvent<Integer> {
        private static final String TYPE = "factorial.result";
        public FactorialResult(Integer body) throws EventBusException {
            super(TYPE, body);
        }
    }
}

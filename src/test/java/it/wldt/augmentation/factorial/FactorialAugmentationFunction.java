package it.wldt.augmentation.factorial;

import it.wldt.augmentation.AugmentationFunction;
import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.core.event.WldtEventFilter;
import it.wldt.exception.EventBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.wldt.augmentation.factorial.event.FactorialEvents.*;

import java.util.Optional;

/**
 * STATEFUL, RECURSIVE Augmentation Function as an example
 */
public class FactorialAugmentationFunction implements AugmentationFunction {
    Logger logger = LoggerFactory.getLogger(FactorialAugmentationFunction.class);
    private Integer result = 1;
    private final WldtEventFilter inputEvents = new WldtEventFilter();
    private final WldtEventFilter outputEvents = new WldtEventFilter();

    public FactorialAugmentationFunction() {
        this.inputEvents.add(FactorialRequest.buildEventType(FactorialRequest.EVENT_BASIC_TYPE, "factorial.request"));
        this.outputEvents.add(FactorialRequest.buildEventType(FactorialRequest.EVENT_BASIC_TYPE, "factorial.result"));
    }

    @Override
    public String getId() {
        return "factorial-augmentation-function";
    }

    @Override
    public WldtEventFilter inputEvents() {
        return inputEvents;
    }

    @Override
    public WldtEventFilter outputEvents() {
        return outputEvents;
    }

    @Override
    public Optional<AugmentationEvent<?>> receive(AugmentationEvent<?> input) {
        try {
            if (input instanceof FactorialRequest) {
                Integer asked = (Integer) input.getBody();
                if (asked < 2) {
                    return Optional.of(new FactorialResult(this.result));
                } else {
                    this.result *= asked;
                    return Optional.of(new FactorialRequest(asked - 1));
                }
            }
        } catch (EventBusException e) {
            logger.error("An error occurred: {}", e.getMessage());
        }
        return Optional.empty();
    }
}

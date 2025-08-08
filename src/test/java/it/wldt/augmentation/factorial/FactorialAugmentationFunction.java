package it.wldt.augmentation.factorial;

import it.wldt.augmentation.AugmentationFunction;
import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.exception.EventBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.wldt.augmentation.factorial.event.FactorialEvents.*;

/**
 * STATEFUL, RECURSIVE Augmentation Function as an example
 */
public class FactorialAugmentationFunction implements AugmentationFunction {
    Logger logger = LoggerFactory.getLogger(FactorialAugmentationFunction.class);
    private Integer result = 1;

    @Override
    public AugmentationEvent<?> receive(AugmentationEvent<?> input) {
        try {
            if (input instanceof FactorialRequest) {
                Integer asked = (Integer) input.getBody();
                if (asked < 2) {
                    return new FactorialResult(this.result);
                } else {
                    this.result *= asked;
                    return new FactorialRequest(asked - 1);
                }
            }
        } catch (EventBusException e) {
            logger.error("An error occurred: {}", e.getMessage());
        }
        return null;
    }
}

package it.wldt.augmentation.factorial;

import it.wldt.augmentation.AugmentationFunction;
import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.augmentation.event.EmptyAugmentationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactorialAugmentationFunction extends AugmentationFunction {
    private static final Logger logger = LoggerFactory.getLogger(FactorialAugmentationFunction.class);

    private Integer factorialResult = 1;

    public FactorialAugmentationFunction(String id) {
        super(id);
    }

    @Override
    protected void onAugmentationStart() {
        logger.info("{} -> Augmentation Function started", getId());
    }

    @Override
    protected void onAugmentationStop() {
        logger.info("{} -> Augmentation Function stopped", getId());
    }

    @Override
    protected void onAugmentationEvent(AugmentationEvent<?> augmentationEvent) {
        if(augmentationEvent instanceof FactorialRequestAugmentationEvent) {
            FactorialRequestAugmentationEvent requestEvent = (FactorialRequestAugmentationEvent) augmentationEvent;
            Integer number = requestEvent.getBody();
            logger.info("{} -> Received factorial request for number: {}", getId(), number);
            try {
                if(number < 0) {
                    logger.error("{} -> Invalid number for factorial: {}", getId(), number);
                    publishAugmentationEvent(new EmptyAugmentationEvent());
                } else if(number == 0 || number == 1) {
                    FactorialResultAugmentationEvent ev = new FactorialResultAugmentationEvent(factorialResult);
                    publishAugmentationEvent(ev);
                } else {
                    factorialResult = factorialResult * number;
                    FactorialIntermediateAugmentationEvent ev = new FactorialIntermediateAugmentationEvent((number - 1));
                    publishAugmentationEvent(ev);
                }
            } catch (Exception e) {
                logger.error("{} -> Error creating intermediate event: {}", getId(), e.getMessage());
            }
        } else if (augmentationEvent instanceof FactorialIntermediateAugmentationEvent) {
            logger.info("{} -> Received intermediate factorial event", getId());
            FactorialIntermediateAugmentationEvent intermediateEvent = (FactorialIntermediateAugmentationEvent) augmentationEvent;
            Integer number = intermediateEvent.getBody();
            if (number > 1) {
                try {
                    factorialResult = factorialResult * number;
                    FactorialIntermediateAugmentationEvent nextEvent = new FactorialIntermediateAugmentationEvent(number - 1);
                    publishAugmentationEvent(nextEvent);
                } catch (Exception e) {
                    logger.error("{} -> Error publishing intermediate event: {}", getId(), e.getMessage());
                }
            } else {
                try {
                    FactorialResultAugmentationEvent resultEvent = new FactorialResultAugmentationEvent(factorialResult);
                    logger.info("{} -> Final factorial result: {}", getId(), factorialResult);
                    publishAugmentationEvent(resultEvent);
                } catch (Exception e) {
                    logger.error("{} -> Error publishing result event: {}", getId(), e.getMessage());
                }
            }
        }
    }
}

package it.wldt.augmentation.simple;

import it.wldt.augmentation.AugmentationFunction;
import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.exception.EventBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAugmentationFunction extends AugmentationFunction {
    private static final Logger logger = LoggerFactory.getLogger(SimpleAugmentationFunction.class);

    public SimpleAugmentationFunction(String id) {
        super(id);
        reactToSimpleEvent();
    }

    @Override
    protected void onAugmentationStart() {
        logger.debug("SimpleAugmentationFunction - onAugmentationStart");
    }

    @Override
    protected void onAugmentationStop() {
        logger.debug("SimpleAugmentationFunction - onAugmentationStop");
    }

    @Override
    protected void onAugmentationEvent(AugmentationEvent<?> augmentationEvent) {
        logger.debug("SimpleAugmentationFunction - onAugmentationEvent: {}", augmentationEvent);
        // Here you can handle the augmentation event as needed
        // For example, you might want to log it or perform some action based on the event type
    }

    private void reactToSimpleEvent() {
        try {
            SimpleAugmentationEvent e = new SimpleAugmentationEvent("simple");
            this.addAugmentationEvent(e);
        } catch (EventBusException ex) {
            throw new RuntimeException(ex);
        }
    }
}

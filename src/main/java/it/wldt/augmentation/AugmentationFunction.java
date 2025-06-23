package it.wldt.augmentation;

import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.core.engine.DigitalTwinWorker;
import it.wldt.core.event.WldtEvent;
import it.wldt.core.event.WldtEventBus;
import it.wldt.core.event.WldtEventListener;
import it.wldt.exception.EventBusException;
import it.wldt.exception.WldtRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authors:
 *          Leonardo Micelli (leonardomicelli@gmail.com)
 * Date: 20/06/2025
 * Project: White Label Digital Twin Java Framework - (whitelabel-digitaltwin)
 *
 * This class represents an Augmentation Function, which is a process that extends the capabilities of a Digital Twin
 * beyond the basic functionalities of data collection, shadowing and exposure.
 *
 * In general, an Augmentation Function can model any type of complex computation happening inside the Digital Twin.
 * In WLDT, an AugmentationFunction is a {@link DigitalTwinWorker} that listens to {@link AugmentationEvent} events,
 * triggering specific actions based on the received events.
 */
public abstract class AugmentationFunction extends DigitalTwinWorker implements WldtEventListener {
    private static final Logger logger = LoggerFactory.getLogger(AugmentationFunction.class);

    private final String id;

    public AugmentationFunction(String id) {
        this.id = id;
    }

    @Override
    public void onWorkerStop() throws WldtRuntimeException {
        try {
            onAugmentationStop();
        }
        catch (Exception e) {
            throw new WldtRuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public void onWorkerStart() throws WldtRuntimeException {
        try {
            onAugmentationStart();
        }
        catch (Exception e) {
            throw new WldtRuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public void onEventSubscribed(String eventType) {
        logger.debug("{} -> Subscribed to: {}", id, eventType);
    }

    @Override
    public void onEventUnSubscribed(String eventType) {
        logger.debug("{} -> Unsubscribed to: {}", id, eventType);
    }

    @Override
    public void onEvent(WldtEvent<?> wldtEvent) {
        logger.debug("{} -> Received Event: {}", id, wldtEvent);
        if(wldtEvent instanceof AugmentationEvent<?>) {
            onAugmentationEvent((AugmentationEvent<?>) wldtEvent);
        }
    }

    public String getId() {
        return id;
    }

    protected void publishAugmentationEvent(AugmentationEvent<?> augmentationEvent) throws EventBusException {
        WldtEventBus.getInstance().publishEvent(this.digitalTwinId, this.id, augmentationEvent);
    }

    protected abstract void onAugmentationStart();

    protected abstract void onAugmentationStop();

    protected abstract void onAugmentationEvent(AugmentationEvent<?> augmentationEvent);
}

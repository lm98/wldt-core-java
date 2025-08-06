package it.wldt.augmentation;

import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.core.engine.DigitalTwinWorker;
import it.wldt.core.event.WldtEvent;
import it.wldt.core.event.WldtEventBus;
import it.wldt.core.event.WldtEventFilter;
import it.wldt.core.event.WldtEventListener;
import it.wldt.exception.EventBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class AugmentationFunctionExecutor implements WldtEventListener {
    private static final Logger logger = LoggerFactory.getLogger(AugmentationFunctionExecutor.class);

    private final String digitalTwinId;

    private final String id;

    private final WldtEventFilter augmentationEventFilter = new WldtEventFilter();

    private final Map<String, AugmentationFunction> augmentationFunctions = new ConcurrentHashMap<>();

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public AugmentationFunctionExecutor(String digitalTwinId, String id) {
        this.digitalTwinId = digitalTwinId;
        this.id = id;
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
        if (wldtEvent instanceof AugmentationEvent) {
            AugmentationEvent<?> event = (AugmentationEvent<?>) wldtEvent;
            String eventType = event.getType();
            AugmentationFunction function = augmentationFunctions.get(eventType);
            if (function != null) {
                logger.debug("{} -> Executing Augmentation Function for event type: {}", id, eventType);
                executorService.submit(() -> {
                    AugmentationEvent<?> result = function.receive(event);
                    if (result != null) {
                        try {
                            publishAugmentationEvent(result);
                        } catch (EventBusException e) {
                            logger.error("{} -> Error publishing Augmentation Event: {}", id, e.getMessage(), e);
                        }
                    } else {
                        logger.warn("{} -> Augmentation Function returned null for event type: {}", id, eventType);
                    }
                });
            }
        }
    }

    public String getId() {
        return id;
    }

    protected void publishAugmentationEvent(AugmentationEvent<?> augmentationEvent) throws EventBusException {
        WldtEventBus.getInstance().publishEvent(this.digitalTwinId, this.id, augmentationEvent);
    }

    /**
     * Adds an AugmentationEvent to the list of events that this AugmentationFunctionExecutor will listen to.
     * It also subscribes to the event type in the WldtEventBus.
     *
     * @param augmentationEvent The AugmentationEvent to be added.
     * @throws EventBusException If there is an error while subscribing to the event.
     */
    private void addAugmentationEvent(String augmentationEvent) throws EventBusException {
        if (augmentationEvent != null) {
            this.augmentationEventFilter.add(augmentationEvent);
            WldtEventBus.getInstance().subscribe(this.digitalTwinId, this.id, this.augmentationEventFilter, this);
        }
    }

    public void addAugmentationFunction(String event, AugmentationFunction function) throws EventBusException {
        if (function != null) {
            addAugmentationEvent(event);
            this.augmentationFunctions.put(event, function);
            logger.debug("{} -> Added Augmentation Function: {}", id, function.getClass().getSimpleName());
        } else {
            logger.warn("{} -> Attempted to add a null Augmentation Function", id);
        }
    }
}

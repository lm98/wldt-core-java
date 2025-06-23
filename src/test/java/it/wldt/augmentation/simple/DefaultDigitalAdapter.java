package it.wldt.augmentation.simple;

import it.wldt.adapter.digital.DigitalAdapter;
import it.wldt.core.state.DigitalTwinState;
import it.wldt.core.state.DigitalTwinStateChange;
import it.wldt.core.state.DigitalTwinStateEventNotification;
import org.slf4j.Logger;

import java.util.ArrayList;

public class DefaultDigitalAdapter<C> extends DigitalAdapter<C> {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DefaultDigitalAdapter.class);

    public DefaultDigitalAdapter(String id) {
        super(id);
    }

    @Override
    protected void onStateUpdate(DigitalTwinState newDigitalTwinState, DigitalTwinState previousDigitalTwinState, ArrayList<DigitalTwinStateChange> digitalTwinStateChangeList) {
        logger.debug("DefaultDigitalAdapter - onStateUpdate: newState={}, previousState={}, changes={}",
                newDigitalTwinState, previousDigitalTwinState, digitalTwinStateChangeList);
        // Here you can handle the state update as needed
        // For example, you might want to log it or perform some action based on the state change
    }

    @Override
    protected void onEventNotificationReceived(DigitalTwinStateEventNotification<?> digitalTwinStateEventNotification) {
        logger.debug("DefaultDigitalAdapter - onEventNotificationReceived: {}", digitalTwinStateEventNotification);
        // Here you can handle the event notification as needed
        // For example, you might want to log it or perform some action based on the event type
    }

    @Override
    public void onAdapterStart() {
        logger.debug("DefaultDigitalAdapter - onAdapterStart");
        notifyDigitalAdapterBound();
    }

    @Override
    public void onAdapterStop() {
        logger.debug("DefaultDigitalAdapter - onAdapterStop");
        // Here you can handle the adapter stop logic if needed
        // For example, you might want to clean up resources or notify other components
    }

    @Override
    public void onDigitalTwinSync(DigitalTwinState digitalTwinState) {
        logger.debug("DefaultDigitalAdapter - onDigitalTwinSync: {}", digitalTwinState);
        // Here you can handle the digital twin synchronization logic if needed
        // For example, you might want to log the synchronization or perform some action based on the state
    }

    @Override
    public void onDigitalTwinUnSync(DigitalTwinState digitalTwinState) {

    }

    @Override
    public void onDigitalTwinCreate() {

    }

    @Override
    public void onDigitalTwinStart() {

    }

    @Override
    public void onDigitalTwinStop() {

    }

    @Override
    public void onDigitalTwinDestroy() {

    }
}

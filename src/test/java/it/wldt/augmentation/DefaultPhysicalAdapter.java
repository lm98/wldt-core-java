package it.wldt.augmentation;

import it.wldt.adapter.physical.PhysicalAdapter;
import it.wldt.adapter.physical.PhysicalAssetDescription;
import it.wldt.adapter.physical.event.PhysicalAssetActionWldtEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultPhysicalAdapter extends PhysicalAdapter {
    private static final Logger logger = LoggerFactory.getLogger(DefaultPhysicalAdapter.class);

    public DefaultPhysicalAdapter(String id) {
        super(id);
    }

    @Override
    public void onIncomingPhysicalAction(PhysicalAssetActionWldtEvent<?> physicalActionEvent) {
        logger.debug("DefaultPhysicalAdapter - onIncomingPhysicalAction: {}", physicalActionEvent);
    }

    @Override
    public void onAdapterStart() {
        logger.debug("DefaultPhysicalAdapter - onAdapterStart");
        try {
            notifyPhysicalAdapterBound(new PhysicalAssetDescription());
        } catch (Exception e) {
            logger.error("Error notifying physical adapter bound: {}", e.getMessage());
        }
    }

    @Override
    public void onAdapterStop() {
        logger.debug("DefaultPhysicalAdapter - onAdapterStop");
    }
}

package it.wldt.augmentation.factorial;

import it.wldt.adapter.digital.event.DigitalActionWldtEvent;
import it.wldt.adapter.physical.PhysicalAssetDescription;
import it.wldt.adapter.physical.event.PhysicalAssetEventWldtEvent;
import it.wldt.adapter.physical.event.PhysicalAssetPropertyWldtEvent;
import it.wldt.adapter.physical.event.PhysicalAssetRelationshipInstanceCreatedWldtEvent;
import it.wldt.adapter.physical.event.PhysicalAssetRelationshipInstanceDeletedWldtEvent;
import it.wldt.core.event.WldtEventBus;
import it.wldt.core.model.ShadowingFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ShadowingWithFactorial extends ShadowingFunction {
    private static final Logger logger = LoggerFactory.getLogger(ShadowingWithFactorial.class);

    private Integer factorialRequest;

    public ShadowingWithFactorial(String id, Integer factorialRequest) {
        super(id);
        this.factorialRequest = factorialRequest;
    }

    @Override
    protected void onCreate() {
        logger.info("{} -> Shadowing Function created", getId());
        // send factorial request
        try {
            FactorialRequestAugmentationEvent requestEvent = new FactorialRequestAugmentationEvent(factorialRequest);
            WldtEventBus.getInstance().publishEvent(this.digitalTwinStateManager.getDigitalTwinId(), this.getId(), requestEvent);
        } catch (Exception e) {
            logger.error("{} -> Error creating factorial request event: {}", getId(), e.getMessage());
        }
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onDigitalTwinBound(Map<String, PhysicalAssetDescription> adaptersPhysicalAssetDescriptionMap) {

    }

    @Override
    protected void onDigitalTwinUnBound(Map<String, PhysicalAssetDescription> adaptersPhysicalAssetDescriptionMap, String errorMessage) {

    }

    @Override
    protected void onPhysicalAdapterBidingUpdate(String adapterId, PhysicalAssetDescription adapterPhysicalAssetDescription) {

    }

    @Override
    protected void onPhysicalAssetPropertyVariation(PhysicalAssetPropertyWldtEvent<?> physicalPropertyEventMessage) {

    }

    @Override
    protected void onPhysicalAssetEventNotification(PhysicalAssetEventWldtEvent<?> physicalAssetEventWldtEvent) {

    }

    @Override
    protected void onPhysicalAssetRelationshipEstablished(PhysicalAssetRelationshipInstanceCreatedWldtEvent<?> physicalAssetRelationshipWldtEvent) {

    }

    @Override
    protected void onPhysicalAssetRelationshipDeleted(PhysicalAssetRelationshipInstanceDeletedWldtEvent<?> physicalAssetRelationshipWldtEvent) {

    }

    @Override
    protected void onDigitalActionEvent(DigitalActionWldtEvent<?> digitalActionWldtEvent) {

    }
}

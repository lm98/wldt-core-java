package it.wldt.augmentation;

import it.wldt.augmentation.event.AugmentationEvent;
import it.wldt.core.event.WldtEventFilter;

import java.util.Optional;


public interface AugmentationFunction {
    String getId();
    WldtEventFilter getEventFilter();
    Optional<AugmentationEvent<?>> receive(AugmentationEvent<?> input);
}

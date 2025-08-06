package it.wldt.augmentation;

import it.wldt.augmentation.event.AugmentationEvent;

@FunctionalInterface
public interface AugmentationFunction {
    AugmentationEvent<?> receive(AugmentationEvent<?> input);
}

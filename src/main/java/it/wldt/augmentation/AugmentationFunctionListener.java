package it.wldt.augmentation;

public interface AugmentationFunctionListener {
    void onAugmentationFunctionAdded(AugmentationFunction augmentationFunction);
    void onAugmentationFunctionStart(AugmentationFunction augmentationFunction);
    void onAugmentationFunctionStop(AugmentationFunction augmentationFunction);
}

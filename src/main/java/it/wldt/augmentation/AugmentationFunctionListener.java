package it.wldt.augmentation;

public interface AugmentationFunctionListener {
    /**
     * This method is called when the Augmentation Function starts.
     */
    void onAugmentationStart();

    /**
     * This method is called when the Augmentation Function stops.
     */
    void onAugmentationStop();

    /**
     * This method is called when an error occurs in the Augmentation Function.
     *
     * @param errorMessage The error message describing the issue.
     */
    void onAugmentationError(String errorMessage);
}

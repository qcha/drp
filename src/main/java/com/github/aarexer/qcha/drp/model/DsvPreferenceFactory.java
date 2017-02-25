package com.github.aarexer.qcha.drp.model;

public final class DsvPreferenceFactory {
    private DsvPreferenceFactory() {
        throw new IllegalAccessError("It's factory.");
    }

    public static DsvPreference getDefaultDsvPreference() {
        return new DsvPreference();
    }
}

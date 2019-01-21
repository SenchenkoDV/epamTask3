package com.senchenko.hookah.entity;

public class Hookah {
    private int hookahId;

    public Hookah(int hookahId) {
        this.hookahId = hookahId;
    }

    @Override
    public String toString() {
        return "Hookah " + hookahId;
    }
}

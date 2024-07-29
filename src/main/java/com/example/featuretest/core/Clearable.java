package com.example.featuretest.core;

/**
 * Mark classes which have internal state which needs to be cleared between scenarios
 */
public interface Clearable {
    void clear();
}
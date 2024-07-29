package com.example.featuretest.core;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to work around the variable assignment limitations from lambda
 */
@Getter
@Setter
public class ValueHolder<T> {
    private T value;
}

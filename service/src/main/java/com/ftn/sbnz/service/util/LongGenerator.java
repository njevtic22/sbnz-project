package com.ftn.sbnz.service.util;

import java.util.NoSuchElementException;

public class LongGenerator extends NumberGenerator<Long> {
    public LongGenerator() {
        this(0L);
    }

    public LongGenerator(long start) {
        this(start, 1L);
    }

    public LongGenerator(long start, long step) {
        super(start, step);
    }

    @Override
    protected boolean willOverflow() {
        // step is positive
        return current > Long.MAX_VALUE - step;
    }

    @Override
    protected boolean willUnderflow() {
        // step is negative
        return current < Long.MIN_VALUE - step;
    }

    @Override
    public boolean isStepPositive() {
        return step > 0L;
    }

    @Override
    public boolean isStepNegative() {
        return !isStepPositive() && step != 0L;
    }

    @Override
    public Long next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Long generator has no more elements to generate.");
        }
        current += step;
        return current;
    }
}

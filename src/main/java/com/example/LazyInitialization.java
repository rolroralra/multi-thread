package com.example;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LazyInitialization {
    private static LazyInitialization INSTANCE = null;

    private LazyInitialization() { }

    public static LazyInitialization getInstance() throws InterruptedException {
        if (INSTANCE == null) {
            Thread.sleep(1);
            INSTANCE = new LazyInitialization();
        }

        return INSTANCE;
    }

}

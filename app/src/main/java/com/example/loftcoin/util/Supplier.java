package com.example.loftcoin.util;

import androidx.annotation.NonNull;

public interface Supplier<T> {
    @NonNull
    T get();
}

package com.slimenano.api.exception.inner;

import java.io.IOException;

public class DuplicateMainPluginException extends IOException {
    public DuplicateMainPluginException(String message) {
        super(message);
    }
}

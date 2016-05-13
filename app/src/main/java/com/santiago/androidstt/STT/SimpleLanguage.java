package com.santiago.androidstt.STT;

import android.support.annotation.NonNull;

/**
 * Created by santiago on 27/03/16.
 */
public enum SimpleLanguage {

    AUTO(""),
    SPANISH("es_ES"),
    ENGLISH("en_US");

    private String code;

    SimpleLanguage(@NonNull String code) {
        this.code = code;
    }

    public @NonNull String getCode() {
        return code;
    }

}

package com.santiago.androidstt.STT;

/**
 * Created by santiago on 27/03/16.
 */
public enum SimpleLanguage {

    AUTO(""),
    SPANISH("es_ES"),
    ENGLISH("en_US");

    private String code;

    SimpleLanguage(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

package com.maciejprogramuje.facebook.retrofittest;

/**
 * Created by m.szymczyk on 2018-03-14.
 */

public class ErrorResponse {
    public String error;
    public String code;

    @Override
    public String toString() {
        return error;
    }
}

package com.pst.efi.biller.util;

import com.github.f4b6a3.uuid.UuidCreator;

public class Util {

    public static String getTimeSTampId(){
        return UuidCreator.getTimeBased().toString();
    }
}

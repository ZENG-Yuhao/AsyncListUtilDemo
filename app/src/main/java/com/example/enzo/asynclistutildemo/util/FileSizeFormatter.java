package com.example.enzo.asynclistutildemo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FileSizeFormatter {
    public static final long Bt = 1024;
    public static final long Mb = Bt * 1024;
    public static final long Gb = Mb * 1024;
    public static final long Tb = Gb * 1024;
    public static final long Pb = Tb * 1024;

    public static String format(long size) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        if (size < Mb) {
            return df.format(size) + "b";
        } else if (size < Gb) {
            return df.format((double) size / Mb) + "M";
        } else if (size < Tb) {
            return df.format((double) size / Gb) + "G";
        } else if (size < Pb) {
            return df.format((double) size / Tb) + "T";
        } else {
            return df.format((double) size / Pb) + "P";
        }
    }
}

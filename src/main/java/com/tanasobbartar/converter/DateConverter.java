package com.tanasobbartar.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateConverter {

    public static String gregorianDateToJalali(String input) {

        // Parse Gregorian datetime
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        LocalDateTime dateTime =
                LocalDateTime.parse(input, formatter);

        // Extract Gregorian date
        int gy = dateTime.getYear();
        int gm = dateTime.getMonthValue();
        int gd = dateTime.getDayOfMonth();

        // Gregorian to Jalali conversion
        int[] out = {
                (gm > 2) ? (gy + 1) : gy,
                0,
                0
        };

        int[] g_d_m = {
                0, 31, 59, 90, 120, 151,
                181, 212, 243, 273, 304, 334
        };

        out[2] = 355666
                + (365 * gy)
                + ((int) ((out[0] + 3) / 4))
                - ((int) ((out[0] + 99) / 100))
                + ((int) ((out[0] + 399) / 400))
                + gd
                + g_d_m[gm - 1];

        out[0] = -1595 + (33 * ((int) (out[2] / 12053)));

        out[2] %= 12053;

        out[0] += 4 * ((int) (out[2] / 1461));

        out[2] %= 1461;

        if (out[2] > 365) {
            out[0] += (int) ((out[2] - 1) / 365);
            out[2] = (out[2] - 1) % 365;
        }

        if (out[2] < 186) {
            out[1] = 1 + (int) (out[2] / 31);
            out[2] = 1 + (out[2] % 31);
        } else {
            out[1] = 7 + (int) ((out[2] - 186) / 30);
            out[2] = 1 + ((out[2] - 186) % 30);
        }

        return out[0] + "/" + out[1] + "/" + out[2];
    }

    public static String gregorianDateToJalali(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Extract Gregorian date
        int gy = calendar.get(Calendar.YEAR);
        int gm = calendar.get(Calendar.MONTH) + 1;
        int gd = calendar.get(Calendar.DAY_OF_MONTH);

        // Gregorian to Jalali conversion
        int[] out = {
                (gm > 2) ? (gy + 1) : gy,
                0,
                0
        };

        int[] g_d_m = {
                0, 31, 59, 90, 120, 151,
                181, 212, 243, 273, 304, 334
        };

        out[2] = 355666
                + (365 * gy)
                + ((out[0] + 3) / 4)
                - ((out[0] + 99) / 100)
                + ((out[0] + 399) / 400)
                + gd
                + g_d_m[gm - 1];

        out[0] = -1595 + (33 * (out[2] / 12053));

        out[2] %= 12053;

        out[0] += 4 * (out[2] / 1461);

        out[2] %= 1461;

        if (out[2] > 365) {
            out[0] += (out[2] - 1) / 365;
            out[2] = (out[2] - 1) % 365;
        }

        if (out[2] < 186) {
            out[1] = 1 + (out[2] / 31);
            out[2] = 1 + (out[2] % 31);
        } else {
            out[1] = 7 + ((out[2] - 186) / 30);
            out[2] = 1 + ((out[2] - 186) % 30);
        }

        return out[0] + "/" + out[1] + "/" + out[2];
    }

}

package com.pylaev.dmitry;

import com.digdes.school.DatesToCronConvertException;
import com.digdes.school.DatesToCronConverter;
import java.util.Arrays;

public class DigDesTest {
    public static void main (String[] args) throws DatesToCronConvertException {

        DatesToCronConverter datesToCronConverter = new DatesToCronConverterImplementation();

        System.out.println(datesToCronConverter.getImplementationInfo());

        String[] arr1 = {"2022-01-25T08:00:00",
                "2022-01-25T08:30:00",
                "2022-01-25T09:00:00",
                "2022-01-25T09:30:00",
                "2022-01-26T08:00:00",
                "2022-01-26T08:30:00",
                "2022-01-26T09:00:00",
                "2022-01-26T09:30:00"
        };

        datesToCronConverter.convert(Arrays.asList(arr1));

        String[] arr2 = {"2022-01-24T19:53:00",
                "2022-01-24T19:54:00",
                "2022-01-24T19:55:00",
                "2022-01-24T19:56:00",
                "2022-01-24T19:57:00",
                "2022-01-24T19:58:00",
                "2022-01-24T19:59:00",
                "2022-01-24T20:00:00",
                "2022-01-24T20:01:00",
                "2022-01-24T20:02:00"
        };

        datesToCronConverter.convert(Arrays.asList(arr2));

        String[] arr3 = {"2022-01-26T09:30:00",
                "2022-01-26T09:00:00",
                "2022-01-26T08:30:00",
                "2022-01-26T08:00:00",
                "2022-01-25T09:30:00",
                "2022-01-25T09:00:00",
                "2022-01-25T08:30:00",
                "2022-01-25T08:00:00",
                "2022-01-25T10:00:00",
                "2022-01-25T12:00:00"
        };

        datesToCronConverter.convert(Arrays.asList(arr3));

//        String[] arr4 = {"2022-01-24T19:53:00",
//                "2022-01-24T19:54:00",
//                "2022-01-24T19:55:00",
//                "2022-01-24T19:56:00",
//                "2022-06-24T19:57:00",
//                "2022-07-23T19:58:00",
//                "2022-08-23T19:59:00",
//                "2022-09-23T21:00:00",
//                "2022-10-23T21:01:00",
//                "2022-11-23T21:02:00"
//        };
//
//        datesToCronConverter.convert(Arrays.asList(arr4));

        String[] arr5 = {"2022-01-24T19:53:00",
                "2022-01-24T19:54:00",
                "2022-01-24T19:55:00",
                "2022-01-24T19:56:00",
                "2022-01-24T19:57:00",
                "2022-01-23T19:58:00",
                "2022-rr-23T19:59:00",
                "2022-01-23T21:00:00",
                "2022-01-23T21:01:00",
                "2022-01-23T21:02:00"
        };

        datesToCronConverter.convert(Arrays.asList(arr5));

    }
}

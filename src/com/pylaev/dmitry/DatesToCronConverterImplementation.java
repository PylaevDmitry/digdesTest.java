package com.pylaev.dmitry;

import com.digdes.school.DatesToCronConvertException;
import com.digdes.school.DatesToCronConverter;
import java.util.*;

public class DatesToCronConverterImplementation implements DatesToCronConverter {

    @Override
    public String getImplementationInfo ( ) {
        System.out.println("Pylaev D.V.\n" + getClass().getName() + "\nGithub");
        return "Pylaev D.V.\n" + getClass().getName() + "\nGithub\n";
    }

    @Override
    public String convert (List<String> list) throws DatesToCronConvertException {
        List<TimeItem> timeItems = new ArrayList<>();
        Collections.sort(list);
        for (String s : list) { timeItems.add(new TimeItem(s)); }

        Map<String, List<TimeItem>> mapDayOfMonth = new HashMap();
        Map<String, List<TimeItem>> mapMonth = new HashMap();
        Map<String, List<TimeItem>> mapDayOfWeek = new HashMap();
        Map<String, List<TimeItem>> mapHour = new LinkedHashMap<>();

        for (TimeItem timeItem : timeItems) {
            String dayOfWeek = timeItem.getDayOfWeek();
            if (!mapDayOfWeek.containsKey(dayOfWeek)) mapDayOfWeek.put(dayOfWeek, new ArrayList<>());
            mapDayOfWeek.get(dayOfWeek).add(timeItem);

            String dayOfMonth = timeItem.getDayOfMonth();
            if (!mapDayOfMonth.containsKey(dayOfMonth)) mapDayOfMonth.put(dayOfMonth, new ArrayList<>());
            mapDayOfMonth.get(dayOfMonth).add(timeItem);

            String month = timeItem.getMonth();
            if (!mapMonth.containsKey(month)) mapMonth.put(month, new ArrayList<>());
            mapMonth.get(month).add(timeItem);

            String hour = timeItem.getHour();
            if (!mapHour.containsKey(hour)) mapHour.put(hour, new ArrayList<>());
            mapHour.get(hour).add(timeItem);
        }

        String minuteSolution = findPeriodSolution(timeItems);
        String hourSolution = findSequenceSolution(mapHour, timeItems.size());
        String dayOfMonthSolution = findMaxSolution(mapDayOfMonth, timeItems.size());
        String monthSolution = findMaxSolution(mapMonth, timeItems.size());
        String dayOfWeekSolution = findMaxSolution(mapDayOfWeek, timeItems.size());

        String[] resArr = new String[6];

        resArr[0] = "0";
        resArr[1] = (minuteSolution==null)?"*":minuteSolution;
        resArr[2] = (hourSolution==null)?"*":hourSolution;
        resArr[3] = (dayOfMonthSolution==null)?"*":dayOfMonthSolution;
        resArr[4] = (monthSolution==null)?"*":monthSolution;
        resArr[5] = (dayOfWeekSolution==null)?"*":dayOfWeekSolution;

        String result = Arrays.toString(resArr)
                .replace(",","")
                .replace("[","'")
                .replace("]","'");

        if (result.equals("'0 * * * * *'")) throw new DatesToCronConvertException();

        System.out.println(result);
        return result;
    }

    String findMaxSolution (Map <String, List<TimeItem>> map, int size) {
        List<Map.Entry<String, List<TimeItem>>> list = new ArrayList<>(map.entrySet());
        int maxLength = 0;

        for (Map.Entry<String, List<TimeItem>> stringListEntry : list) {
            int solutionLength = stringListEntry.getValue().size();
            if (solutionLength > maxLength) {
                maxLength = solutionLength;
                if (maxLength > size/2) return stringListEntry.getKey();
            }
        }
        return null;
    }

    String findSequenceSolution (Map <String, List<TimeItem>> map, int size) {
        List<Map.Entry<String, List<TimeItem>>> listHour = new ArrayList<>(map.entrySet());
        int firstHour = 25;
        int timeInterval = 1;
        List<TimeItem> tempList = new ArrayList<>();
        String tempStr = "*";

        for (int i = 0; i < listHour.size(); i++) {
            Map.Entry<String, List<TimeItem>> stringListEntry = listHour.get(i);

            int currentHour = Integer.parseInt(stringListEntry.getKey());

            if (i==0) {
                firstHour = currentHour;
                tempList = stringListEntry.getValue();
                continue;
            }

            if (currentHour-firstHour==timeInterval) {
                tempStr = firstHour + "-" + currentHour;
                tempList.addAll(stringListEntry.getValue());
                timeInterval++;
            }
        }

        if (tempList.size() > size/2) {
            return tempStr;
        }
        else return null;
    }

    String findPeriodSolution(List<TimeItem> timeItems) {
        int period = 0;
        List<TimeItem> tempList = new ArrayList<>();
        String tempStr = "*";

        for (int i = 1; i < timeItems.size(); i++) {
            int firstMinute = Integer.parseInt(timeItems.get(i-1).getMinute());
            int secondMinute = Integer.parseInt(timeItems.get(i).getMinute());

            if (i==1) {
                period =  secondMinute - firstMinute;
                if (period>1) {
                    tempStr = firstMinute + "/" + period;
                    tempList = new ArrayList<>();
                    tempList.add(timeItems.get(0));
                    tempList.add(timeItems.get(1));
                }
            }

            if (i>1 && Math.abs(secondMinute - firstMinute) == period) {
                tempList.add(timeItems.get(i));
            }
            if (tempList.size() > timeItems.size()/2) {
                return tempStr;
            }
        }
        return null;
    }
}

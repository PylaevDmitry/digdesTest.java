package com.pylaev.dmitry;

import java.util.List;

public class Solution {
    private String[] output;
    private List<TimeItem> list;
    private int length;

    public Solution(String[] output, List<TimeItem> list) {
        this.output = output;
        this.list = list;
    }

    public String[] getOutput() {
        return output;
    }

    public void setOutput(String[] output) {
        this.output = output;
    }

    public List<TimeItem> getList() {
        return list;
    }

    public void setList(List<TimeItem> list) {
        this.list = list;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

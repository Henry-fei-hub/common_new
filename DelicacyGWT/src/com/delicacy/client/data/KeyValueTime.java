package com.delicacy.client.data;

import java.util.*;

public class KeyValueTime {

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public LinkedHashMap<String, String> getValues() {
        return values;
    }

    public void setValues(LinkedHashMap<String, String> values) {
        this.values = values;
    }

    private long currentTime;
    private LinkedHashMap<String, String> values;
}

package com.example.markmyattendance;

public class RecordClass {
    int id;
    String name;
    String roll;
    String std;
    String line;
    String status;
    String lect;

    public RecordClass() {
    }

    String time;

    public String getLect() {
        return lect;
    }

    public void setLect(String lect) {
        this.lect = lect;
    }

    public RecordClass(String name, String roll, String std, String line, String status, String lect, String time) {
        this.name = name;
        this.roll = roll;
        this.std = std;
        this.line = line;
        this.status = status;
        this.lect = lect;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

package com.example.markmyattendance;

public class TimeTable {
    int id;
    String std;
    String line;
    String day;
    String sub;
    int starth,startm,endh,endm;

    public int getId() {
        return id;
    }

    public TimeTable() {
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public TimeTable(String std, String line, String day, int starth, int startm, int endh, int endm, String sub) {
        this.std = std;
        this.line = line;
        this.day = day;
        this.starth = starth;
        this.startm = startm;
        this.endh = endh;
        this.endm = endm;
        this.sub = sub;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStarth() {
        return starth;
    }

    public void setStarth(int starth) {
        this.starth = starth;
    }

    public int getStartm() {
        return startm;
    }

    public void setStartm(int startm) {
        this.startm = startm;
    }

    public int getEndh() {
        return endh;
    }

    public void setEndh(int endh) {
        this.endh = endh;
    }

    public int getEndm() {
        return endm;
    }

    public void setEndm(int endm) {
        this.endm = endm;
    }
}

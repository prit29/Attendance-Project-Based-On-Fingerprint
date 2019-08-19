package com.example.markmyattendance;

public class StudentClass {
    int id;
    String std;
    String line;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStd() {
        return std;
    }

    public StudentClass() {
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

    public StudentClass(String std, String line) {
        this.std = std;
        this.line = line;
    }
}

package com.example.markmyattendance;

public class StudentList {
    int id;
    String roll;
    String name;
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

    public void setStd(String std) {
        this.std = std;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public StudentList() {
    }

    public StudentList(String roll, String name, String std, String line) {
        this.roll = roll;
        this.name = name;
        this.std = std;
        this.line = line;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

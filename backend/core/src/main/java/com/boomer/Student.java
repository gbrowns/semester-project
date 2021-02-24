package com.boomer;

public class Student {
    private String name;
    private int regNo;

    public Student(String name, int regNo){
        this.name = name;
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }
    public int getRegNo(){
        return regNo;
    }
}

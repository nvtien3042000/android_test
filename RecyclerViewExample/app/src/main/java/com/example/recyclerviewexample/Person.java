package com.example.recyclerviewexample;

public class Person {
    private String name;
    private String phoneNumber;
    private String sms;
    private String inf;

    public Person(String name, String phoneNumber, String sms, String inf){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sms = sms;
        this.inf = inf;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getSms() {
        return sms;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    public String getInf() {
        return inf;
    }
}

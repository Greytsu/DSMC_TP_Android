package com.example.contactfavoris.models;

public class Contact {

    private String name;
    private String number;
    private int ID;

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }
    public Contact(int ID, String name, String number){
        this.name = name;
        this.number = number;
    }
    public Contact(){
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}

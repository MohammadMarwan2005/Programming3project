package com.mohammadmarwan.Structure;

import com.mohammadmarwan.Charging.ChargingSystem;
import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String description;
    private double budget;
    private ArrayList<Ticket> listOfBookedTickets = new ArrayList<>();

    private String password;

    public String getPassword() {
        return password;
    }

    public User(String name, String description, double budget) {

        User u = null;
        try {
            u = ChargingSystem.getUser(name);

        } catch (UserNameIsNotExistException e) {
//            throw new RuntimeException(e);
        }
        if (u == null) {
            this.budget = budget;
            this.name = name;
            this.description = description;
        }
        ChargingSystem.addUser(this);
    }

    public User(String name, String description, double budget, String password) {
        User u = null;
        try {
            u = ChargingSystem.getUser(name);
        } catch (UserNameIsNotExistException e) {
//            throw new RuntimeException(e);
        }

        if (u == null){
            this.name = name;
            this.description = description;
            this.budget = budget;
            this.password = password;
        }

        ChargingSystem.addUser(this);
    }


    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void discount(double amount) {
//        this.budget -= amount;
//    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                ", listOfBookedTickets=" + listOfBookedTickets +
                '}' + '\n';
    }

    public void addTicket(Ticket ticket) {
        listOfBookedTickets.add(ticket);
        ChargingSystem.addUser(this);
    }

    public ArrayList<Ticket> getListOfBookedTickets() {
        return listOfBookedTickets;
    }

    public void setListOfBookedTickets(ArrayList<Ticket> listOfBookedTickets) {
        this.listOfBookedTickets = listOfBookedTickets;
    }

    public void removeTicket(Ticket ticket) {
        listOfBookedTickets.remove(ticket);
    }

}


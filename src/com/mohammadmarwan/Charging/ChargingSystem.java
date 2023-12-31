package com.mohammadmarwan.Charging;

import com.mohammadmarwan.IO.IO;
import com.mohammadmarwan.CustomExceptions.UserNameIsNotExistException;
import com.mohammadmarwan.CustomExceptions.WrongPasswordException;
import com.mohammadmarwan.Structure.User;

import java.util.ArrayList;
import java.util.Iterator;

public class ChargingSystem {
    public static ArrayList<User> listOfUsers = IO.initialUsersList();

    public static void addUser(User user) {
        Iterator<User> iterator = listOfUsers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(user.getName()))
                iterator.remove();
        }

        listOfUsers.add(user);

        IO.saveUsersList(listOfUsers);
    }

    public static void chargeUser(User user, double amount) {
        double budgetBefore = user.getBudget();
        user.setBudget(budgetBefore + amount);
        for (int i = 0; i < listOfUsers.size(); i++)
            if (listOfUsers.get(i).getName().equals(user.getName())) {
                listOfUsers.set(i, user);
            }
        IO.saveUsersList(listOfUsers);
    }

    public static void chargeUser(String name, double amount) {
        for (User u : listOfUsers) {
            if (u.getName().equals(name)) {
                double budgetBefore = u.getBudget();
                u.setBudget(budgetBefore + amount);
            }
        }
        IO.saveUsersList(listOfUsers);
    }

    public static void discountUser(String name, double amount) {
        chargeUser(name, -1 * amount);
    }

    public static boolean userIsExist(String userName) {
        for (User u : listOfUsers) {
            if (u.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public static User getUser(String userName) throws UserNameIsNotExistException {
        for (User u : listOfUsers) {
            if (u.getName().equals(userName))
                return u;
        }
        throw new UserNameIsNotExistException();
    }

    public static User getUser(String userName, String password) throws UserNameIsNotExistException, WrongPasswordException {
        for (User u : listOfUsers) {
            if (u.getName().equals(userName)) {
                if (u.getPassword().equals(password))
                    return u;
                else throw new WrongPasswordException();
            }
        }
        throw new UserNameIsNotExistException();
    }
}

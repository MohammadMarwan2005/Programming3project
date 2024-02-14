package com.mohammadmarwan.IO;

import com.mohammadmarwan.Structure.Cinema;
import com.mohammadmarwan.Structure.Movie;
import com.mohammadmarwan.Structure.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IO {
    public static String UNIQUE_STRING = "USERS";

    public static void saveCinema(Cinema cinema) {
        File file = new File(cinema.getName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(cinema);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Cinema readFile(String path) throws Exception {
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        Cinema c = (Cinema) ois.readObject();
        ois.close();
        return c;
    }

    public static void saveUsersList(ArrayList<User> list) {
        File file = new File(UNIQUE_STRING);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream listOutputStream = new ObjectOutputStream(fileOutputStream);
            listOutputStream.writeObject(list);
            listOutputStream.flush();
            listOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Cinema createOldOrNewCinema(String name,
                                              List<Movie> movies,
                                              int numberOfSeats
    ) {
        Cinema c;
        try {
            c = IO.readFile(name);
        } catch (Exception e) {
            c = new Cinema(name, movies, numberOfSeats);
        }
        IO.saveCinema(c);
        return c;
    }

    public static ArrayList<User> readUsersList() throws Exception {
        File file = new File(UNIQUE_STRING);
        FileInputStream fileInputStream = new FileInputStream(file); // FileNotFoundException
        ObjectInputStream listInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<User> res = (ArrayList<User>) listInputStream.readObject();
        listInputStream.close();
        return res;
    }

    public static ArrayList<User> initialUsersList() {
        ArrayList<User> res = new ArrayList<>();
        try {
            res = readUsersList();
        } catch (Exception ignored) {
        } finally {
            saveUsersList(res);
        }
//        ChargingSystem.listOfUsers = res;
        return res;
    }


}

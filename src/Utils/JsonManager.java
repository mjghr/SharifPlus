package Utils;

import Model.Order;
import Model.Product.Product;
import Model.User;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class JsonManager {
    private static final Gson gson = new Gson();

    public static void writeArrToJson(ArrayList list, String path) {
        try {

            Writer writer = Files.newBufferedWriter(Paths.get(path));

            // convert books object to JSON file
            gson.toJson(list, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static ArrayList<User> readUsersFromJson(String path) {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(path));

            // convert JSON array to list of books
            ArrayList<User> userss = new ArrayList<>();
            try {
                userss.addAll(Arrays.asList(gson.fromJson(reader, User[].class)));
                reader.close();
            } catch (NullPointerException e) {
                System.out.println("Null khordi kos khol.");
                reader.close();
            }

            return userss;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void writeMapArrToFile(HashMap<Ingredient, Integer> map) {
        ArrayList<Ingredient> ingredients = new ArrayList<>(map.keySet());
        writeArrToJson(ingredients, "src\\resources\\storageIngredients.json");
    }

    public static ArrayList<User> readOrdersFromJson(String path) {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(path));

            // convert JSON array to list of books
            ArrayList<User> userss = new ArrayList<>();
            try {
                userss.addAll(Arrays.asList(gson.fromJson(reader, User[].class)));
                reader.close();
            } catch (NullPointerException e) {
                System.out.println("Null khordi kos khol.");
                reader.close();
            }

            return userss;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Order> readOrderFromJson(String path) {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(path));

            // convert JSON array to list of books
            ArrayList<Order> orders = new ArrayList<>();
            try {
                orders.addAll(Arrays.asList(gson.fromJson(reader, Order[].class)));
                reader.close();
            } catch (NullPointerException e) {
                System.out.println("Null khordi kos khol.");
                reader.close();
            }

            return orders;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Ingredient> readStorageFromJson() {
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src\\resources\\storageIngredients.json"));

            // convert JSON array to list of books
            ArrayList<Ingredient> userss = new ArrayList<>();
            try {
                userss.addAll(Arrays.asList(gson.fromJson(reader, Ingredient[].class)));
                reader.close();
            } catch (NullPointerException e) {
                System.out.println("Null khordi kos khol.");
                reader.close();
            }

            return userss;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void writeLogToText(String str) {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            FileWriter writer = new FileWriter("log.txt", true);
            writer.write(dtf.format(dateTime) + " => " + str + "\n");
            writer.close();
        } catch (IOException e) {
        }
    }
}

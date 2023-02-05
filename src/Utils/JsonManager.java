package Utils;

import Controller.UserController;
import Model.User;
import com.google.gson.Gson;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class JsonManager {
    private static final Gson gson = new Gson();

    public static <T> void writeArrToJson(ArrayList<T> list, String path) {
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

    public static void writeUsersToJson() {
        try {

            Writer writer = Files.newBufferedWriter(Paths.get("users.json"));

            // convert books object to JSON file
            gson.toJson(UserController.getUsers(), writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<User> readArrayFromJson(String path){
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(path));

            // convert JSON array to list of books
            ArrayList<User> userss = new ArrayList<>();
            userss.addAll(Arrays.asList(gson.fromJson(reader, User[].class)));

            // close reader
            reader.close();
            return userss;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

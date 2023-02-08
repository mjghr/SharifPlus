package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FileManager {

    public static void writeArrToFile(ArrayList list){
        try {

            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert books object to JSON file
            mapper.writeValue(Paths.get("books.json").toFile(), list);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

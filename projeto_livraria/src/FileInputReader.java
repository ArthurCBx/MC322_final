import java.io.BufferedReader;
import java.io.File;

public class FileInputReader {
    public static void read(File file){
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader())) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.startsWith(""))
            }
        } catch (Exception e) {
            e.printStackTrace();
    }
}

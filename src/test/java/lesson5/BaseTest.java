package lesson5;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    Properties props = new Properties();
    private static InputStream parameters;

    static {
        try {
            parameters = new FileInputStream("src/main/resources/test.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void setNewId(String newId) {
        props.load(parameters);
        props.setProperty("NewID", newId);
        props.store(new FileOutputStream("src/main/resources/test.properties"), null);
    }
    @SneakyThrows
    public int getNewId() {
        props.load(parameters);
        return Integer.parseInt(props.getProperty("NewID"));
    }

}

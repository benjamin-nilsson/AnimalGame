package animalgame.fileutilities;

import java.io.*;

public class FilesUtils {
    // todo: make messages if it failed to save file
    public static void writeFile(String fileName, Object objects) {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(fileName, false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objects);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object readFile(String fileName) {
        ObjectInputStream objectInputStream = null;
        Object students = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            students = objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception ignore) {}

        return students;
    }

}

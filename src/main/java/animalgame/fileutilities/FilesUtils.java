package animalgame.fileutilities;

import java.io.*;

/**
 * Able to convert an object into a file and then take that file and convert it back into an object.
 * @author Lara Ibrahim, William Hökegård, Benjamin Nilsson, Fredrik Jonsson.
 */
public class FilesUtils {
    /**
     * Takes an object and converts it into a file.
     * @param fileName the name the user wants the file to be saved as.
     * @param object an instance of any class.
     */
    public static void writeFile(String fileName, Object object) {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(fileName, false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a file into an object and returns it to the user.
     * @param fileName the name of the file that we want to access.
     * @return the object of the read file.
     */
    public static Object readFile(String fileName) {
        ObjectInputStream objectInputStream = null;
        Object object = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception ignore) {}

        return object;
    }
}

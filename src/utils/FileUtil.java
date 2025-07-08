package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.Dictionary;
import model.Lang;

public class FileUtil {

    private static final Gson gson = new Gson();

    public static String readCurrentLang() {
        Lang lang = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("lang.txt"))) {
            lang = (Lang) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return "";
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading CanBo: " + e.getMessage());
        }
        if (lang == null) {
            return "";
        }
        return lang.getKey();
    }

    public static void setLang(Lang lang) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lang.txt", false))) {
            oos.writeObject(lang);
            System.out.println("Current language is set to " + lang.getName() + "!");
        } catch (Exception e) {
            System.out.println("Error saving CanBo: " + e.getMessage());
        }
    }

    private static List<Dictionary> readAllDictionaries(String lang) {
        String fileName = "dictionary-" + lang + ".json";
        List<Dictionary> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Type listType = new TypeToken<List<Dictionary>>(){}.getType();
            list = gson.fromJson(reader, listType);
            if (list == null) list = new ArrayList<>();
        } catch (IOException e) {
            // File có thể chưa tồn tại, trả về list rỗng
        }
        return list;
    }

    private static void writeAllDictionaries(List<Dictionary> list, String lang) {
        String fileName = "dictionary-" + lang + ".json";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("Error writing dictionary: " + e.getMessage());
        }
    }

    public static void addToFile(Dictionary dictionary, String lang) {
        List<Dictionary> list = readAllDictionaries(lang);
        list.add(dictionary);
        writeAllDictionaries(list, lang);
        System.out.println("Saved successfully!");
    }

    public static List<Dictionary> searchInFile(String keyword, String lang) {
        List<Dictionary> all = readAllDictionaries(lang);
        List<Dictionary> results = new ArrayList<>();
        for (Dictionary d : all) {
            if (keyword.isEmpty() || d.getKeyWord().equalsIgnoreCase(keyword)) {
                results.add(d);
            }
        }
        return results;
    }

    public static void deleteFromFile(String keyWord, String lang) {
        List<Dictionary> list = readAllDictionaries(lang);
        list.removeIf(d -> d.getKeyWord().equalsIgnoreCase(keyWord));
        writeAllDictionaries(list, lang);
        System.out.println("Deleted successfully!");
    }
}

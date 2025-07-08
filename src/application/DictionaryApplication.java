package application;

import model.Dictionary;
import service.DictionaryService;
import utils.InputUtil;
import utils.MenuUtil;

public class DictionaryApplication {

    private static final DictionaryService service = new DictionaryService();
    private static String currentLanguage = "en"; // Default language

    public static void main(String[] args) throws Exception {
        dictionaryApplication();
    }

    public static void dictionaryApplication() throws Exception {
        boolean check = true;
        int choice = 0;

        while (check) {
            MenuUtil.displayMenu();
            choice = InputUtil.inputNumber();
            switch (choice) {
                case 1:
                    searchDictionary();
                    break;
                case 2:
                    addDictionary();
                    break;
                case 3:
                    switchLanguage();
                    break;
                case 4:
                    System.out.println("----------------- Tam Biet Cau Chu Nho ---------------------");
                    check = false;
                    break;
                default:
                    System.out.println("----------------- Tam Biet Cau Chu Nho ---------------------");
                    check = false;
                    break;
            }
        }
    }

    public static void searchDictionary() {
        System.out.print("Enter word to search (leave empty to list all): ");
        String keyword = InputUtil.inputString();
        service.searchWord(keyword);
    }

    public static void addDictionary() {
        System.out.print("Enter new word: ");
        String keyWord = InputUtil.inputString();

        System.out.print("Enter definition: ");
        String definition = InputUtil.inputString();

        Dictionary dictionary = new Dictionary(keyWord, definition);
        service.addWord(dictionary);
    }

    public static void switchLanguage() {
        System.out.print("Enter language (en/ja): ");
        String lang = InputUtil.inputString();
        service.switchLanguage(lang);
        currentLanguage = lang;
    }
}

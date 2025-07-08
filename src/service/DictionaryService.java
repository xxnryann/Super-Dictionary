package service;

import java.util.List;
import application.SystemLang;
import model.Dictionary;
import model.Lang;
import utils.FileUtil;

public class DictionaryService {

    public void addWord(Dictionary dictionary) {
        String lang = FileUtil.readCurrentLang();
        FileUtil.addToFile(dictionary, lang);
        System.out.println("---Add new key successfully!");
    }

    public void searchWord(String keyWord) {
        String lang = FileUtil.readCurrentLang();
        List<Dictionary> dictionaries = FileUtil.searchInFile(keyWord, lang);
        for(Dictionary dic: dictionaries) {
            System.out.println("--- Word: "+dic.getKeyWord()+", Definition: "+dic.getDefinition()+" ---");
        }
    }

    public void switchLanguage(String lang) {
        String name = "";
        switch (lang) {
            case "en":
                name = SystemLang.ENGLISH;
                break;
            case "ja":
                name = SystemLang.JAPANESE;
                break;
            default:
                break;
        }
        Lang object = new Lang(lang, name);
        FileUtil.setLang(object);
        System.out.println("---Language switched to " + name + "!");
    }
}

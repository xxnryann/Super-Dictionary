package model;

import java.io.Serializable;

public class Dictionary implements Serializable {

    private String keyWord;

    private String definition;

    public Dictionary() {

    }

    public Dictionary(String keyWord, String definition) {
        this.keyWord = keyWord;
        this.definition = definition;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String toJson() {
        return String.format("{\"keyWord\":\"%s\", \"definition\":\"%s\"}", escape(keyWord), escape(definition));
    }

    public static Dictionary fromJson(String jsonLine) {
        String key = jsonLine.replaceAll(".*\"keyWord\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        String value = jsonLine.replaceAll(".*\"definition\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        return new Dictionary(key, value);
    }

    private static String escape(String text) {
        return text.replace("\"", "\\\"");
    }
}

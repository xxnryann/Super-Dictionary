package model;

import java.io.Serializable;

public class Lang implements Serializable{

    private String key;

    private String name;

    public Lang() {

    }

    public Lang(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

package com.example.myapplicationandroid2023.weather;

public class Weather {

    private int id;
    private String description;
    private String icon;
    private float temp;
    private float humidity;
    private float temp_min;
    private float temp_max;
    private byte[] iconData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        switch(id){
            default: this.setDescription("descrição indisponível"); break;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public byte[] getIconData() {
        return iconData;
    }

    public void setIconData(byte[] iconData) {
        this.iconData = iconData;
    }
}

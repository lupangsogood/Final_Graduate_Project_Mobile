package com.autchariya.kiklik.makeuptutorial4.simulate.model;

import com.google.gson.annotations.SerializedName;

public class Makeup {

    @SerializedName("TYPE_ID")
    private String Type;
    @SerializedName("STYLE_ID")
    private String Style;
    @SerializedName("IMAGE_PATH")
    private String Image;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "Makeup{" +
                "Type='" + Type + '\'' +
                ", Style='" + Style + '\'' +
                ", Image='" + Image + '\'' +
                '}';

    }
}

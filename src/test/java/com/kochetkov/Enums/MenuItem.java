package com.kochetkov.Enums;

public enum MenuItem {
    SHOP("Магазин"),
    MAC("Mac"),
    IPAD("iPad"),
    IPHONE("iPhone"),
    WATCH("Watch"),
    AIRPODS("AirPods");

    private final String desc;

    MenuItem(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
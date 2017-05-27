package com.hamster.map;

/**
 * Created by opabinia on 2017/5/26.
 */
public enum MapEnum {
    A_MAP("高德", 0);

    private String name;
    private Integer index;

    MapEnum(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}

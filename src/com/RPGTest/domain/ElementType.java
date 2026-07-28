package com.RPGTest.domain;

import java.util.Random;

/**
 * 元素系统：定义元素常量、元素名称映射、克制关系
 */
public class ElementType {
    // 元素字符常量
    public static final char FIRE   = '火';
    public static final char ICE    = '冰';
    public static final char LIGHT  = '光';
    public static final char DARK   = '暗';
    public static final char WIND   = '风';
    public static final char EARTH  = '土';
    public static final char NONE   = '无';
    public static final char HEAL   = '愈';

    // 元素对应的firstName
    public static final String[] ELEMENT_NAMES = {
        "火焰",
        "寒冰",
        "闪光",
        "黑暗",
        "疾风",
        "磐岩"
    };

    // 元素字符数组
    public static final char[] ELEMENTS = {
        FIRE, ICE, LIGHT, WIND, EARTH, DARK
    };

    /**
     * 获取某元素的弱点（被什么元素克制）
     */
    public static char getWeakness(char element) {
        if (element == FIRE)   return ICE;
        if (element == ICE)    return FIRE;
        if (element == LIGHT)  return DARK;
        if (element == WIND)   return EARTH;
        if (element == EARTH)  return WIND;
        if (element == DARK)   return LIGHT;
        return NONE;
    }

    /**
     * 根据firstName获取对应的元素字符
     */
    public static char getElementByName(String firstName) {
        if (firstName == null) return NONE;
        if (firstName.equals(ELEMENT_NAMES[0])) return FIRE;
        if (firstName.equals(ELEMENT_NAMES[1])) return ICE;
        if (firstName.equals(ELEMENT_NAMES[2])) return LIGHT;
        if (firstName.equals(ELEMENT_NAMES[3])) return DARK;
        if (firstName.equals(ELEMENT_NAMES[4])) return WIND;
        if (firstName.equals(ELEMENT_NAMES[5])) return EARTH;
        return NONE;
    }

    /**
     * 随机获取一个元素firstName
     */
    public static String getRandomElementName() {
        Random r = new Random();
        return ELEMENT_NAMES[r.nextInt(ELEMENT_NAMES.length)];
    }

    /**
     * 随机获取一个元素字符
     */
    public static char getRandomElement() {
        Random r = new Random();
        return ELEMENTS[r.nextInt(ELEMENTS.length)];
    }
}

package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import java.util.Scanner;

/**
 * 特殊事件基类
 */
public abstract class SpecialEvent {
    public String name;       // 事件名
    public int weight;        // 抽取权重
    public String description;// 事件描述

    public SpecialEvent(String name, int weight, String description) {
        this.name = name;
        this.weight = weight;
        this.description = description;
    }

    /** 执行事件，返回 true 表示已触发 */
    public abstract boolean execute(MyCharacter player, Scanner sc);
}

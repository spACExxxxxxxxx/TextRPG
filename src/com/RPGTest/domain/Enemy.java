package com.RPGTest.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 敌人类
 * firstName = 元素类型
 * lastName = 职业类型
 * 通过随机组合实现自动生成
 */
public class Enemy extends Character {

    // 职业基础属性表（lastName → {HP, MP, ATK, DEF}）
    private static final Map<String, int[]> CLASS_STATS = new HashMap<>();
    static {
        CLASS_STATS.put("骑士", new int[]{120, 30, 30, 20});  // 均衡
        CLASS_STATS.put("剑士", new int[]{150, 10, 35, 20});  // atk hp高，mp低
        CLASS_STATS.put("法师", new int[]{ 75, 60, 30,  0});  // MP高, ATK高, 脆皮
        CLASS_STATS.put("坦克", new int[]{210, 10, 20, 25});  // hp def高，mp atk低
        CLASS_STATS.put("弓手", new int[]{105, 20, 30, 10});  // atk高，def低
        CLASS_STATS.put("刺客", new int[]{ 60, 10, 40,  0});  // atk高，def mp低
    }

    // 职业每场成长率表（lastName → {HP, MP, ATK, DEF} 每场增量）
    private static final Map<String, int[]> CLASS_GROWTH = new HashMap<>();
    static {
        CLASS_GROWTH.put("骑士", new int[]{ 12,  2, 3, 1});  // 均衡
        CLASS_GROWTH.put("剑士", new int[]{ 15,  1, 5, 1});  // 血牛战士
        CLASS_GROWTH.put("法师", new int[]{  8,  5, 3, 0});  // 魔力成长
        CLASS_GROWTH.put("坦克", new int[]{ 18,  1, 2, 2});  // 沙包
        CLASS_GROWTH.put("弓手", new int[]{ 10,  2, 5, 1});  // 物理输出
        CLASS_GROWTH.put("刺客", new int[]{  8,  1, 6, 0});  // 玻璃大炮
    }

    public static final String[] LAST_NAMES = {"骑士", "剑士", "法师", "坦克", "弓手" , "刺客"};

    /**
     * 创建一个敌人
     * @param firstName 元素名（如"火焰"）
     * @param lastName  职业名（如"骑士"）
     * @param battleCount 当前战斗场次（决定属性成长）
     */
    public Enemy(String firstName, String lastName, int battleCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isPlayer = false;

        // 根据lastName获取基础属性
        int[] baseStats = CLASS_STATS.getOrDefault(lastName, new int[]{100, 30, 25, 10});

        // 根据职业成长率线性成长
        int[] growth = CLASS_GROWTH.getOrDefault(lastName, new int[]{8, 2, 2, 1});
        this.hp = baseStats[0] + battleCount * growth[0];
        this.maxHp = this.hp;
        this.mp = baseStats[1] + battleCount * growth[1];
        this.maxMp = this.mp;
        this.atk = baseStats[2] + battleCount * growth[2];
        this.def = baseStats[3] + battleCount * growth[3];

        // 根据firstName确定元素和弱点
        char element = ElementType.getElementByName(firstName);
        this.weak = ElementType.getWeakness(element);

        this.skills = new java.util.ArrayList<>();
    }

    public Enemy() {
        this.isPlayer = false;
    }

    /**
     * 设置敌人的技能（由SkillPool分配）
     */
    public void setSkills(java.util.ArrayList<Skill> skillList) {
        this.skills = skillList;
    }
}

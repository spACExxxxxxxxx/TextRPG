package com.RPGTest.domain;

/**
 * 技能基类
 * 通过继承此类实现技能的便捷创建
 */
public class Skill {
    public String name;       // 技能名称
    public double power;      // 技能威力（伤害=power*atk，治疗=power）
    public int cost;          // MP消耗
    public char element;      // 元素属性
    public boolean isSpecial; // 是否为特色技能（true=特色技能池，false=普通技能池）
    public boolean isAOE = false; // 是否为群攻技能
    public boolean isTemporary = false; // 是否为临时技能（战斗结束后清除）
    public boolean isBuff = false; // 是否为自身增益技能（敌人AI优先使用）
    public String description;// 技能描述
    public int price;

    public Skill() {}

    public Skill(String name, double power, int cost, char element,int price) {
        this.name = name;
        this.power = power;
        this.cost = cost;
        this.element = element;
        this.price = price;
    }

    public Skill(String name, double power, int cost, char element, boolean isSpecial) {
        this.name = name;
        this.power = power;
        this.cost = cost;
        this.element = element;
        this.isSpecial = isSpecial;
    }

    /**
     * 伤害技能：对目标造成伤害
     * 元素克制时伤害×2
     */
    public void damageSkill(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int damage = (int)(this.power * owner.atk);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damage *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        System.out.println("对 " + target.firstName + target.lastName + " 造成了 " + damage + " 点伤害");
        target.takePhysicalDamage(damage);
    }

    /**
     * 治疗技能：恢复owner的HP
     */
    public void healSkill(Character owner) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！恢复了 " + this.power + " 点HP");
        owner.heal((int)this.power);
    }

    /**
     * 使用技能（根据技能类型自动判断伤害/治疗）
     */
    public void use(Character owner, Character target) {
        if (this.element == ElementType.HEAL) {
            healSkill(owner);
        } else {
            damageSkill(owner, target);
        }
    }

    /**
     * 简短格式（无描述），用于战后状态面板
     */
    public String toBriefString() {
        return name + "[威力:" + power
                + "|消耗MP:" + cost + "|元素:" + element + "]";
    }

    @Override
    public String toString() {
        return name + "[威力:" + power
                + "|消耗MP:" + cost + "|元素:" + element + "]  " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Skill other) {
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

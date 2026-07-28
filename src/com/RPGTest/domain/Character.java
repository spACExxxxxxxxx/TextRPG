package com.RPGTest.domain;

import java.util.ArrayList;

public class Character {
    public String firstName;
    public String lastName;
    public int hp;
    public int maxHp;
    public int atk;
    public int def;
    public int mp;
    public int maxMp;
    public boolean inDefense = false;
    public ArrayList<Skill> skills;      // 技能列表（Skill对象）
    public ArrayList<Buff> buffs = new ArrayList<>();  // Buff列表
    public int tempHpBoost = 0;    // 战斗内临时HP提升量
    public int tempMpBoost = 0;    // 战斗内临时MP提升量
    public int tempAtkBoost = 0;   // 战斗内临时ATK提升量
    public int tempDefBoost = 0;   // 战斗内临时DEF提升量
    public char weak;
    public boolean isPlayer = false;     // 是否为玩家

    public Character(String firstName, String lastName, int hp, int mp, int atk, int def) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hp = hp;
        this.maxHp = hp;
        this.mp = mp;
        this.maxMp = mp;
        this.atk = atk;
        this.def = def;
        this.skills = new ArrayList<>();
    }

    public Character() {
        this.skills = new ArrayList<>();
    }

    public boolean isHpAlive() {
        return hp > 0;
    }

    public boolean isMpAlive() {
        return mp > 0;
    }

    public void heal(int heal) {
        hp += heal;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    /**
     * 受到伤害，计算防御减伤和防御状态
     */
    public void takePhysicalDamage(int damage) {
        double reduction = 100.0 / (100.0 + this.def);
        int actualDamage;
        if (inDefense) {
            actualDamage = (int) (damage * reduction / 2);
            inDefense = false;
        } else {
            actualDamage = (int) (damage * reduction);
        }
        if (actualDamage <= 0) actualDamage = 1;
        this.hp -= actualDamage;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.firstName + this.lastName + " 受到 " + actualDamage + " 点伤害 [剩余HP:" + this.hp + "/" + this.maxHp + "]");
    }

    public void takeMagicalDamage(int damage) {
        int actualDamage = damage;
        if (actualDamage <= 0) actualDamage = 1;
        this.hp -= actualDamage;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.firstName + this.lastName + " 受到 " + actualDamage + " 点伤害 [剩余HP:" + this.hp + "/" + this.maxHp + "]");
    }

    /**
     * 消耗MP，成功返回true
     */
    public boolean mpCost(int mpCost) {
        if (this.mp < mpCost) {
            return false;
        }
        this.mp -= mpCost;
        return true;
    }

    private String hpBar() {
        int bars = (int)((double)hp / maxHp * 10);
        if (bars < 0) bars = 0;
        if (bars > 10) bars = 10;
        return "█".repeat(bars) + "░".repeat(10 - bars);
    }

    /**
     * 普通攻击
     */
    public void normalAttack(Character target) {
        System.out.println(this.firstName + this.lastName + " 进行普通攻击！");
        int damage = this.atk;
        target.takePhysicalDamage(damage);
        mp += 8;
        if (mp > maxMp) mp = maxMp;
    }

    /**
     * 施加Buff（正向/负向统一入口）
     * @param percent 目标属性百分比，如 20 = 20%
     */
    public void applyBuff(BuffType type, int percent, int turns) {
        boolean up = isUpBuff(type);

        // 根据目标当前属性计算实际变化量
        int base = getBuffBaseStat(type);
        int actualValue = base * percent / 100;
        if (percent > 0 && actualValue <= 0) actualValue = 1;

        // 替换：先移除同类型旧buff
        for (int i = buffs.size() - 1; i >= 0; i--) {
            if (buffs.get(i).type == type) {
                Buff old = buffs.get(i);
                applyBuffStat(old.type, isUpBuff(old.type) ? -old.value : old.value);
                buffs.remove(i);
                break;
            }
        }

        // 应用新buff
        buffs.add(new Buff(type, actualValue, percent, turns));
        applyBuffStat(type, up ? actualValue : -actualValue);
        String duration = turns == -1 ? "整场" : turns + "回合";
        System.out.println(this.firstName + this.lastName + " " + getBuffName(type)
                + (up ? "上升" : "下降") + " " + percent + "% [" + duration + "]");
    }

    private boolean isUpBuff(BuffType type) {
        return type == BuffType.ATK_UP || type == BuffType.DEF_UP
            || type == BuffType.MAXHP_UP || type == BuffType.MAXMP_UP;
    }

    /**
     * 获取buff对应属性的当前值（用于百分比计算）
     */
    private int getBuffBaseStat(BuffType type) {
        return switch (type) {
            case ATK_UP, ATK_DOWN -> atk;
            case DEF_UP, DEF_DOWN -> def;
            case MAXHP_UP, MAXHP_DOWN -> maxHp;
            case MAXMP_UP, MAXMP_DOWN -> maxMp;
        };
    }

    private void applyBuffStat(BuffType type, int delta) {
        switch (type) {
            case ATK_UP, ATK_DOWN -> atk += delta;
            case DEF_UP, DEF_DOWN -> def += delta;
            case MAXHP_UP, MAXHP_DOWN -> {
                maxHp += delta;
                if (hp > maxHp) hp = maxHp;
            }
            case MAXMP_UP, MAXMP_DOWN -> {
                maxMp += delta;
                if (mp > maxMp) mp = maxMp;
            }
        }
    }

    private String getBuffName(BuffType type) {
        return switch (type) {
            case ATK_UP, ATK_DOWN -> "攻击力";
            case DEF_UP, DEF_DOWN -> "防御力";
            case MAXHP_UP, MAXHP_DOWN -> "最大生命";
            case MAXMP_UP, MAXMP_DOWN -> "最大魔力";
        };
    }

    /**
     * 回合开始时调用，处理buff过期
     */
    public void tickBuffs() {
        for (int i = buffs.size() - 1; i >= 0; i--) {
            Buff b = buffs.get(i);
            if (b.tick()) {
                applyBuffStat(b.type, isUpBuff(b.type) ? -b.value : b.value);  // 恢复属性
                System.out.println(this.firstName + this.lastName + " 的" + getBuffName(b.type) + "恢复了！");
                buffs.remove(i);
            }
        }
    }

    /**
     * 战后清除所有buff并恢复临时属性
     */
    public void clearBuffs() {
        for (Buff b : buffs) {
            applyBuffStat(b.type, isUpBuff(b.type) ? -b.value : b.value);
        }
        buffs.clear();
        // 恢复临时属性提升
        maxHp -= tempHpBoost;
        if (hp > maxHp) hp = maxHp;
        maxMp -= tempMpBoost;
        if (mp > maxMp) mp = maxMp;
        atk -= tempAtkBoost;
        def -= tempDefBoost;
        tempHpBoost = 0;
        tempMpBoost = 0;
        tempAtkBoost = 0;
        tempDefBoost = 0;
    }

    /**
     * 显示角色信息
     */
    public void show() {
        System.out.println("┌─────────────────────────────");
        System.out.println("│ " + this.firstName + " " + this.lastName);
        System.out.println("│ HP: " + this.hp + "/" + this.maxHp
                + "  MP: " + this.mp + "/" + this.maxMp);
        System.out.println("│ ATK: " + this.atk + "  DEF: " + this.def);
        System.out.print("│ 技能: ");
        if (skills.isEmpty()) {
            System.out.println("无");
        } else {
            System.out.println();
            for (Skill s : skills) {
                System.out.println("│   - " + s.name + " (消耗MP:" + s.cost + ")");
            }
        }
        System.out.println("└─────────────────────────────");
    }

    /**
     * 简略显示（战斗中）
     */
    public void showBrief() {
        System.out.print(firstName + lastName
                + " [" + hpBar() + " HP:" + hp + "/" + maxHp
                + " MP:" + mp + "/" + maxMp
                + " ATK:" + atk + " DEF:" + def + "]");
        if (!buffs.isEmpty()) {
            System.out.print(" [");
            for (int i = 0; i < buffs.size(); i++) {
                Buff d = buffs.get(i);
                String symbol = switch (d.type) {
                    case ATK_UP -> "ATK↑";
                    case DEF_UP -> "DEF↑";
                    case MAXHP_UP -> "MHP↑";
                    case MAXMP_UP -> "MMP↑";
                    case ATK_DOWN -> "ATK↓";
                    case DEF_DOWN -> "DEF↓";
                    case MAXHP_DOWN -> "MHP↓";
                    case MAXMP_DOWN -> "MMP↓";
                };
                System.out.print(symbol + d.percent + "%");
                if (d.turnsLeft > 0) System.out.print("(" + d.turnsLeft + "T)");
                if (i < buffs.size() - 1) System.out.print(" ");
            }
            System.out.print("]");
        }
        System.out.println();
    }
}

package com.RPGTest.domain;

import java.util.ArrayList;

public class MyCharacter extends Character {
    public int skillPoints = 0;   // 可分配点数
    public int battleCount = 0;   // 战斗场次
    public int eventCount = 0;    // 触发特殊事件次数
    public ArrayList<Skill> soldSkills = new ArrayList<>();   // 已出售技能（商店抓取时排除）

    public MyCharacter(String firstName, String lastName, int hp, int mp, int atk, int def) {
        super(firstName, lastName, hp, mp, atk, def);
        this.isPlayer = true;
        this.skills = new ArrayList<>();
    }

    public MyCharacter() {
        this.isPlayer = true;
    }

    /**
     * 获得技能点数（战斗胜利）
     */
    public void addSkillPoint() {
        skillPoints += 3;
        System.out.println("🌟 获得3个技能点数！当前点数: " + skillPoints);
    }

    public void addSomeSkillPoint(int amount) {
        skillPoints += amount;
        System.out.println("🌟 获得" + amount + "个技能点数！当前点数: " + skillPoints);
    }

    /**
     * 消耗指定数量的技能点数
     */
    public boolean spendSkillPoints(int amount) {
        if (skillPoints < amount) {
            System.out.println("点数不足！需要 " + amount + " 点，当前只有 " + skillPoints + " 点。");
            return false;
        }
        skillPoints -= amount;
        return true;
    }

    /**
     * 升级属性（消耗1点）
     */
    public void upgradeStat(String stat) {
        switch (stat) {
            case "HP":
                maxHp += 15;
                hp += 15;
                System.out.println("生命上限 +15！当前 HP: " + hp + "/" + maxHp);
                break;
            case "MP":
                maxMp += 5;
                mp += 5;
                System.out.println("魔力上限 +5！当前 MP: " + mp + "/" + maxMp);
                break;
            case "ATK":
                atk += 3;
                System.out.println("攻击力 +3！当前 ATK: " + atk);
                break;
            case "DEF":
                def += 3;
                System.out.println("防御力 +3！当前 DEF: " + def);
                break;
            default:
                System.out.println("无效的属性！");
        }
    }

    /**
     * 添加技能（检查重复）
     */
    public boolean addSkill(Skill skill) {
        if (skills.contains(skill)) {
            System.out.println("你已经拥有该技能了！");
            return false;
        }
        skills.add(skill);
        System.out.println("  学会了新技能：" + skill.name + "！");
        return true;
    }

    /**
     * 出售技能（移除技能并返还1点，加入soldSkills防止商店再出现）
     */
    public boolean removeSkill(Skill skill) {
        if (skills.remove(skill)) {
            soldSkills.add(skill);
            skillPoints++;
            System.out.println("  出售了技能：「" + skill.name + "」，获得1个技能点。");
            return true;
        }
        return false;
    }

    /**
     * 战斗场次+1
     */
    public void incrementBattleCount() {
        battleCount++;
    }

    /**
     * 战斗后恢复（恢复部分HP和全部MP）
     */
    public void recoverAfterBattle() {
        clearBuffs();  // 清除所有buff
        // 清除临时技能
        for (int i = skills.size() - 1; i >= 0; i--) {
            if (skills.get(i).isTemporary) {
                skills.remove(i);
            }
        }
        int recoverHp = maxHp / 3;
        hp += recoverHp;
        if (hp > maxHp) hp = maxHp;
        mp = maxMp;
        System.out.println("战后恢复：HP恢复 " + recoverHp + " 点，MP完全恢复。");
    }

    /**
     * 显示完整状态
     */
    public void showStatus() {
        System.out.println("\n═══════════════════════════════");
        System.out.println("  " + firstName + " " + lastName + "  场次:" + battleCount + "  点数:" + skillPoints);
        System.out.println("  HP: " + hp + "/" + maxHp + "  MP: " + mp + "/" + maxMp);
        System.out.println("  ATK: " + atk + "  DEF: " + def);
        System.out.print("  技能: ");
        if (skills.isEmpty()) {
            System.out.println("无");
        } else {
            System.out.println();
            for (int i = 0; i < skills.size(); i += 2) {
                System.out.print("    " + skills.get(i).toBriefString());
                if (i + 1 < skills.size()) {
                    System.out.print("\t\t" + skills.get(i + 1).toBriefString());
                }
                System.out.println();
            }
        }
        System.out.println("═══════════════════════════════");
    }

}

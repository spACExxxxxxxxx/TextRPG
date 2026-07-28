package com.RPGTest.domain.Bosses;

import com.RPGTest.domain.*;
import com.RPGTest.domain.Skills.boss.*;
import java.util.Random;

public class Boss07 extends Boss {
    private int scriptStep = 0;
    private final Random rng = new Random();

    public Boss07() {
        super("", "终焉之龙", 19999, 1999, 199, 199);
        this.weak = ElementType.getWeakness(ElementType.NONE);
        this.isFinalBoss = true;

        skills.add(new FinalJudgement());   // [0] 终焉审判
        skills.add(new FinalBreath());      // [1] 灭世龙息
        skills.add(new FinalAbyss());       // [2] 绝望之渊
    }

    @Override
    public boolean hasScript() { return true; }

    @Override
    public Skill getScriptedSkill(com.RPGTest.domain.Character target) {
        // 首次必定 debuff
        if (scriptStep == 0) {
            scriptStep++;
            return skills.get(2);  // 绝望之渊
        }

        int phase = (scriptStep - 1) % 5;
        scriptStep++;

        switch (phase) {
            case 0 -> {
                // 普攻 or 龙息
                scriptedSkip = false;
                if (rng.nextBoolean()) {
                    System.out.println(lastName + " 挥动龙爪！");
                    return null;  // → BattleService 走 normalAttack
                }
                return skills.get(1);  // 灭世龙息
            }
            case 1 -> {
                // 玩家无 debuff → 上 debuff；有 debuff → 龙息
                scriptedSkip = false;
                boolean playerDebuffed = false;
                for (Buff b : target.buffs) {
                    if (b.type == BuffType.ATK_DOWN || b.type == BuffType.DEF_DOWN) {
                        playerDebuffed = true; break;
                    }
                }
                return playerDebuffed ? skills.get(1) : skills.get(2);
            }
            case 2 -> {
                // 必定龙息
                scriptedSkip = false;
                return skills.get(1);
            }
            case 3 -> {
                // 预告
                scriptedSkip = true;
                System.out.println("「终焉之龙」仰天长啸，星球的魔力全部向头顶的天空聚集");
                System.out.println("--下一击将避无可避！！");
                return null;
            }
            case 4 -> {
                // 审判
                return skills.get(0);
            }
        }
        return null;
    }

    @Override
    public void takePhysicalDamage(int damage) {
        int hpBefore = this.hp;
        super.takePhysicalDamage(damage);
        damageDealt += (hpBefore - this.hp);
    }

    @Override
    public void takeMagicalDamage(int damage) {
        int hpBefore = this.hp;
        super.takeMagicalDamage(damage);
        damageDealt += (hpBefore - this.hp);
    }
}

package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class FinalAbyss extends Skill {
    public FinalAbyss() {
        super("绝望之渊", 0, 0, ElementType.NONE, 0);
        this.description = "降低目标30%攻击和防御，持续9回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.applyBuff(BuffType.ATK_DOWN, 30, 9);
        target.applyBuff(BuffType.DEF_DOWN, 30, 9);
    }
}

package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class CurseOfLife extends Skill {
    public CurseOfLife() {
        super("生命诅咒", 0, 30, ElementType.DARK, 3);
        this.description = "减少目标30%最大HP，整场战斗有效";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.applyBuff(BuffType.MAXHP_DOWN, 20, -1);
    }
}

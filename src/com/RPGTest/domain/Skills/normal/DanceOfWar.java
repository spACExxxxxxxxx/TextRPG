package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class DanceOfWar extends Skill {
    public DanceOfWar() {
        super("战舞", 0, 25, ElementType.NONE, 3);
        this.isBuff = true;
        this.description = "自身攻击力上升30%，持续5回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        owner.applyBuff(BuffType.ATK_UP, 30, 5);
    }
}

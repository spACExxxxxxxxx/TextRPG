package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class IceLord extends Skill {
    public IceLord() {
        super("苍之冕", 0, 100, ElementType.ICE, 0);
        this.description = "提高自身全属性30%*5回合";
    }

    @Override
    public void use(com.RPGTest.domain.Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.firstName + " 释放了 " + this.name + "！");
        owner.applyBuff(BuffType.DEF_UP, 30, 5);
        owner.applyBuff(BuffType.ATK_UP, 30, 5);
        owner.applyBuff(BuffType.MAXHP_UP, 30, 5);
        owner.applyBuff(BuffType.MAXMP_UP, 30, 5);
    }
}

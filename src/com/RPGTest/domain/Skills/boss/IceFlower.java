package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class IceFlower extends Skill {
    public IceFlower() {
        super("冰天百华葬", 0, 70, ElementType.NONE, 0);
        this.description = "降低目标全属性30%*5回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.firstName + " 释放了 " + this.name + "！");
        target.applyBuff(BuffType.DEF_DOWN, 30, 5);
        target.applyBuff(BuffType.ATK_DOWN, 30, 5);
        target.applyBuff(BuffType.MAXHP_DOWN, 30, 5);
        target.applyBuff(BuffType.MAXMP_DOWN, 30, 5);
    }
}

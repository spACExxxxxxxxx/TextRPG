package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class HolyBlessing extends Skill {
    public HolyBlessing() {
        super("光天颂圣歌", 0, 40, ElementType.NONE, 0);
        this.description = "基于mp*20%提高自身防御力&攻击力*5回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.firstName + " 释放了 " + this.name + "！");
        int percent = (int) (owner.mp * 0.2);
        owner.applyBuff(BuffType.DEF_UP, percent, 5);
        owner.applyBuff(BuffType.ATK_UP, percent, 5);
    }
}

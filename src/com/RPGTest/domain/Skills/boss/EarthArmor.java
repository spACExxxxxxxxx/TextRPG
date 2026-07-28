package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class EarthArmor extends Skill {
    public EarthArmor() {
        super("顽石金刚", 0, 30, ElementType.NONE, 0);
        this.description = "自身防御力上升40%，持续5回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        owner.applyBuff(BuffType.DEF_UP, 40, 5);
    }
}

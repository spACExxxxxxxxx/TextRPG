package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class IronField extends Skill {
    public IronField() {
        super("绝对圣域", 0, 60, ElementType.NONE, 4);
        this.isBuff = true;
        this.description = "自身防御力上升60%，持续2回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        owner.applyBuff(BuffType.DEF_UP, 60, 2);
    }
}
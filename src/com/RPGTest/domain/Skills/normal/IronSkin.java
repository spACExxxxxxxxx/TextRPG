package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class IronSkin extends Skill {
    public IronSkin() {
        super("铁壁盾防", 0, 30, ElementType.NONE, 3);
        this.isBuff = true;
        this.description = "自身防御力上升20%，持续4回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        owner.applyBuff(BuffType.DEF_UP, 20, 4);
    }
}

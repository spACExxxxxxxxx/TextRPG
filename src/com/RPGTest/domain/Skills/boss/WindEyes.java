package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class WindEyes extends Skill {
    public WindEyes() {
        super("飓风眼", 0, 40, ElementType.NONE, 0);
        this.description = "自身攻击力上升40%，持续4回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.firstName + " 释放了 " + this.name + "！");
        owner.applyBuff(BuffType.ATK_UP, 40, 4);
    }
}

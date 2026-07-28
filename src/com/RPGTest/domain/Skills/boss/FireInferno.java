package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class FireInferno extends Skill {
    public FireInferno() {
        super("炼狱", 0, 0, ElementType.NONE, 0);
        this.description = "消耗40%Hp，提升攻击力60%*5回合";
    }

    @Override
    public void use(com.RPGTest.domain.Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.firstName + " 释放了 " + this.name + "！");
        owner.applyBuff(BuffType.ATK_UP, 60, 5);
        owner.takeMagicalDamage((int) (owner.hp * 0.4));
    }
}

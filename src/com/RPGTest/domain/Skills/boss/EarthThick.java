package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class EarthThick extends Skill {
    public EarthThick() {
        super("泼沙", 0, 50, ElementType.EARTH, 0);
        this.description = "降低目标攻击力40%*2回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.applyBuff(BuffType.ATK_DOWN, 40, 2);
    }
}

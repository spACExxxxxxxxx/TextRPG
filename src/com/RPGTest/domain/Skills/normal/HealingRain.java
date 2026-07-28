package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class HealingRain extends Skill {
    public HealingRain() {
        super("天降甘露", 0, 60, ElementType.HEAL,4);
        this.description = "恢复1/3生命值";
    }

    @Override
    public void healSkill(Character owner) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！恢复了 " + owner.maxHp/3 + " 点HP");
        owner.heal(owner.maxHp/3);
    }
}

package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class FinalBreath extends Skill {
    public FinalBreath() {
        super("灭世龙息", 2, 50, ElementType.DARK, 0);
        this.description = "造成2*攻击力的贯穿伤害";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        int damage = (int) (this.power * owner.atk);
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damage);
    }
}

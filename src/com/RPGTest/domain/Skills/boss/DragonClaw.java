package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class DragonClaw extends Skill {
    public DragonClaw() {
        super("裂空龙爪", 1.8, 20, ElementType.NONE, 0);
        this.isAOE = true;
        this.description = "造成1.8*攻击力的对群伤害";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        int damage = (int) (this.power * owner.atk);
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damage);
    }
}

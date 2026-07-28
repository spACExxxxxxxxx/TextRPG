package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class BloodAbsorb extends Skill {
    public BloodAbsorb() {
        super("鲜血征收", 1.5, 40, ElementType.NONE,3);
        this.description = "造成1.5*攻击力的伤害，自身回复造成伤害的一半";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int baseDamage = (int) (this.power * owner.atk);
        int damageToTarget = baseDamage;
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damageToTarget);

        System.out.println("吸收了" + damageToTarget/2 + "点生命");
        owner.heal(damageToTarget/2);
    }
}
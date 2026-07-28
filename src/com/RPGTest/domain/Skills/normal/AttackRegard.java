package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class AttackRegard extends Skill {
    public AttackRegard() {
        super("盾击", 0, 20, ElementType.NONE,2);
        this.description = "造成2*防御力的伤害，自身进入防御状态";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int baseDamage = owner.def * 2;
        int damageToTarget = baseDamage;
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        System.out.println(owner.lastName +"进入了防御状态");
        target.takePhysicalDamage(damageToTarget);
        owner.inDefense = true;

    }
}

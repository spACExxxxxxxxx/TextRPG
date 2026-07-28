package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class FireFlame extends Skill {
    public FireFlame() {
        super("耀斑", 5, 100, ElementType.FIRE, 0);
        this.isSpecial = true;
        this.description = "造成基于减少的hp比例的0~5*攻击力的对群伤害，并回复造成伤害的一半数值的hp";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        int damage = (int) (this.power *owner.atk * (1-(double)owner.hp/owner.maxHp));
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damage *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damage);
        owner.heal(damage/2);
    }
}

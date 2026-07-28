package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class IceStorm extends Skill {
    public IceStorm() {
        super("冰殛暴风雪", 0.8, 50, ElementType.ICE, 0);
        this.description = "造成0.8*攻击力的伤害5次";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.firstName + " 释放了 " + this.name + "！");
        for (int i = 0; i < 5; i++) {
            int damage = (int) (this.power * owner.atk);
            if (this.element == target.weak && this.element != ElementType.NONE) {
                System.out.println("💥 元素克制！伤害翻倍！");
                damage *= 2;
            }
            target.takePhysicalDamage(damage);
        }
    }
}

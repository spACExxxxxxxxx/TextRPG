package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class DarkWave extends Skill {
    public DarkWave() {
        super("黑暗波动", 2, 20, ElementType.DARK,3);
        this.description = "造成2*攻击力的伤害，自身反噬一半伤害";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
                System.out.println("释放失败，MP不足！");
                return;
        }

        int baseDamage = (int) (this.power * owner.atk);
        int damageToTarget = baseDamage;
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damageToTarget);

        // 对自身造成一半伤害
        int damageToSelf = baseDamage / 2;
        System.out.println("黑暗波动反噬自身，对 " + owner.firstName + owner.lastName + " 造成了 " + damageToSelf + " 点伤害");
        owner.takePhysicalDamage(damageToSelf);
    }
}

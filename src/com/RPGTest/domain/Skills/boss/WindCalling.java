package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.*;
import com.RPGTest.domain.Character;


public class WindCalling extends Skill {
    public WindCalling() {
        super("祭风", 1.3, 0, ElementType.WIND, 0);
        this.description = "造成1.3*攻击力的对群伤害，降低目标群体攻击力10%*3回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int baseDamage = (int) (owner.atk * this.power);
        if (baseDamage < 0) baseDamage = 0;
        int damageToTarget = baseDamage;

        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damageToTarget);

        target.applyBuff(BuffType.ATK_DOWN, 10, 3);
    }
}

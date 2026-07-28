package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.ElementType;

public class EarthBreath extends Skill {
    public EarthBreath() {
        super("盖亚之息", 1.6, 50, ElementType.EARTH,4);
        this.description = "造成1.6*攻击力的对群伤害，自身进入防御状态，并减少目标20%攻击*3回合";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target) {
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

        target.applyBuff(BuffType.ATK_DOWN, 20, 3);
        owner.inDefense = true;

    }
}

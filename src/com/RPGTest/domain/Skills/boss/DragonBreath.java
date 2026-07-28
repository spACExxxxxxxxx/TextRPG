package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class DragonBreath extends Skill {
    public DragonBreath() {
        super("暗影吐息", 1.5, 30, ElementType.DARK, 0);
        this.isAOE = true;
        this.description = "造成1.5*攻击力的对群穿透伤害";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int baseDamage = (int)(owner.atk * this.power);
        if (baseDamage < 0) baseDamage = 0;
        int damageToTarget = baseDamage;

        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damageToTarget);
    }
}

package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class WindDance extends Skill {
    public WindDance() {
        super("天风魔舞", 2, 40, ElementType.WIND, 0);
        this.description = "造成2*mp/maxMp*攻击力的对群穿透伤害，并回复伤害数值10%的mp";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int baseDamage = (int)(owner.atk * this.power * (double)owner.mp / owner.maxMp);
        if (baseDamage < 0) baseDamage = 0;
        int damageToTarget = baseDamage;

        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damageToTarget);

        owner.mp += (int)(damageToTarget * 0.1);
        if (owner.mp > owner.maxMp) owner.mp = owner.maxMp;
    }
}

package com.RPGTest.domain.Skills.special;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class Excalibur extends Skill {
    public Excalibur() {
        super("咖喱棒", 0, 0, ElementType.LIGHT, true);
        this.description = "造成mp/20*攻击力的伤害，消耗全部mp，";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        int baseDamage = owner.atk * owner.mp/20;
        if (baseDamage < 0) {
            baseDamage = 0;
        }
        int damageToTarget = baseDamage;
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damageToTarget);
        owner.mp = 0;
    }
}
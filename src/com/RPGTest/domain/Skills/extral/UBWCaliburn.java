package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;

public class UBWCaliburn extends TemporarySkill {
    public UBWCaliburn() {
        super("永久遥远的黄金剑(伪)", 0, 30, ElementType.LIGHT);
        this.description = "造成基于减少的mp比例的1.5~2.5*攻击力的伤害";
    }

    @Override
    protected void execute(com.RPGTest.domain.Character owner, Character target) {
        int damage = (int)((2.5 - (double) owner.mp/owner.maxMp)*owner.atk);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damage *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damage);
    }
}

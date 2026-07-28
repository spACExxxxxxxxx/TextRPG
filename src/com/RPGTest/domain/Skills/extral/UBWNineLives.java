package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;

public class UBWNineLives extends TemporarySkill {
    public UBWNineLives() {
        super("射杀百头(伪)", 1.8, 20, ElementType.EARTH);
        this.description = "造成1.8攻击力的伤害，并提升自身攻击力30%*3回合";
    }

    @Override
    protected void execute(com.RPGTest.domain.Character owner, Character target) {
        int damage = (int) (this.power * owner.atk);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damage *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damage);
        owner.applyBuff(BuffType.ATK_UP, 30, 3);
    }
}

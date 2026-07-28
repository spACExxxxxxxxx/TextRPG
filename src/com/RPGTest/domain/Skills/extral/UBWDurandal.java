package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;

public class UBWDurandal extends TemporarySkill {
    public UBWDurandal() {
        super("杜兰达尔(伪)", 1.4, 15, ElementType.ICE);
        this.isAOE = true;
        this.description = "造成1.4*攻击力的对群伤害，并提升自身防御力50%*3回合";
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
        owner.applyBuff(BuffType.DEF_UP, 50, 3);
    }
}
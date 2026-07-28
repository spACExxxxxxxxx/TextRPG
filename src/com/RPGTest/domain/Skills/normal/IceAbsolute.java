package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class IceAbsolute extends Skill {
    public IceAbsolute() {
        super("绝对零度", 2.5, 50, ElementType.ICE,4);
        this.description = "造成2.5*当前mp的穿透伤害，并降低敌人防御力20%持续3回合";
    }
    @Override
    public void damageSkill(com.RPGTest.domain.Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int damage = (int)(this.power * owner.mp);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damage *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damage);
        target.applyBuff(BuffType.DEF_DOWN, 20, 3);
    }
}

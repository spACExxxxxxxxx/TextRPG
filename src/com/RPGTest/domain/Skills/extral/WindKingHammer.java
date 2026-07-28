package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;

public class WindKingHammer extends TemporarySkill {
    public WindKingHammer() {
        super("风王铁锤", 1.8, 25, ElementType.WIND);
        this.isAOE = true;
        this.description = "造成1.8*攻击力的对群伤害";
    }

    @Override
    protected void execute(Character owner, Character target) {
        int damage = (int) (this.power * owner.atk);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damage *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damage);
    }
}

package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class DarkSilence extends Skill {
    public DarkSilence() {
        super("沉默禁令", 0, 70, ElementType.DARK,4);
        this.description = "损耗目标群体低于自己mp数值的mp，并造成其2倍数值的的对群穿透伤害";
        isAOE = true;
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int mpDamage = owner.mp > target.mp ? owner.mp - target.mp : 0;
        int hpDamage = mpDamage * 2;
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            hpDamage *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        System.out.println("损耗了 " + target.firstName + target.lastName + "的" + mpDamage + " 点MP");
        target.takeMagicalDamage(hpDamage);
        target.mp -= mpDamage;
    }
}
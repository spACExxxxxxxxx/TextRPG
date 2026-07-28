package com.RPGTest.domain.Skills.special;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;


public class ArgonCoin extends Skill {
    public ArgonCoin() {
        super("幻兽之龙炎", 3, 60, ElementType.DARK, true);
        this.isAOE = true;
        this.description = "造成3*当前mp/100*攻击力的对群穿透伤害，并降低目标mp30%*3回合";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int damageToTarget = (int) (owner.mp * this.power *owner.atk /100);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damageToTarget);
        target.applyBuff(BuffType.MAXMP_DOWN, 30, 3);
    }
}

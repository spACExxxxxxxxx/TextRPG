package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;

public class UBWCaladbolg extends TemporarySkill {
    public UBWCaladbolg() {
        super("螺旋剑(伪)", 2, 30, ElementType.FIRE);
        this.description = "造成2*攻击力的贯穿伤害";
    }
    @Override
    public void execute(com.RPGTest.domain.Character owner, Character target){
        int damageToTarget = (int)(this.power * owner.atk);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damageToTarget);
    }
}
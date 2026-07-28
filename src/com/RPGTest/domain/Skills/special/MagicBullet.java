package com.RPGTest.domain.Skills.special;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class MagicBullet extends Skill {
    public MagicBullet() {
        super("魔弹", 1.2, 5, ElementType.DARK, true);
        this.description = "造成1.2攻击力的穿透伤害";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int damageToTarget = (int)(this.power * owner.atk);
        if (damageToTarget < 0) {
            damageToTarget = 0;
        }
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damageToTarget);
    }
}
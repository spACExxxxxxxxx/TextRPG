package com.RPGTest.domain.Skills.special;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skills.extral.TemporarySkill;
import com.RPGTest.domain.Skills.extral.WindKingHammer;

public class WindField extends Skill {
    public WindField() {
        super("风王结界", 1.2, 15, ElementType.WIND, true);
        this.description = "造成1.2*攻击力的伤害，进入防御状态，并解锁临时技能";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int baseDamage = (int)(this.power * owner.atk);
        if (baseDamage < 0) {
            baseDamage = 0;
        }
        int damageToTarget = baseDamage;
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }
        owner.inDefense = true;

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damageToTarget);

        // 获得一次性技能「风王铁锤」
        TemporarySkill.grant(owner, new WindKingHammer());
    }
}
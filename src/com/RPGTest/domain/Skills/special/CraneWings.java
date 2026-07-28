package com.RPGTest.domain.Skills.special;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class CraneWings extends Skill {
    public CraneWings() {
        super("鹤翼三连", 3, 30, ElementType.NONE, true);
        this.description = "造成基于减少的hp比例的0~2*攻击力的伤害3次";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        if(target.inDefense == true){
            System.out.println(target.firstName + target.lastName + " 的防御姿态被打破了！");
            target.inDefense = false;
            target.takePhysicalDamage(owner.atk);
        }
        int baseDamage = (int) (this.power * owner.atk * (1 - (double) owner.hp / owner.maxHp));
        int damageToTarget = 0;
        for (int i = 0; i < 3; i++) {
            System.out.println(owner.lastName + " 释放了 " + this.name + "！");
            damageToTarget += baseDamage;
        }
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }

        target.takeMagicalDamage(damageToTarget);
    }
}

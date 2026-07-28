package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;

public class UBWCraneWings extends TemporarySkill {
    public UBWCraneWings() {
        super("鹤翼三连(伪)", 2, 10, ElementType.WIND);
        this.description = "造成基于减少的hp比例的0~2*攻击力的伤害2次";
    }
    @Override
    public void execute(Character owner, Character target){
        if(target.inDefense){
            System.out.println(target.firstName + target.lastName + " 的防御姿态被打破了！");
            target.inDefense = false;
            target.takePhysicalDamage(owner.atk);
        }
        int baseDamage = (int) (this.power * owner.atk * (1-(double)owner.hp/owner.maxHp)-target.def);
        int damageToTarget = 0;
        for (int i = 0; i < 2; i++) {
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

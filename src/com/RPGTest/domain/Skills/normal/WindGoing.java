package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class WindGoing extends Skill {
    public WindGoing() {
        super("天狗风", 4, 50, ElementType.WIND,4);
        this.description = "造成1*攻击力的伤害4次，降低目标防御力&攻击力10%*2回合";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        if(target.inDefense){
            System.out.println(target.firstName + target.lastName + " 的防御姿态被打破了！");
            target.inDefense = false;
            target.takePhysicalDamage(owner.atk);
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        int totalDamage = 0;
        for (int i = 0; i < 4; i++) {
            int hit = owner.atk;
            if (this.element == target.weak && this.element != ElementType.NONE) {
                hit *= 2;
            }
            totalDamage += hit;
        }
        target.takePhysicalDamage(totalDamage);
        target.applyBuff(BuffType.DEF_DOWN, 10, 2);
        target.applyBuff(BuffType.ATK_DOWN, 10, 2);

    }
}

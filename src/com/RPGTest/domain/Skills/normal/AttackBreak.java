package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.ElementType;

public class AttackBreak extends Skill {
    public AttackBreak() {
        super("破阵击", 0, 10, ElementType.NONE,2);
        this.description = "支付30点生命，造成2*攻击力的伤害，减少目标40%防御";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (owner.hp < 30) {
            System.out.println("释放失败，HP不足！");
            return;
        }
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }


        if(target.inDefense == true){
            System.out.println(target.firstName + target.lastName + " 的防御姿态被打破了！");
            target.inDefense = false;
            target.takePhysicalDamage(owner.atk);
        }
        int baseDamage = 2* owner.atk;
        if (baseDamage < 0) {
            baseDamage = 0;
        }
        int damageToTarget = baseDamage;
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        System.out.println(target.firstName + target.lastName + "的防御降低了。");
        target.takePhysicalDamage(damageToTarget);
        owner.hp -= 30;
        target.applyBuff(BuffType.DEF_DOWN, 40, 3);
    }
}

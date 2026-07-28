package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class AttackDual extends Skill {
    public AttackDual() {
        super("二连击", 0, 0, ElementType.NONE,2);
        this.description = "支付10点生命，造成1*攻击力的伤害2次";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (owner.hp < 10) {
            System.out.println("释放失败，HP不足！");
            return;
        }

        int damage = 2 * owner.atk;
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damage);
        owner.hp -= 10;
    }
}

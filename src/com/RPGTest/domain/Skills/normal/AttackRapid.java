package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class AttackRapid extends Skill {
    public AttackRapid() {
        super("拔刀斩", 3, 20, ElementType.NONE,3);
        this.description = "造成3*mp/maxMp*攻击力的伤害，自己的防御力下降40%*2回合";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int baseDamage = (int)(owner.atk * this.power * owner.mp/owner.maxMp);
        if (baseDamage < 0) baseDamage = 0;
        int damageToTarget = baseDamage;
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damageToTarget);

        owner.applyBuff(BuffType.DEF_DOWN, 40, 2);

    }
}

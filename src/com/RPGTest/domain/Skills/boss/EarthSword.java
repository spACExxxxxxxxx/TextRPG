package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class EarthSword extends Skill {
    public EarthSword() {
        super("断钢", 3, 50, ElementType.NONE, 0);
        this.description = "造成3*攻击力的穿透伤害，降低自身防御力50%*2回合";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        if(target.inDefense){
            System.out.println("弹刀了！");
            System.out.println(target.lastName + " 的架势被破坏了！");
            owner.applyBuff(BuffType.DEF_DOWN, 100, 5);
            owner.applyBuff(BuffType.ATK_DOWN, 50, 5);
        }

        int baseDamage = (int)(owner.atk * this.power);
        if (baseDamage < 0) baseDamage = 0;
        int damageToTarget = baseDamage;

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damageToTarget);
        owner.applyBuff(BuffType.DEF_DOWN, 50, 2);
        if(owner.lastName.equals("飞沙之剑圣")){
            owner.applyBuff(BuffType.ATK_DOWN, 40, 3);
        }

    }
}

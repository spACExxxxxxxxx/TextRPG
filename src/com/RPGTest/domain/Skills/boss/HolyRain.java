package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class HolyRain extends Skill {
    public HolyRain() {
        super("净世雨", 1, 30, ElementType.HEAL, 0);
        this.description = "回复自身1*攻击力的hp，并驱除所有负面效果";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        int damage = (int) (this.power * owner.atk);
        System.out.println(owner.firstName + " 释放了 " + this.name + "！");
        owner.heal(damage);
        owner.applyBuff(BuffType.ATK_DOWN, 0, 1);
        owner.applyBuff(BuffType.DEF_DOWN, 0, 1);
        owner.applyBuff(BuffType.MAXMP_DOWN, 0, 1);
        owner.applyBuff(BuffType.MAXHP_DOWN, 0, 1);
    }
}

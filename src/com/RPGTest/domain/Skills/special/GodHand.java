package com.RPGTest.domain.Skills.special;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class GodHand extends Skill {
    public GodHand() {
        super("十二试炼", 0, 30, ElementType.HEAL, true);
        this.description = "消耗所有mp，恢复一半hp，并提升自身防御&攻击30%*2回合，";
    }
    @Override
    public void healSkill(Character owner) {
        System.out.println(owner.lastName + " 释放了 " + this.name + "！恢复了 " + owner.maxHp/2 + " 点HP");
        owner.heal(owner.maxHp/2);
        owner.mp = 0;
        owner.applyBuff(BuffType.DEF_UP, 30, 2);
        owner.applyBuff(BuffType.ATK_UP, 30, 2);
    }
}
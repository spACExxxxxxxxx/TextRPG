package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class Caladbolg extends Skill {
    public Caladbolg() {
        super("螺旋剑", 0, 80, ElementType.NONE, true);
        this.description = "造成目标当前hp的一半+1*攻击力的对群穿透伤害";
        this.isAOE = true;
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int damageToTarget = target.hp/2 + owner.atk;
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damageToTarget);
    }
}
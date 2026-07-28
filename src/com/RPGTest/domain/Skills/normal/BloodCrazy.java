package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class BloodCrazy extends Skill {
    public BloodCrazy() {
        super("沸血癫狂", 0, 50, ElementType.NONE,4);
        this.isBuff = true;
        this.description = "本场战斗提升攻击力80%，降低防御力100%";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }


        owner.applyBuff(BuffType.ATK_UP, 80, -1);
        owner.applyBuff(BuffType.DEF_DOWN, 100, -1);
    }
}
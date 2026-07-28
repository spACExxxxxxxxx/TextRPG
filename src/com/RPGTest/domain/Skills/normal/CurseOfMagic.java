package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class CurseOfMagic extends Skill {
    public CurseOfMagic() {
        super("魔力清洗", 0, 20, ElementType.LIGHT, 1);
        this.description = "减少目标30%最大MP，整场战斗有效";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.applyBuff(BuffType.MAXMP_DOWN, 30, -1);
    }
}
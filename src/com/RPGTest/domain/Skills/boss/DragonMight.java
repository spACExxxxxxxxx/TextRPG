package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class DragonMight extends Skill {
    public DragonMight() {
        super("龙威", 0, 30, ElementType.NONE, 0);
        this.isSpecial = true;
        this.description = "降低目标群体攻击力&防御力20%*4回合";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.applyBuff(BuffType.ATK_DOWN, 20, 4);
        target.applyBuff(BuffType.DEF_DOWN, 20, 4);
    }
}

package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class FinalJudgement extends Skill {
    public FinalJudgement() {
        super("终焉审判", 99, 200, ElementType.DARK, 0);
        this.isAOE = true;
        this.description = "造成目标一半生命+1*攻击力的对群穿透伤害";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        int damage = 0;
        if(target.inDefense) damage = (owner.atk + target.maxHp/2);
        else damage = (int) (this.power * owner.atk);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damage *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damage);
    }
}
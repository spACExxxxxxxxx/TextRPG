package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

import java.util.Random;

public class LightingDance extends Skill {
    public LightingDance() {
        super("煌雷之天闪", 4, 100, ElementType.LIGHT, 4);
        this.isAOE = true;
        this.description = "造成0.8~4*攻击力的对群穿透伤害";
    }
    Random random = new Random();
    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        int baseDamage = (int) (this.power * owner.atk * random.nextInt(20, 100) / 100);
        int damageToTarget = baseDamage;
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damageToTarget *= 2;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        target.takePhysicalDamage(damageToTarget);
    }
}
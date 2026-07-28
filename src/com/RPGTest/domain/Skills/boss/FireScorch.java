package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;
import java.util.Random;

public class FireScorch extends Skill {
    private final Random random = new Random();

    public FireScorch() {
        super("焚尽", 2.5, 60, ElementType.FIRE, 0);
        this.isAOE = true;
        this.description = "造成0.5~2.5*攻击力的随机倍率对群穿透伤害";
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        double rate = 0.5 + random.nextDouble() * 2.5;
        int damage = (int) (rate * owner.atk);
        if (this.element == target.weak && this.element != ElementType.NONE) {
            System.out.println("💥 元素克制！伤害翻倍！");
            damage *= 2;
        }
        System.out.println(owner.firstName + " 释放了 " + this.name + "！");
        target.takeMagicalDamage(damage);
    }
}

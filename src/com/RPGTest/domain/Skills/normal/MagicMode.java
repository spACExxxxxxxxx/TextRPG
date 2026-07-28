package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class MagicMode extends Skill {

    private int hpCost;   // 消耗的HP量
    private int mpGain;   // 回复的MP量

    public MagicMode() {
        super("祭品", 0, 0, ElementType.NONE,3);
        this.description = "消耗30+1/4maxHP，回复20+1/2maxMP";
    }

    @Override
    public void use(Character owner, Character target){
        hpCost = 30 + owner.maxHp/4;
        mpGain = 20 + owner.maxMp/2;
        if (owner.hp <= this.hpCost) {
            System.out.println(owner.lastName + " 的HP不足，无法释放 " + this.name + "！");
            return;
        }
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");

        owner.takePhysicalDamage(this.hpCost);
        System.out.println("🥀 消耗了 " + this.hpCost + " 点HP，剩余HP：" + owner.hp);

        owner.mp += this.mpGain;
        System.out.println("✨ 恢复了 " + this.mpGain + " 点MP，当前MP：" + owner.mp);
    }
}
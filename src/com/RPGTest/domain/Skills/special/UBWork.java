package com.RPGTest.domain.Skills.special;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skills.extral.*;


public class UBWork extends Skill {
    public UBWork() {
        super("无限剑制", 0, 60, ElementType.NONE, true);
        this.description = "解锁临时技能组";
    }
    @Override
    public void use(com.RPGTest.domain.Character owner, Character target){
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }

        System.out.println(owner.lastName + " 释放了 " + this.name + "！");

        // 获得一次性技能
        TemporarySkill.grant(owner, new UBWCaladbolg());
        TemporarySkill.grant(owner, new UBWCaliburn());
        TemporarySkill.grant(owner, new UBWDurandal());
        TemporarySkill.grant(owner, new UBWNineLives());
        TemporarySkill.grant(owner, new UBWCraneWings());
        TemporarySkill.grant(owner, new UBWRhoAias());

    }
}
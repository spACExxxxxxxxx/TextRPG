package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Character;
import com.RPGTest.domain.BuffType;

public class UBWRhoAias extends TemporarySkill{
    public UBWRhoAias() {
        super("炽天覆七重圆环", 0, 50, ElementType.NONE);
        this.description = "提高自身防御力100%*1回合";
    }
    @Override
    public void execute(com.RPGTest.domain.Character owner, Character target){
        System.out.println(owner.lastName + " 释放了 " + this.name + "！");
        owner.applyBuff(BuffType.DEF_UP, 100, 1);
    }
}

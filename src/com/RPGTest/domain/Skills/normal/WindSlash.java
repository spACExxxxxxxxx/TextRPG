package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class WindSlash extends Skill {
    public WindSlash() {
        super("风卷", 1.1, 10, ElementType.WIND,2);
        this.isAOE = true;
        this.description = "造成1.2*攻击力的对群伤害";
    }
}

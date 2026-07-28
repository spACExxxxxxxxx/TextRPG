package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class WindBlade extends Skill {
    public WindBlade() {
        super("风暴之刃", 1.7, 20, ElementType.WIND,3);
        this.description = "造成1.7*攻击力的伤害";
    }
}

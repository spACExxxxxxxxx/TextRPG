package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class LightBolt extends Skill {
    public LightBolt() {
        super("雷击", 1.2, 10, ElementType.LIGHT,2);
        this.description = "造成1.2*攻击力的伤害";
    }
}

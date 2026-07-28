package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class EarthSpike extends Skill {
    public EarthSpike() {
        super("地刺", 1.2, 10, ElementType.EARTH,2);
        this.description = "造成1.2*攻击力的伤害";
    }
}

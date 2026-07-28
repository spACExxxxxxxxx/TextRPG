package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class IceBlade extends Skill {
    public IceBlade() {
        super("冰枪", 1.4, 15, ElementType.ICE, 2);
        this.description = "造成1.4*攻击力的伤害";
    }
}

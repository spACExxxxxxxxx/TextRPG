package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class IceAge extends Skill {
    public IceAge() {
        super("冰河时代", 1.6, 30, ElementType.ICE, 3);
        this.isAOE = true;
        this.description = "造成1.6*攻击力的对群伤害";
    }

}

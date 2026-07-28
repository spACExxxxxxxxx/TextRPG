package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class EarthQuake extends Skill {
    public EarthQuake() {
        super("地震", 1.4, 25, ElementType.EARTH,3);
        this.isAOE = true;
        this.description = "造成1.4*攻击力的对群伤害";
    }
}

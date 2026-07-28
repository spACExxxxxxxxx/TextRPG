package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class FireStorm extends Skill {
    public FireStorm() {
        super("烈焰风暴", 1.6, 30, ElementType.FIRE,3);
        this.isAOE = true;
        this.description = "造成1.6*攻击力的对群伤害";
    }
}

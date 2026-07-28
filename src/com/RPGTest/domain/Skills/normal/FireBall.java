package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class FireBall extends Skill {
    public FireBall() {
        super("火球术", 1.3, 10, ElementType.FIRE,2);
        this.description = "造成1.3*攻击力的伤害";
    }
}

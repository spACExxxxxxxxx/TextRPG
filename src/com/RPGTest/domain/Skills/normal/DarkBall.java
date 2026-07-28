package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class DarkBall extends Skill {
    public DarkBall() {
        super("暗球", 1.2, 10, ElementType.DARK,2);
        this.description = "造成1.2*攻击力的伤害";
    }
}

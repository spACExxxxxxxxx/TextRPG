package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class FirePhoenix extends Skill {
    public FirePhoenix() {
        super("真凰炎", 2.7, 60, ElementType.FIRE,4);
        this.description = "造成2.7*攻击力的伤害";
    }
}

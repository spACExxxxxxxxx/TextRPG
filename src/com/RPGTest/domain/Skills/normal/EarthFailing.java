package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class EarthFailing extends Skill {
    public EarthFailing() {
        super("天陨", 3, 70, ElementType.EARTH,4);
        this.description = "造成3*攻击力的伤害";
    }
}
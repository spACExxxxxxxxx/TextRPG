package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class Lighting extends Skill {
    public Lighting() {
        super("霆光闪", 2.5, 40, ElementType.LIGHT,3);
        this.description = "造成2.5*攻击力的伤害";
    }
}

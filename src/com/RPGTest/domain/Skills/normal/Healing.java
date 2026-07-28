package com.RPGTest.domain.Skills.normal;

import com.RPGTest.domain.Skill;
import com.RPGTest.domain.ElementType;

public class Healing extends Skill {
    public Healing() {
        super("治愈术", 50, 20, ElementType.HEAL,2);
        this.description = "恢复50生命值";
    }
}

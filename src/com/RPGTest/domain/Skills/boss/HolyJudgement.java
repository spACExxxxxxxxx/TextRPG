package com.RPGTest.domain.Skills.boss;

import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;

public class HolyJudgement extends Skill {
    public HolyJudgement() {
        super("光剑裁决", 1.6, 30, ElementType.LIGHT, 0);
        this.isAOE = true;
        this.description = "造成1.6*攻击力的对群伤害";
    }
}

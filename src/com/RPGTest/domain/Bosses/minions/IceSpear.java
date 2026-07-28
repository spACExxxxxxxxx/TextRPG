package com.RPGTest.domain.Bosses.minions;

import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Enemy;
import com.RPGTest.domain.Skills.boss.WindDance;
import com.RPGTest.domain.Skills.normal.IceAbsolute;
import com.RPGTest.service.SkillPool;

public class IceSpear extends Enemy {
    public IceSpear() {
        this.firstName = "玄冰";
        this.lastName = "侍从";
        this.isPlayer = false;
        this.hp = 300;
        this.maxHp = this.hp;
        this.mp = 110;
        this.maxMp = this.mp;
        this.atk = 110;
        this.def = 60;
        this.weak = ElementType.getWeakness(ElementType.ICE);
        this.skills.add(new IceAbsolute());
    }
}

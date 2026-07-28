package com.RPGTest.domain.Bosses.minions;

import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Enemy;
import com.RPGTest.domain.Skills.boss.WindDance;
import com.RPGTest.service.SkillPool;

public class WindMonster extends Enemy {
    public WindMonster() {
        this.firstName = "有翼";
        this.lastName = "异形体";
        this.isPlayer = false;
        this.hp = 200;
        this.maxHp = this.hp;
        this.mp = 60;
        this.maxMp = this.mp;
        this.atk = 60;
        this.def = 0;
        this.weak = ElementType.getWeakness(ElementType.WIND);
        this.skills.add(new WindDance());
    }
}

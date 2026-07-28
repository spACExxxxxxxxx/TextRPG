package com.RPGTest.domain.Bosses.minions;

import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Enemy;
import com.RPGTest.domain.Skills.boss.WindCalling;

public class WindTemple extends Enemy {
    public WindTemple() {
        this.firstName = "风暴尽头的";
        this.lastName = "神殿";
        this.isPlayer = false;
        this.hp = 750;
        this.maxHp = this.hp;
        this.mp = 1;
        this.maxMp = this.mp;
        this.atk = 50;
        this.def = 120;
        this.weak = ElementType.getWeakness(ElementType.WIND);
        this.skills.add (new WindCalling());
    }
}

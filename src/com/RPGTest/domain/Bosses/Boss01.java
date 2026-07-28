package com.RPGTest.domain.Bosses;

import com.RPGTest.domain.*;
import com.RPGTest.domain.Skills.boss.*;

public class Boss01 extends Boss {
    public Boss01() {
        super("","暗影龙", 500, 120, 55, 30);
        this.weak = ElementType.getWeakness(ElementType.DARK);

        skills.add(new DragonBreath());
        skills.add(new DragonClaw());
        skills.add(new DragonMight());
    }
}

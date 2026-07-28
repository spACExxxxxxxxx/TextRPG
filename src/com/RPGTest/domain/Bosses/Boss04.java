package com.RPGTest.domain.Bosses;

import com.RPGTest.domain.*;
import com.RPGTest.domain.Skills.boss.*;

public class Boss04 extends Boss {
    public Boss04() {
        super("","飞沙之剑圣", 800, 300, 100, 80);
        this.weak = ElementType.getWeakness(ElementType.EARTH);

        skills.add(new EarthSword());
        skills.add(new EarthThick());
        skills.add(new EarthArmor());
    }
}

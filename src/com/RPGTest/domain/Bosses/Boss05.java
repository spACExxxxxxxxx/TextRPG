package com.RPGTest.domain.Bosses;

import com.RPGTest.domain.*;
import com.RPGTest.domain.Skills.boss.*;

public class Boss05 extends Boss {
    public Boss05() {
        super("","炎魔", 3600, 200, 120, 0);
        this.weak = ElementType.getWeakness(ElementType.FIRE);

        skills.add(new FireInferno());
        skills.add(new FireScorch());
        skills.add(new FireFlame());
    }
}

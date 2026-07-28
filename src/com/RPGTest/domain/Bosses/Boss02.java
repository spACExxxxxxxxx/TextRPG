package com.RPGTest.domain.Bosses;

import com.RPGTest.domain.*;
import com.RPGTest.domain.Skills.boss.*;

public class Boss02 extends Boss {
    public Boss02() {
        super("","三一结构体", 900, 150, 40, 35);
        this.weak = ElementType.getWeakness(ElementType.LIGHT);

        skills.add(new HolyJudgement());
        skills.add(new HolyRain());
        skills.add(new HolyBlessing());
    }
}

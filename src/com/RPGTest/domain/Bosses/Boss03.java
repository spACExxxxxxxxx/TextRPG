package com.RPGTest.domain.Bosses;

import com.RPGTest.domain.*;
import com.RPGTest.domain.Bosses.minions.*;
import com.RPGTest.domain.Skills.boss.*;

import java.util.List;

public class Boss03 extends Boss {
    public Boss03() {
        super("","风暴主", 1200, 300, 80, 70);
        this.weak = ElementType.getWeakness(ElementType.WIND);

        skills.add(new WindCalling());
        skills.add(new WindDance());
        skills.add(new WindEyes());
    }

    @Override
    public List<Enemy> getMinions(int battleCount) {
        List<Enemy> minions = new java.util.ArrayList<>();
        minions.add(new WindMonster());
        minions.add(new WindTemple());
        return minions;
    }
}

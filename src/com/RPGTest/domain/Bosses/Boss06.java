package com.RPGTest.domain.Bosses;

import com.RPGTest.domain.*;
import com.RPGTest.domain.Bosses.minions.IceSpear;
import com.RPGTest.domain.Bosses.minions.WindMonster;
import com.RPGTest.domain.Bosses.minions.WindTemple;
import com.RPGTest.domain.Skills.boss.*;

import java.util.List;

public class Boss06 extends Boss {
    public Boss06() {
        super("","苍银帝", 2100, 800, 100, 70);
        this.weak = ElementType.getWeakness(ElementType.ICE);

        skills.add(new IceLord());
        skills.add(new IceStorm());
        skills.add(new IceFlower());
    }
    @Override
    public List<Enemy> getMinions(int battleCount) {
        List<Enemy> minions = new java.util.ArrayList<>();
        minions.add(new IceSpear());
        minions.add(new IceSpear());
        minions.add(new IceSpear());
        return minions;
    }
}

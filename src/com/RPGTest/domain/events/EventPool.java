package com.RPGTest.domain.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 事件池：管理所有特殊事件，权重随机抽取
 */
public class EventPool {
    private static final List<SpecialEvent> events = new ArrayList<>();
    private static final Random random = new Random();

    static {
        events.add(new TreasureEvent());        //宝箱 30
        events.add(new FountainEvent());        //生命之泉 8
        events.add(new SacrificeEvent());       //祭坛 5
        events.add(new CampfireEvent());        //营火 30
        events.add(new PhantomEvent());         //神秘老爷爷 5
        events.add(new CurseEvent());           //诅咒 5
        events.add(new ArcherEvent());          //男神来了 2
        events.add(new BossEvent());            //漫步灵庙 3
        events.add(new BridgeEvent());          //桥 2
        events.add(new SoulSuckerEvent());      //吸魂鬼 5
        events.add(new RuinEvent());            //废都遗迹 3
        events.add(new AlchemyEvent());         //人体炼成阵 2
    }

    /**
     * 按权重随机抽取一个事件
     */
    public static SpecialEvent roll() {
        int totalWeight = 0;
        for (SpecialEvent e : events) {
            totalWeight += e.weight;
        }
        int roll = random.nextInt(totalWeight);
        int cumulative = 0;
        for (SpecialEvent e : events) {
            cumulative += e.weight;
            if (roll < cumulative) {
                return e;
            }
        }
        return events.get(events.size() - 1);
    }
}

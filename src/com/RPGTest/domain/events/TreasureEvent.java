package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class TreasureEvent extends SpecialEvent {
    public TreasureEvent() {
        super("宝箱", 30, "---一个宝箱！在RPG中还有什么比这更令人兴奋呢？");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        int roll = new Random().nextInt(100);
        System.out.println("\n  你打开了宝箱···");

        if (roll < 30) {
            System.out.println("  里面是5个技能点！");
            player.addSomeSkillPoint(5);
        } else if (roll < 90) {
            System.out.println("  搜刮出了能用的装备！");
            treasureStat(player);
            treasureStat(player);
        } else {
            System.out.println("  宝箱突然张开血盆大口——是宝箱怪！");
            int threshold = player.battleCount *2 + 30;

            if (player.def >= threshold) {
                System.out.println("  还好你防御够强，防下了宝箱怪的突袭！");
                System.out.println("  将宝箱怪杀掉了");
                System.out.println("  掉落了宝物：");
                player.addSomeSkillPoint(5);
                treasureStat(player);
            } else {
                int bite = player.maxHp / 3;
                player.takePhysicalDamage( bite);
                System.out.println("  你被咬了一口！");
                System.out.println("  宝箱怪溜走了");
            }
        }
        return true;
    }

    private void treasureStat(MyCharacter player) {
        switch (new Random().nextInt(4)) {
            case 0 -> {
                player.maxHp += 30; player.hp += 30;
                System.out.println("  生命上限 +30！");
            }
            case 1 -> {
                player.maxMp += 10; player.mp += 10;
                System.out.println("  魔力上限 +10！");
            }
            case 2 -> {
                player.atk += 6;
                System.out.println("  攻击力 +6！");
            }
            case 3 -> {
                player.def += 6;
                System.out.println("  防御力 +6！");
            }
        }
    }
}

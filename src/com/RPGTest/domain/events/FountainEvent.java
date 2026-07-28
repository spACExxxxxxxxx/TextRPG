package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import java.util.Random;
import java.util.Scanner;

public class FountainEvent extends SpecialEvent {
    public FountainEvent() {
        super("生命之泉", 8, "---一汪泛着幽蓝光芒的泉水，或许住着泉水精灵？");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  你要：");
        System.out.println("  1. 泉水清澈，喝一口缓解疲惫    (回复 全部生命)");
        System.out.println("  2. 效仿农夫的斧头，扔个装备进去 (与 随机数 有关)");
        System.out.print("  请选择行动: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            choice = 1;
        }

        switch (choice) {
            case 1 -> {
                player.hp = player.maxHp;
                System.out.println("  好甜！");
                System.out.println("  (话说这算不算美少女的洗澡水)");
                System.out.println("  生命恢复了！");
            }
            case 2 -> {
                System.out.println("\n  扔什么进去？");
                System.out.println("  1. 生命之戒  (MaxHP -20)");
                System.out.println("  2. 魔力披风  (MaxMP -10)");
                System.out.println("  3. 宝剑     (ATK -6)");
                System.out.println("  4. 盾牌     (DEF -4)");
                System.out.println("  0. 算了");
                System.out.print("  请选择: ");

                int item;
                try {
                    item = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    item = 0;
                }

                if (item < 1 || item > 4) {
                    System.out.println("  你收回了手。");
                    return true;
                }

                String itemName = "";
                int loss = 0;
                switch (item) {
                    case 1 -> { player.maxHp -= 20; loss = 20; itemName = "生命之戒"; }
                    case 2 -> { player.maxMp -= 10; loss = 10; itemName = "魔力披风"; }
                    case 3 -> { player.atk   -=  6; loss =  6; itemName = "宝剑"; }
                    case 4 -> { player.def   -=  4; loss =  4; itemName = "盾牌"; }
                }
                if (player.hp > player.maxHp) player.hp = player.maxHp;
                if (player.mp > player.maxMp) player.mp = player.maxMp;
                System.out.println("  你将「" + itemName + "」丢入了泉水中...");

                int roll = new Random().nextInt(100);
                if (roll < 10) {
                    // 5%: 砸晕泉水精灵，全属性 +20%
                    player.maxHp += 300;
                    System.out.println("  你好像听到水中传来'啊'的一声");
                    System.out.println("  咕嘟咕嘟······");
                    System.out.println("  咕嘟咕嘟咕嘟···");
                    System.out.println("  一具人体浮了上来····你好像把她砸晕了");
                    System.out.println("  获得了 泉水精灵的宝物 ！");
                    System.out.println("  最大生命增加了300!");
                } else if (roll < 50) {
                    // 50%: 泉水精灵出现，5倍返还
                    int gain = loss * 3;
                    switch (item) {
                        case 1 -> { player.maxHp += gain; player.hp = player.maxHp; }
                        case 2 -> { player.maxMp += gain; player.mp = player.maxMp; }
                        case 3 -> player.atk += gain;
                        case 4 -> player.def += gain;
                    }
                    System.out.println("  泉水精灵浮出水面！");
                    System.out.println("  '我知道你想干什么，直接给你吧'");
                    System.out.println("  你获得了黄金的白银的和你自己的" + itemName);
                } else {
                    // 75%: 无事发生
                    System.out.println("  水面泛起涟漪...然后归于平静。");
                    System.out.println("  泉水精灵似乎不在家。");
                }
            }
        }
        return true;
    }
}

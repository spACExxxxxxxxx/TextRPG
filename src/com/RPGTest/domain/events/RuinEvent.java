package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import java.util.Scanner;

public class RuinEvent extends SpecialEvent {
    public RuinEvent() {
        super("废都遗迹的宝石", 3, "---宫殿的遗迹，正中央是深邃的荷花池");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  这里是废都的遗迹");
        System.out.println("  你穿越遗迹的诸多房间和长廊，来到了广场区域");
        System.out.println("  广场的中央是一个荷花池。荷花正不分季节和场合地妖艳地绽放着");
        System.out.println("  你走近池子，向水下望去，池水幽暗而深邃，难以窥见池底");
        System.out.println("  突然，仿佛有幻影在水中闪烁，");
        System.out.println("  你觉得那是: ");
        System.out.println("  1. 翡翠石一般翠绿的生机幻影");
        System.out.println("  2. 青金石一般苍蓝的聪慧幻影");
        System.out.println("  3. 石榴石一般火红的征战幻影");
        System.out.println("  4. 琥珀石一般金黄的坚韧幻影");
        System.out.println("  0. 什么都没有，水中只有自己的倒影");
        System.out.print("  请选择: ");

        int pick;
        try { pick = Integer.parseInt(sc.nextLine()); }
        catch (NumberFormatException e) { pick = 0; }

        switch (pick) {
            case 1 -> {
                player.maxHp += 200;
                player.hp += 200;
                player.def -= 18;
                System.out.println("  得到了 悲鸣之翡翠石");
                System.out.println("  最大生命值增加了，防御力减少了");
            }
            case 2 -> {
                player.maxMp += 50;
                player.mp += 50;
                player.maxHp -= 120;
                System.out.println("  得到了 臆想之青金石");
                System.out.println("  最大魔力值增加了，最大生命值减少了");
            }
            case 3 -> {
                player.atk += 30;
                player.maxMp -= 30;
                System.out.println("  得到了 诅咒之石榴石");
                System.out.println("  攻击力增加了，最大魔力值减少了");
            }
            case 4 -> {
                player.def += 30;
                player.atk -= 18;
                System.out.println("  得到了 羁勒之琥珀石");
                System.out.println("  防御力增加了，攻击力减少了");
            }
            case 0 -> {
                player.maxHp += 100; player.hp += 100;
                player.maxMp += 25; player.mp += 25;
                player.atk += 15;
                player.def += 15;
                System.out.println("  你离开了废都遗迹");
                System.out.println("\n  突然，你感觉手里多了什么东西");
                System.out.println("  得到了 未来之月长石");
                System.out.println("  全属性增加了");
            }
        }
        return true;
    }
}

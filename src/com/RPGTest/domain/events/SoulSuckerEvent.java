package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import java.util.Scanner;

public class SoulSuckerEvent extends SpecialEvent {
    public SoulSuckerEvent() {
        super("吸魂鬼", 5, "---想要力量吗");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  一只吸魂鬼正蹲在角落里，看样子马上要饿死了");
        System.out.println("  '求求你了，给我吸一口吧，" +
                "\n  我好久没吸了，感觉身上有虫子在咬'");

        while (true) {
            System.out.println("\n  当前: MP=" + player.mp + "/" + player.maxMp
                    + "  HP=" +  player.hp + "/" +  player.maxHp + "  技能点：" + player.skillPoints);
            System.out.println("  1. 献出灵魂 (-30MP/100HP，获得 1 技能点)");
            System.out.println("  2. 一脚踹死 (与 攻击力 有关)");
            System.out.println("  0. 离开");
            System.out.print("  请选择: ");

            int pick;
            try {
                pick = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                pick = 0;
            }

            switch (pick) {
                case 1:
                    if (player.mp >= 30) {
                        player.mp -= 30;
                        System.out.println("  吸魂鬼吸取了你的魔力");
                        player.addSomeSkillPoint(1);
                        break;
                    } else if (player.hp > 100) {
                        player.hp -= 100;
                        System.out.println("  吸魂鬼吸取了你的生命");
                        player.addSomeSkillPoint(1);
                        break;
                    } else {
                        System.out.println("\n  你没有足够的生命和魔力来喂食吸魂鬼");
                        break;
                    }
                case 2:
                    if (player.atk > 100) {
                        System.out.println("\n  你一脚踹死了吸魂鬼");
                        System.out.println("  爆金币咯");
                        player.addSomeSkillPoint(3);
                    }else{
                        System.out.println("\n  你踹了吸魂鬼一脚，力量太弱，被轻松接下");
                        System.out.println("  '喂，你干嘛？！'");
                        System.out.println("  你被反杀了");
                        System.out.println("  吸魂鬼吸取了你的生命");
                        player.takePhysicalDamage(250);
                        System.out.println("  吸魂鬼离开了");
                    }
                    return true;
                case 0:
                    System.out.println("  你离开了");
                    return true;
            }
        }
    }
}

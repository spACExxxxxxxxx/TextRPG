package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import java.util.Scanner;

public class SacrificeEvent extends SpecialEvent {
    public SacrificeEvent() {
        super("祭坛", 5, "---阴森的祭坛，但似乎可以大幅提升属性");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  可以献祭技能点或生命强化自身");

        int count = 0;
        while (count < 3) {
            System.out.println("\n  剩余献祭次数: " + (3 - count));
            System.out.println("  选择提升属性：");
            System.out.println("    1. HP +30");
            System.out.println("    2. MP +10");
            System.out.println("    3. ATK +6");
            System.out.println("    4. DEF +6");
            System.out.println("    0. 离开祭坛");
            System.out.println("  当前: 技能点=" + player.skillPoints + "  HP=" + player.hp + "/" + player.maxHp);
            System.out.print("  请选择: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = 0;
            }

            if (choice == 0) break;
            if (choice < 1 || choice > 4) {
                System.out.println("  无效选择！");
                continue;
            }

            String stat = switch (choice) {
                case 1 -> "HP";
                case 2 -> "MP";
                case 3 -> "ATK";
                case 4 -> "DEF";
                default -> "";
            };

            // 选择支付方式
            boolean canPoint = player.skillPoints >= 1;
            boolean canHp = player.hp > 100;
            if (!canPoint && !canHp) {
                System.out.println("  技能点不足且生命过低，无法继续献祭。");
                break;
            }

            System.out.println("  支付方式：");
            if (canPoint) System.out.println("    1. 消耗 1 技能点");
            if (canHp)   System.out.println("    2. 献祭 100 生命");
            System.out.print("  请选择: ");

            int pay;
            try {
                pay = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }

            if (pay == 1 && canPoint) {
                player.spendSkillPoints(1);
                player.upgradeStat(stat);
                player.upgradeStat(stat);
                System.out.println("  献祭技能点，" + stat + " 提升了！");
                count++;
            } else if (pay == 2 && canHp) {
                player.hp -= 100;
                player.upgradeStat(stat);
                player.upgradeStat(stat);
                System.out.println("  献祭 100 生命，" + stat + " 提升了！");
                count++;
            } else {
                System.out.println("  无法以此方式支付。");
            }
        }

        System.out.println("  你离开了祭坛。");
        return true;
    }
}

package com.RPGTest.service;

import com.RPGTest.domain.MyCharacter;
import com.RPGTest.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 战斗后商店
 * 1. 分配属性点数（1点换属性提升）
 * 2. 购买技能（随机3个普通技能，2-3点一个）
 */
public class ShopService {

    /**
     * 打开商店
     */
    public static void openShop(MyCharacter player, Scanner sc) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        🏪 战后商店 🏪         ║");
        System.out.println("╚══════════════════════════════╝");

        // 本场战斗结束后一次性生成技能列表，防止反复进出商店页面刷新技能
        // 排除已拥有 + 已出售的技能
        List<Skill> excludeList = new ArrayList<>(player.skills);
        excludeList.addAll(player.soldSkills);
        List<Skill> offeredSkills = SkillPool.getRandomNormalSkills(3, excludeList);

        while (true) {
            player.showStatus();

            System.out.println("\n请选择操作：");
            System.out.println("  1. 提升属性（消耗1点数）");
            System.out.println("  2. 购买技能（消耗2-4点数）");
            System.out.println("  3. 出售技能（返还1点数）");
            System.out.println("  4. 离开商店，继续下一场战斗");
            System.out.print("请输入选择: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字！");
                continue;
            }

            switch (choice) {
                case 1 -> upgradeStat(player, sc);
                case 2 -> buySkill(player, sc, offeredSkills);
                case 3 -> sellSkill(player, sc);
                case 4 -> {
                    System.out.println("离开商店，准备下一场战斗...");
                    return;
                }
                default -> System.out.println("无效选择！");
            }
        }
    }

    /**
     * 提升属性
     */
    private static void upgradeStat(MyCharacter player, Scanner sc) {
        while (true) {
            if (player.skillPoints < 1) {
                System.out.println("点数不足！至少需要1点。当前点数: " + player.skillPoints);
                return;
            }

            System.out.println("\n--- 属性提升 ---");
            System.out.println("  点数: " + player.skillPoints + "  消耗1点");
            System.out.println("  1. HP +15  (当前: " + player.maxHp + ")");
            System.out.println("  2. MP +5   (当前: " + player.maxMp + ")");
            System.out.println("  3. ATK +3  (当前: " + player.atk + ")");
            System.out.println("  4. DEF +3  (当前: " + player.def + ")");
            System.out.println("  0. 返回");
            System.out.print("  请选择: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("  请输入有效数字！");
                continue;
            }

            if (choice == 0) return;

            String stat = switch (choice) {
                case 1 -> "HP";
                case 2 -> "MP";
                case 3 -> "ATK";
                case 4 -> "DEF";
                default -> null;
            };

            if (stat == null) {
                System.out.println("  无效选择！");
                continue;
            }

            if (player.spendSkillPoints(1)) {
                player.upgradeStat(stat);
            }
        }
    }

    /**
     * 购买技能
     */
    private static void buySkill(MyCharacter player, Scanner sc, List<Skill> offeredSkills) {
        System.out.println("\n--- 技能商店 ---");
        System.out.println("当前点数: " + player.skillPoints);

        if (offeredSkills.isEmpty()) {
            System.out.println("你已经拥有了所有普通技能！");
            return;
        }

        for (int i = 0; i < offeredSkills.size(); i++) {
            Skill s = offeredSkills.get(i);
            int price = s.price;
            System.out.println("  " + (i + 1) + ". " + s.toString() + " [价格:" + price + "点]");
        }
        System.out.println("  0. 取消购买");
        System.out.print("请选择要购买的技能: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("请输入有效数字！");
            return;
        }

        if (choice == 0) {
            System.out.println("取消购买。");
            return;
        }

        if (choice < 1 || choice > offeredSkills.size()) {
            System.out.println("无效选择！");
            return;
        }

        Skill selectedSkill = offeredSkills.get(choice - 1);
        int price = selectedSkill.price;

        if (player.skillPoints < price) {
            System.out.println("点数不足！需要 " + price + " 点，当前只有 " + player.skillPoints + " 点。");
            return;
        }

        if (player.spendSkillPoints(price)) {
            if (player.addSkill(selectedSkill)) {
                offeredSkills.remove(selectedSkill);  // 购买成功后从列表中移除，防止重复购买
            }
        }
    }

    /**
     * 出售技能（返还1个技能点，技能永久消失）
     */
    private static void sellSkill(MyCharacter player, Scanner sc) {
        if (player.skills.isEmpty()) {
            System.out.println("你没有可出售的技能！");
            return;
        }

        System.out.println("\n--- 出售技能 ---");
        System.out.println("当前点数: " + player.skillPoints);
        for (int i = 0; i < player.skills.size(); i++) {
            Skill s = player.skills.get(i);
            System.out.println("  " + (i + 1) + ". " + s.toBriefString() + "  [返还1点]");
        }
        System.out.println("  0. 取消");
        System.out.print("请选择要出售的技能(被出售的技能将不会再出现在商店中): ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("请输入有效数字！");
            return;
        }

        if (choice == 0) {
            System.out.println("取消出售。");
            return;
        }

        if (choice < 1 || choice > player.skills.size()) {
            System.out.println("无效选择！");
            return;
        }

        Skill selectedSkill = player.skills.get(choice - 1);
        player.removeSkill(selectedSkill);
    }
}

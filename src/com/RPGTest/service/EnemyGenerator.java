package com.RPGTest.service;

import com.RPGTest.domain.Boss;
import com.RPGTest.domain.Bosses.*;
import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 敌人自动生成器
 */
public class EnemyGenerator {

    private static final Random random = new Random();

    public static Enemy generateEnemy(int battleCount) {
        String firstName = ElementType.getRandomElementName();
        String lastName = Enemy.LAST_NAMES[random.nextInt(Enemy.LAST_NAMES.length)];
        Enemy enemy = new Enemy(firstName, lastName, battleCount);
        char element = ElementType.getElementByName(firstName);
        enemy.setSkills(SkillPool.getRandomEnemySkills(element, battleCount));
        return enemy;
    }

    public static List<Enemy> generateEnemies(int battleCount, Scanner sc) {
        List<Enemy> enemies = new ArrayList<>();
        int battleNumber = battleCount + 1;

        // 最终 Boss：第31场
        if (battleNumber == 31) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        ☠  最终决战！ ☠        ║");
            System.out.println("╚══════════════════════════════╝");
            Boss boss = new Boss07();
            enemies.add(boss);
            boss.showBrief();
            System.out.print("      技能: ");
            for (int j = 0; j < boss.skills.size(); j++) {
                System.out.print(boss.skills.get(j).name);
                if (j < boss.skills.size() - 1) System.out.print("、");
            }
            System.out.println();
            return enemies;
        }

        // Boss 战（每10场）
        if (battleNumber % 10 == 0) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       👑  !BOSS战!  👑       ║");
            System.out.println("╚══════════════════════════════╝");
            Boss boss = getBoss(battleNumber, sc);
            enemies.add(boss);
            java.util.List<Enemy> minions = boss.getMinions(battleCount);
            enemies.addAll(minions);
            System.out.print("  ");
            boss.showBrief();
            System.out.print("      技能: ");
            for (int j = 0; j < boss.skills.size(); j++) {
                System.out.print(boss.skills.get(j).name);
                if (j < boss.skills.size() - 1) System.out.print("、");
            }
            System.out.println();
            for (Enemy m : minions) {
                System.out.print("     👾 ");
                m.showBrief();
                System.out.println();
            }
            return enemies;
        }

        // 普通敌人数量
        int count;
        if (battleCount < 7) {
            count = 1;
        } else if (battleCount < 14) {
            count = random.nextInt(2) + 1;
        } else if (battleCount < 23) {
            count = random.nextInt(2) + 2;
        } else {
            count = random.nextInt(2) + 2;
            if (battleCount >= 24 && random.nextInt(100) < 30) {
                count = 4;
            }
        }

        System.out.println("\n⚔️  第 " + battleNumber + " 场战斗！敌人数量: " + count);

        for (int i = 0; i < count; i++) {
            Enemy enemy = generateEnemy(battleCount);
            enemies.add(enemy);
            System.out.print("  👾 ");
            enemy.showBrief();
            System.out.print("      弱点: " + enemy.weak + "  技能: ");
            for (int j = 0; j < enemy.skills.size(); j++) {
                System.out.print(enemy.skills.get(j).name);
                if (j < enemy.skills.size() - 1) System.out.print("、");
            }
            System.out.println();
        }

        return enemies;
    }

    private static Boss getBoss(int battleNumber, Scanner sc) {
        int bossIndex = battleNumber / 10;  // 1, 2, 3...

        switch (bossIndex) {
            case 1 -> {
                // 第15场：光暗二选一
                System.out.println("\n选择你的对手：");
                System.out.println("  1. 暗影龙 [暗]");
                System.out.println("  2. 三一结构体 [光]");
                System.out.print("请选择: ");
                try {
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice == 2) return new Boss02();
                } catch (NumberFormatException ignored) {}
                return new Boss01();
            }
            case 2 -> {
                // 第30场：风地随机
                return random.nextBoolean() ? new Boss03() : new Boss04();
            }
            case 3 -> {
                // 第45场：水火随机
                return random.nextBoolean() ? new Boss05() : new Boss06();
            }
            default -> {
                return new Boss01();
            }
        }
    }
}

package com.RPGTest.service;

import com.RPGTest.domain.Boss;
import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.Enemy;
import com.RPGTest.domain.MyCharacter;
import com.RPGTest.domain.Skill;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 回合制战斗核心逻辑
 */
public class BattleService {

    private static final Random random = new Random();

    /**
     * 开始一场战斗
     * @return true=胜利, false=失败
     */
    public static boolean startBattle(MyCharacter player, List<Enemy> enemies, Scanner sc) {
        System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃      ⚔️  第 "+ (player.battleCount + 1) +" 场战斗  ⚔️      ┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

        int turnCount = 0;

        // 回合循环
        while (player.isHpAlive() && !allEnemiesDead(enemies)) {
            turnCount++;
            System.out.println("\n━━━━━━ 第 " + turnCount + " 回合 ━━━━━━");

            // 回合开始：tick 双方 buff
            player.tickBuffs();
            for (Enemy e : enemies) {
                if (e.isHpAlive()) e.tickBuffs();
            }

            // === 玩家回合 ===
            playerTurn(player, enemies, sc);

            // 检查敌人是否全灭
            if (allEnemiesDead(enemies)) {
                break;
            }

            // === 敌人回合 ===
            enemyTurn(enemies, player);
        }

        // === 战斗结果 ===
        if (player.isHpAlive()) {
            System.out.println("\n  战斗胜利！所有敌人已被消灭！");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            return true;
        } else {
            System.out.println("\n  你被击败了...");
            System.out.println("  最终战绩：共进行了 " + player.battleCount + " 场战斗");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            return false;
        }
    }

    /**
     * 玩家回合
     */
    private static void playerTurn(MyCharacter player, List<Enemy> enemies, Scanner sc) {
        System.out.println("\n--- 你的回合 ---");
        player.showBrief();

        // 显示敌人列表
        System.out.println("\n敌人状态：");
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (e.isHpAlive()) {
                System.out.print("  " + (i + 1) + ". ");
                e.showBrief();
            }
        }

        // 玩家行动选择
        while (true) {
            System.out.println("\n请选择行动：");
            System.out.println("  1. 普通攻击（回复8点mp）");
            System.out.println("  2. 技能攻击");
            System.out.println("  3. 防御（本回合受到的伤害减半）");
            System.out.println("  4. 临时强化（不占行动）");
            System.out.println("  5. 回血药水 ×" + player.potionCount + "（不占行动）");
            System.out.print("请输入选择: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字！");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    // 普通攻击：选择目标
                    Enemy target = selectEnemyTarget(enemies, sc);
                    if (target != null) {
                        player.normalAttack(target);
                        return;
                    }
                }
                case 2 -> {
                    // 技能攻击
                    if (player.skills.isEmpty()) {
                        System.out.println("你没有可用的技能！");
                        continue;
                    }
                    Skill selectedSkill = selectSkill(player, sc);
                    if (selectedSkill == null) {
                        continue; // 取消选择
                    }
                    if (selectedSkill.isAOE) {
                        // 群攻：只扣一次MP，临时设cost=0避免后续循环重复扣费
                        if (!player.mpCost(selectedSkill.cost)) {
                            System.out.println("MP不足！");
                            continue;
                        }
                        int savedCost = selectedSkill.cost;
                        selectedSkill.cost = 0;
                        for (Enemy e : enemies) {
                            if (e.isHpAlive()) {
                                selectedSkill.use(player, e);
                            }
                        }
                        selectedSkill.cost = savedCost;
                        return;
                    } else {
                        // 单体：选择目标
                        Enemy target = selectEnemyTarget(enemies, sc);
                        if (target != null) {
                            selectedSkill.use(player, target);
                            return;
                        }
                    }
                }
                case 3 -> {
                    player.inDefense = true;
                    System.out.println(player.lastName + " 进入防御状态！");
                    return;
                }
                case 4 -> {
                    if (player.skillPoints < 1) {
                        System.out.println("技能点不足！当前只有 " + player.skillPoints + " 点。");
                        continue;
                    }
                    System.out.println("\n选择强化属性（消耗1点，本场战斗内生效）：");
                    System.out.println("  1. HP +40");
                    System.out.println("  2. MP +10");
                    System.out.println("  3. ATK +6");
                    System.out.println("  4. DEF +6");
                    System.out.println("  0. 取消");
                    System.out.print("请输入选择: ");

                    int boostChoice;
                    try {
                        boostChoice = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("请输入有效数字！");
                        continue;
                    }

                    if (boostChoice == 0) continue;

                    boolean applied = false;
                    switch (boostChoice) {
                        case 1 -> {
                            if (player.spendSkillPoints(1)) {
                                player.maxHp += 40;
                                player.hp += 40;
                                player.tempHpBoost += 40;
                                System.out.println("  生命上限 +40！当前 HP: " + player.hp + "/" + player.maxHp);
                                applied = true;
                            }
                        }
                        case 2 -> {
                            if (player.spendSkillPoints(1)) {
                                player.maxMp += 10;
                                player.mp += 10;
                                player.tempMpBoost += 10;
                                System.out.println("  魔力上限 +10！当前 MP: " + player.mp + "/" + player.maxMp);
                                applied = true;
                            }
                        }
                        case 3 -> {
                            if (player.spendSkillPoints(1)) {
                                player.atk += 6;
                                player.tempAtkBoost += 6;
                                System.out.println("  攻击力 +6！当前 ATK: " + player.atk);
                                applied = true;
                            }
                        }
                        case 4 -> {
                            if (player.spendSkillPoints(1)) {
                                player.def += 6;
                                player.tempDefBoost += 6;
                                System.out.println("  防御力 +6！当前 DEF: " + player.def);
                                applied = true;
                            }
                        }
                        default -> System.out.println("无效选择！");
                    }

                    // 不return，继续主菜单，玩家可以再攻击/防御
                    continue;
                }
                case 5 -> {
                    if (player.potionCount < 1) {
                        System.out.println("药水已用完！");
                        continue;
                    }
                    player.potionCount--;
                    int heal = 100 + player.maxHp / 10;
                    player.hp += heal;
                    if (player.hp > player.maxHp) player.hp = player.maxHp;
                    System.out.println("  使用了回血药水！HP +" + heal + "（剩余: " + player.potionCount + " 瓶）");
                    continue;
                }
                default -> System.out.println("无效选择，请重新输入！");
            }
        }
    }

    /**
     * 选择技能
     */
    private static Skill selectSkill(MyCharacter player, Scanner sc) {
        System.out.println("\n┌" + "─".repeat(70)+"┐");
        for (int i = 0; i < player.skills.size(); i++) {
            Skill s = player.skills.get(i);
            String affordable = player.mp >= s.cost ? "✓" : "✗";
            System.out.println("│ " + affordable + " " + (i + 1) + ". " + s.toString());
        }
        System.out.println("│ 0. 取消");
        System.out.println("└"+ "─".repeat(70) +"┘");

        while (true) {
            System.out.print("请选择: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字！");
                continue;
            }

            if (choice == 0) return null;
            if (choice >= 1 && choice <= player.skills.size()) {
                Skill s = player.skills.get(choice - 1);
                if (player.mp < s.cost) {
                    System.out.println("MP不足！请选择其他技能。");
                    continue;
                }
                return s;
            }
            System.out.println("无效选择！");
        }
    }

    /**
     * 选择敌人目标
     */
    private static Enemy selectEnemyTarget(List<Enemy> enemies, Scanner sc) {
        // 获取存活的敌人
        List<Enemy> aliveEnemies = enemies.stream()
                .filter(Enemy::isHpAlive)
                .toList();

        if (aliveEnemies.size() == 1) {
            return aliveEnemies.get(0);
        }

        System.out.println("\n选择目标：");
        for (int i = 0; i < aliveEnemies.size(); i++) {
            System.out.print("  " + (i + 1) + ". ");
            aliveEnemies.get(i).showBrief();
        }
        System.out.print("请选择目标（输入0取消）: ");

        while (true) {
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字！");
                continue;
            }

            if (choice == 0) return null;
            if (choice >= 1 && choice <= aliveEnemies.size()) {
                return aliveEnemies.get(choice - 1);
            }
            System.out.println("无效选择！");
        }
    }

    /**
     * 敌人回合
     */
    private static void enemyTurn(List<Enemy> enemies, MyCharacter player) {
        System.out.println("\n--- 敌人回合 ---");

        for (Enemy enemy : enemies) {
            if (!enemy.isHpAlive()) continue;
            if (!player.isHpAlive()) break;

            // Boss 隔一回合行动
            if (enemy instanceof Boss boss && !boss.takeAction()) {
                continue;
            }

            // 脚本化 Boss：走预定技能，不走随机 AI
            if (enemy instanceof Boss boss && boss.hasScript()) {
                Skill scripted = boss.getScriptedSkill(player);
                if (scripted != null) {
                    scripted.use(enemy, player);
                } else if (!boss.scriptedSkip) {
                    enemy.normalAttack(player);
                }
                continue;
            }

            // 过滤 MP 够用的技能
            List<Skill> affordable = new java.util.ArrayList<>();
            for (Skill s : enemy.skills) {
                if (enemy.mp >= s.cost) affordable.add(s);
            }

            // 2/3 概率使用技能
            if (!affordable.isEmpty() && random.nextInt(3) < 2) {
                Skill chosen = pickSkill(enemy, affordable);
                if (chosen != null) {
                    chosen.use(enemy, player);
                    continue;
                }
            }
            enemy.normalAttack(player);
        }
    }

    /**
     * 敌人技能选择：buff优先 → 治疗(HP<30%)优先 → 随机
     */
    private static Skill pickSkill(Enemy enemy, List<Skill> affordable) {
        // 1. 有自buff技能且身上没同类buff → 优先
        for (Skill s : affordable) {
            if (s.isBuff && !hasAnyBuff(enemy)) {
                return s;
            }
        }
        // 2. HP < 30% 且有治疗技能(ElementType.HEAL) → 优先
        if (enemy.hp < enemy.maxHp * 0.3) {
            for (Skill s : affordable) {
                if (s.element == com.RPGTest.domain.ElementType.HEAL) return s;
            }
        }
        // 3. 否则随机选
        return affordable.get(random.nextInt(affordable.size()));
    }

    /** 敌人身上是否已有任意增益buff */
    private static boolean hasAnyBuff(Enemy enemy) {
        for (com.RPGTest.domain.Buff b : enemy.buffs) {
            if (b.type == BuffType.ATK_UP || b.type == BuffType.DEF_UP) return true;
        }
        return false;
    }

    /**
     * 检查所有敌人是否死亡
     */
    private static boolean allEnemiesDead(List<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (e.isHpAlive()) return false;
        }
        return true;
    }
}

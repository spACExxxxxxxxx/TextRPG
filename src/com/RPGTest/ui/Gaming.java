package com.RPGTest.ui;

import com.RPGTest.domain.Boss;
import com.RPGTest.domain.Enemy;
import com.RPGTest.domain.MyCharacter;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.Skills.normal.*;
import com.RPGTest.domain.Skills.special.CraneWings;
import com.RPGTest.domain.Skills.special.GodHand;
import com.RPGTest.domain.Skills.special.MagicBullet;
import com.RPGTest.domain.Skills.special.WindField;
import com.RPGTest.service.BattleService;
import com.RPGTest.service.EnemyGenerator;
import com.RPGTest.service.ShopService;
import com.RPGTest.service.SkillPool;
import java.util.List;
import java.util.Scanner;

public class Gaming {

    private final Scanner sc = new Scanner(System.in);

    public void gameStart(String userName) {
        System.out.println("\n+------------" + "-".repeat(Math.max(1, userName.length())) + "----+");
        System.out.println("|  " + userName + "的RPG冒险");
        System.out.println("+-------------" + "-".repeat(Math.max(1, userName.length())) + "---+");

        MyCharacter player = createMyCharacter(userName);

        // 进入主游戏循环
        mainGameLoop(player);
    }

    /**
     * 创建角色：分配属性点 + 选择职业
     */
    public MyCharacter createMyCharacter(String userName) {
        while (true) {
            int baseHp = 150, baseMp = 50, baseAtk = 30, baseDef = 15;
            int hp = 0, mp = 0, atk = 0, def = 0;
            String firstname;
            Skill startingSkill1;
            Skill startingSkill2;
            Skill startingSkill3;

            System.out.println("\n请选择角色职业：");
            System.out.println("  1. Berserker - HP++, 获得技能「十二试炼」(治疗)");
            System.out.println("  2. Caster    - MP++, 获得技能「魔弹」(暗)");
            System.out.println("  3. Archer    - ATK++, 获得技能「鹤翼三连」");
            System.out.println("  4. Saber     - DEF++, 获得技能「风王结界」(风)");
            System.out.print("请输入选择: ");

            int classChoice;
            try {
                classChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字！");
                continue;
            }

            switch (classChoice) {
                case 1:
                    firstname = "Berserker";
                    baseHp += 100;
                    startingSkill1 = new GodHand();
                    startingSkill2 = new EarthBreath();
                    startingSkill3 = new AttackBreak();
                    System.out.println("你选择了 Berserker！\n" +
                            "获得技能「十二试炼」(治疗)\n" +
                            "获得技能「大地之息」(土)\n" +
                            "获得技能「破阵击」");
                    break;
                case 2:
                    firstname = "Caster";
                    baseMp += 25;
                    startingSkill1 = new MagicBullet();
                    startingSkill2 = new DarkSilence();
                    startingSkill3 = new MagicMode();
                    System.out.println("你选择了 Caster！\n" +
                            "获得技能「魔弹」(暗)\n" +
                            "获得技能「沉默禁令」(暗)\n" +
                            "获得技能「祭品」");
                    break;
                case 3:
                    firstname = "Archer";
                    baseAtk += 15;
                    startingSkill1 = new CraneWings();
                    startingSkill2 = new WindGoing();
                    startingSkill3 = new AttackDual();
                    System.out.println("你选择了 Archer！\n" +
                            "获得技能「鹤翼三连\n" +
                            "获得技能「天狗风」(风)\n" +
                            "获得技能「二连击」");
                    break;
                case 4:
                    firstname = "Saber";
                    baseDef += 15;
                    startingSkill1 = new WindField();
                    startingSkill2 = new HealingRain();
                    startingSkill3 = new AttackRapid();
                    System.out.println("你选择了 Saber！\n" +
                            "获得技能「风王结界」(风)\n" +
                            "获得技能「天降甘露」\n" +
                            "获得技能「拔刀斩」");
                    break;
                default:
                    System.out.println("没有这个职业，请重新选择。");
                    continue;
            }

            System.out.println("\n创建你的角色：");
            int point = 20;
            System.out.println("你有20点属性点，请分配属性：");

            // HP
            int input = readStatInput("生命值(10hp/1point)", point);
            hp += input;
            point -= input;

            // MP
            if (point > 0) {
                input = readStatInput("魔力值(5mp/1point)", point);
                mp += input;
                point -= input;
            }

            // ATK
            if (point > 0) {
                input = readStatInput("攻击力(3atk/1point)", point);
                atk += input;
                point -= input;
            }

            // DEF
            if (point > 0) {
                System.out.printf("剩余%d点，全部分配给防御力(2def/1point)。\n", point);
                def += point;
                point = 0;
            }

            

            // 创建角色
            MyCharacter player = new MyCharacter(firstname, userName,
                    (hp * 10 + baseHp), (mp * 5 + baseMp), (atk * 3 + baseAtk), (def * 2 + baseDef));
            player.skills.add(startingSkill1);
            player.skills.add(startingSkill2);
            player.skills.add(startingSkill3);
            System.out.println("\n✅ 角色创建成功！");
            player.showStatus();
            return player;
        }
    }

    /**
     * 读取属性输入（带验证）
     */
    private int readStatInput(String statName, int remaining) {
        while (true) {
            System.out.printf("分配给%s多少点？剩余%d点 (请输入0-%d): ", statName, remaining, remaining);
            int input;
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字！");
                continue;
            }
            if (input >= 0 && input <= remaining) {
                return input;
            }
            System.out.println("输入不合法！请输入0到" + remaining + "之间的数字。");
        }
    }

    /**
     * 主游戏循环
     */
    private void mainGameLoop(MyCharacter player) {
        while (true) {
            // 1. 生成敌人
            List<Enemy> enemies = EnemyGenerator.generateEnemies(player.battleCount, sc);

            // 2. 进入战斗
            boolean victory = BattleService.startBattle(player, enemies, sc);

            // 最终 Boss 战：无论胜败都跳结算
            Boss finalBoss = null;
            for (Enemy e : enemies) {
                if (e instanceof Boss b && b.isFinalBoss) {
                    finalBoss = b;
                    break;
                }
            }
            if (finalBoss != null) {
                showFinalResult(player, finalBoss, victory);
                return;
            }

            if (!victory) {
                // 战斗失败
                System.out.println("\n游戏结束！");
                System.out.println("最终战绩：第 " + (player.battleCount + 1) + " 场战斗");
                System.out.print("\n按回车键返回主菜单...");
                sc.nextLine();
                return;
            }

            // 3. 胜利：获得点数，增加战斗场次
            player.incrementBattleCount();
            player.addSomeSkillPoint(4);
            player.recoverAfterBattle();

            // 4. Boss 战奖励：选择一项 Boss 技能
            for (Enemy enemy : enemies) {
                if (enemy instanceof Boss boss) {
                    System.out.println("\n🏆 击败了「" + boss.firstName + "」！选择一项技能作为奖励：");
                    java.util.ArrayList<Skill> rewards = boss.getRewardSkills();
                    for (int i = 0; i < rewards.size(); i++) {
                        System.out.println("  " + (i + 1) + ". " + rewards.get(i).toString());
                    }
                    System.out.println("  4. 技能点*10" );
                    System.out.print("请选择: ");
                    try {
                        int pick = Integer.parseInt(sc.nextLine());
                        if (pick >= 1 && pick <= rewards.size()) {
                            player.addSkill(rewards.get(pick - 1));
                        } else {
                            System.out.println("选择了技能点奖励。");
                            player.addSomeSkillPoint(10);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("选择了技能外奖励。");
                        player.addSomeSkillPoint(10);
                    }
                    // Boss 战后补充药水
                    player.potionCount = player.battleCount >= 20 ? 5 : 4;
                    System.out.println("  🧪 补充了回血药水！（当前: " + player.potionCount + " 瓶）");
                    break;
                }
            }

            // 5. 每10场获得特色技能
            if (player.battleCount % 8 == 0) {
                System.out.println("\n🎊 第 " + player.battleCount + " 场战斗胜利！获得随机特色技能！");
                java.util.ArrayList<Skill> specialExclude = new java.util.ArrayList<>(player.skills);
                specialExclude.addAll(player.soldSkills);
                Skill specialSkill = SkillPool.getRandomSpecialSkill(specialExclude);
                if (specialSkill != null) {
                    player.addSkill(specialSkill);
                } else {
                    System.out.println("你已经拥有了所有特色技能！");
                }
            }

            // 5. 打开商店
            ShopService.openShop(player, sc);

            // 6. 特殊事件（35%概率）
            if (new java.util.Random().nextInt(100) < 35) {
                com.RPGTest.domain.events.SpecialEvent event =
                    com.RPGTest.domain.events.EventPool.roll();
                System.out.println("\n╔══════════════════════════════╗");
                System.out.println("║        ✨ 特殊事件 !✨        ║");
                System.out.println("╚══════════════════════════════╝");
                System.out.println("  「" + event.name + "」");
                System.out.println("  " + event.description);
                event.execute(player, sc);
                player.eventCount++;
            }

            // 7. 询问是否继续
            System.out.print("\n是否继续下一场战斗？(y/n): ");
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("n") || answer.equals("no")) {
                System.out.println("\n🏆 冒险暂停！");
                System.out.println("  最终战绩：完成了 " + player.battleCount + " 场战斗");
                player.showStatus();
                System.out.print("\n按回车键返回主菜单...");
                sc.nextLine();
                return;
            }
            System.out.println("\n══════════════════════════════════════");
            System.out.println("        准备下一场战斗...");
            System.out.println("══════════════════════════════════════");
        }
    }

    /**
     * 最终 Boss 战结算画面
     */
    private void showFinalResult(MyCharacter player, Boss boss, boolean victory) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║           最终决战 结果         ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println();
        System.out.println("  " + player.firstName + " " + player.lastName);
        System.out.println("  场次: " + player.battleCount);
        System.out.println("  HP: " + player.hp + "/" + player.maxHp
                + "  MP: " + player.mp + "/" + player.maxMp);
        System.out.println("  ATK: " + player.atk + "  DEF: " + player.def);
        System.out.println("  结果: " + (victory ? "🏆 胜利！" : "💀 败北"));
        System.out.println("  对「" + boss.lastName + "」造成伤害: " + boss.damageDealt);
        System.out.println("  得分: " + boss.damageDealt);
        System.out.println();
        System.out.println("───────────────────────────────");
        System.out.print("按回车键返回主菜单...");
        sc.nextLine();
    }
}

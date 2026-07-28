package com.RPGTest.domain.events;

import com.RPGTest.domain.BuffType;
import com.RPGTest.domain.MyCharacter;
import java.util.Random;
import java.util.Scanner;

public class CurseEvent extends SpecialEvent {
    public CurseEvent() {
        super("！遭到诅咒！", 5, "---你走进一处泥沼，泥沼的深处，一群咒蛙咕噜咕噜地渗着黑气");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  你要：");
        System.out.println("  1. 突袭敌人，或许能得到技能点      (与 攻击力 有关)");
        System.out.println("  2. 潜伏到它们离去，不会被咒蛙注意到 (与 随机数 有关)");
        System.out.print("  请选择行动: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            choice = 1;
        }

        switch (choice) {
            case 1 -> {
                if (player.atk >= thresholdATK(player)) {
                    System.out.println("  成功！你在咒蛙发觉前杀死了他们！");
                    player.addSomeSkillPoint(5);
                } else {
                    System.out.println("  失败！你不幸还是被黑雾侵染，遭到诅咒！");
                    player.maxHp -= player.maxHp / 3;
                    System.out.println("  最大生命值减少了30%！");
                }
            }
            case 2 -> {
                if (new Random().nextInt(100) >= 50) {
                    System.out.println("  成功！咒蛙离去了！");
                } else {
                    System.out.println("  失败！你不幸陷入泥沼！装备遗失了！");
                    player.applyBuff(BuffType.DEF_DOWN, 20, -1);
                    player.applyBuff(BuffType.ATK_DOWN, 20, -1);
                    System.out.println("  下次战斗防御力&攻击力减少了20%！");
                }
            }
        }
        return true;
    }

    private int thresholdATK(MyCharacter player) {
        return player.battleCount * 2 + 20;
    }
}

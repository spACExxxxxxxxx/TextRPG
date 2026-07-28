package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import com.RPGTest.domain.Skill;
import java.util.Random;
import java.util.Scanner;

public class BridgeEvent extends SpecialEvent {
    public BridgeEvent() {
        super("桥", 2, "---我恨桥");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  你走在连接两端悬崖的桥上");
        System.out.println("  桥断了！");
        if (player.skills.isEmpty()) {
            System.out.println("\n  你摔伤了");
            player.hp -= (int) (player.maxHp * 0.8);
            return true;
        }

        System.out.println("\n  你正在飞速下坠...");

        Random rng = new Random();
        Skill chosen = player.skills.get(rng.nextInt(player.skills.size()));

        while (true) {
            System.out.println("\n  将被丢弃的技能：「" + chosen.name + "」");
            System.out.println("    " + chosen.toBriefString());
            System.out.println("  1. 就选这个");
            System.out.println("  2. 换一个（扣除 10% HP）");
            System.out.print("  请选择: ");

            int pick;
            try { pick = Integer.parseInt(sc.nextLine()); }
            catch (NumberFormatException e) { pick = 1; }

            if (pick == 1) {
                player.skills.remove(chosen);
                player.soldSkills.add(chosen);
                System.out.println("  你丢弃了「" + chosen.name + "」。");
                break;
            } else {
                int loss = player.maxHp / 10;
                player.hp -= loss;
                System.out.println("当前HP为：" + player.hp+ "/" + player.maxHp);
                chosen = player.skills.get(rng.nextInt(player.skills.size()));
            }
        }

        System.out.println("\n  你恨桥");
        return true;
    }
}

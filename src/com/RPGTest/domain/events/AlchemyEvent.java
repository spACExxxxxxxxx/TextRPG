package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import java.util.Random;
import java.util.Scanner;

public class AlchemyEvent extends SpecialEvent {
    public AlchemyEvent() {
        super("人体炼成阵", 2, "---等价交换··吗");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  面前是一个小房间，你走了进去");
        System.out.println("  突然，血红的光芒从脚下爆发！你急忙低头看脚下");
        System.out.println("  脚下踩中的是沸腾的血红的魔法阵...");

        rollStats(player);
        player.hp = player.maxHp;
        player.mp = player.maxMp;
        showStats(player);

        System.out.println("\n  你感觉自己好像被重新创造了");
        System.out.println("  原来这是一座人体炼成阵");

        while (player.skillPoints >= 1) {
            System.out.println("\n  花费 1 技能点重新炼成？(y/n): ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("n")) break;

            player.spendSkillPoints(1);
            rollStats(player);
            player.hp = player.maxHp;
            player.mp = player.maxMp;
            showStats(player);
        }

        System.out.println("\n  炼成阵消散了。");
        return true;
    }

    private void rollStats(MyCharacter player) {
        int total = player.maxHp + player.maxMp + player.atk + player.def;
        Random rng = new Random();

        // HP: 随机从 total-3 中取（给剩下 3 属性各留至少 1）
        int hp  = rng.nextInt(total-1) + 1;
        total -= hp;
        // MP: 从剩余中取
        int mp  = rng.nextInt(total);
        total -= mp;
        // ATK: 从剩余中取
        int atk = rng.nextInt(total);
        total -= atk;
        // DEF: 余下全给
        int def = total;

        player.maxHp = hp;
        player.maxMp = mp;
        player.atk  = atk;
        player.def  = def;
    }

    private void showStats(MyCharacter player) {
        System.out.println("  MaxHP:" + player.maxHp
                + "  MaxMP:" + player.maxMp
                + "  ATK:" + player.atk
                + "  DEF:" + player.def);
    }
}

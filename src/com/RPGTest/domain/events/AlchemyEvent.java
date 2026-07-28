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
        // 各拿出 50% 汇入池中
        int poolHp  = player.maxHp / 2;
        int poolMp  = player.maxMp / 2;
        int poolAtk = player.atk / 2;
        int poolDef = player.def / 2;
        int total = poolHp + poolMp + poolAtk + poolDef;
        Random rng = new Random();

        // 随机分配池中的总值
        int min = total / 20;  // 每项至少分到 5%
        int rHp  = rng.nextInt(total - min * 3) + min;
        total -= rHp;
        int rMp  = rng.nextInt(total - min * 2) + min;
        total -= rMp;
        int rAtk = rng.nextInt(total - min) + min;
        total -= rAtk;
        int rDef = total;

        // 保留的 50% + 随机分配部分
        player.maxHp = poolHp + rHp;
        player.maxMp = poolMp + rMp;
        player.atk   = poolAtk + rAtk;
        player.def   = poolDef + rDef;
    }

    private void showStats(MyCharacter player) {
        System.out.println("  MaxHP:" + player.maxHp
                + "  MaxMP:" + player.maxMp
                + "  ATK:" + player.atk
                + "  DEF:" + player.def);
    }
}

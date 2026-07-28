package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import com.RPGTest.domain.Skill;
import com.RPGTest.service.SkillPool;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampfireEvent extends SpecialEvent {
    public CampfireEvent() {
        super("点燃营火", 30, "---孤独之地的一缕火光···");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  营火照耀着你。你要：");
        System.out.println("  1. 暂且休息  (回复 1/3生命)");
        System.out.println("  2. 汲取火光  (获得 3个技能点)");
        System.out.println("  3. 回忆战斗  (获得 1个技能)");
        System.out.print("  请选择行动: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            choice = 1;
        }

        switch (choice) {
            case 1 -> {
                player.hp += player.maxHp/3;
                if (player.hp > player.maxHp) player.hp = player.maxHp;
                System.out.println("\n  得到了休息···");
                System.out.println("  生命回复了！");
            }
            case 2 -> {
                System.out.println("\n  从火焰中汲取了力量");
                player.addSomeSkillPoint(3);
            }
            case 3 -> {
                System.out.println("\n  你回想了先前的战斗...");
                System.out.println("  你试着总结出什么···");
                List<Skill> available = SkillPool.getRandomNormalSkills(1, new ArrayList<>(player.skills));
                if (!available.isEmpty()) {
                    player.addSkill(available.get(0));
                } else {
                    System.out.println("  但你已掌握了全部技巧...");
                }
            }
        }
        return true;
    }
}
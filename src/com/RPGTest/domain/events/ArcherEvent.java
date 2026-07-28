package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.Skills.extral.Caladbolg;
import com.RPGTest.domain.Skills.special.UBWork;
import java.util.Scanner;

public class ArcherEvent extends SpecialEvent {
    public ArcherEvent() {
        super("男神来了", 2, "---你遇到了总是被自己帅醒的卫宫男神");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  '你会跳高么？'");
        System.out.println("  你表演了一次跳高，把男神迷住了");
        System.out.println("  '你要成为独角兽hunter的伙伴吗'");


        boolean hasUBW = false;
        for (Skill s : player.skills) {
            if (s instanceof UBWork) {
                hasUBW = true;
                break;
            }
        }

        if (!hasUBW) {
            player.addSkill(new UBWork());
            System.out.println("  获得了技能⌈无限剑制⌋");
        }else {
            boolean hasCaladbolg = false;
            for (Skill s : player.skills) {
                if (s instanceof Caladbolg) {
                    hasCaladbolg = true;
                    break;
                }
            }
            if (!hasCaladbolg) {
                player.addSkill(new Caladbolg());
                System.out.println("  获得了技能⌈螺旋剑⌋");
            } else {
                System.out.println("  '看来你都有了，那还说啥了，我把手送你吧'");
                player.atk += 40;
            }
        }
        return true;
    }
}

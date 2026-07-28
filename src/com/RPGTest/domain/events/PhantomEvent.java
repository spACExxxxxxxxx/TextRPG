package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import com.RPGTest.domain.Skill;
import com.RPGTest.service.SkillPool;
import java.util.ArrayList;
import java.util.Scanner;

public class PhantomEvent extends SpecialEvent {
    public PhantomEvent() {
        super("神秘老爷爷", 5, "---幻影老爷爷要传授你一门绝技");
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  '过了这么多年居然还有人造访老朽...'");
        System.out.println("  '也罢，就传你一招吧'");

        java.util.ArrayList<Skill> exclude = new ArrayList<>(player.skills);
        exclude.addAll(player.soldSkills);
        Skill special = SkillPool.getRandomSpecialSkill(exclude);
        if (special != null) {
            player.addSkill(special);
        } else {
            System.out.println("  但你已经掌握了全部绝学...");
        }
        return true;
    }
}

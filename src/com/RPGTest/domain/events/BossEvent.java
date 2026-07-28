package com.RPGTest.domain.events;

import com.RPGTest.domain.MyCharacter;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.Skills.boss.*;
import java.util.*;

public class BossEvent extends SpecialEvent {
    public BossEvent() {
        super("漫步灵庙", 3, "---与强敌战斗的记忆，透过其遗物浮现心头");
    }

    private static final Map<Integer, List<BossEntry>> BOSS_TIERS = new LinkedHashMap<>();
    static {
        BOSS_TIERS.put(15, List.of(
            new BossEntry("暗影龙",   "暗影吐息", "裂空龙爪", "龙威"),
            new BossEntry("三一结构体", "光剑裁决", "净世雨",   "光天颂圣歌")
        ));
        BOSS_TIERS.put(30, List.of(
            new BossEntry("风暴主",     "祭风",   "天风魔舞", "飓风眼"),
            new BossEntry("飞沙之剑圣", "断钢",   "泼沙",     "顽石金刚")
        ));
        BOSS_TIERS.put(45, List.of(
            new BossEntry("炎魔",   "炼狱",     "焚尽",       "耀斑"),
            new BossEntry("苍银帝", "苍之冕",   "冰殛暴风雪", "冰天百华葬")
        ));
    }

    @Override
    public boolean execute(MyCharacter player, Scanner sc) {
        System.out.println("\n  你来到一处平原。远处，漫步的巨大灵庙映入眼帘");
        System.out.println("  仿佛感应到了你，灵庙停下了动作，随后弯曲四肢，向你俯下身来");
        System.out.println("  你看着面前不再动弹的小山一样的灵庙");
        System.out.println("  你走上前去，推开了灵庙的门···");
        Set<String> owned = new HashSet<>();
        for (Skill s : player.skills) owned.add(s.name);

        List<Skill> offered = new ArrayList<>();
        for (var entry : BOSS_TIERS.entrySet()) {
            if (player.battleCount + 1 < entry.getKey()) continue;
            for (BossEntry boss : entry.getValue()) {
                boolean defeated = false;
                for (String sn : boss.skills()) {
                    if (owned.contains(sn)) { defeated = true; break; }
                }
                if (!defeated) continue;
                for (String sn : boss.skills()) {
                    if (!owned.contains(sn)) {
                        Skill s = createSkill(sn);
                        if (s != null && !offered.contains(s)) offered.add(s);
                    }
                }
            }
        }

        if (offered.isEmpty()) {
            System.out.println("\n  没有可追忆的东西");
            return true;
        }

        System.out.println("\n  追忆昔日的身影...");
        System.out.println("  选择一项未掌握的Boss技能：");
        for (int i = 0; i < offered.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + offered.get(i).name);
        }
        System.out.println("    0. 放弃");
        System.out.print("  请选择: ");

        int pick;
        try { pick = Integer.parseInt(sc.nextLine()); }
        catch (NumberFormatException e) { return true; }

        if (pick >= 1 && pick <= offered.size()) {
            player.addSkill(offered.get(pick - 1));
        }
        return true;
    }

    private Skill createSkill(String name) {
        return switch (name) {
            case "暗影吐息"   -> new DragonBreath();
            case "裂空龙爪"   -> new DragonClaw();
            case "龙威"       -> new DragonMight();
            case "光剑裁决"   -> new HolyJudgement();
            case "净世雨"     -> new HolyRain();
            case "光天颂圣歌" -> new HolyBlessing();
            case "祭风"       -> new WindCalling();
            case "天风魔舞"   -> new WindDance();
            case "飓风眼"     -> new WindEyes();
            case "断钢"       -> new EarthSword();
            case "泼沙"       -> new EarthThick();
            case "顽石金刚"   -> new EarthArmor();
            case "炼狱"       -> new FireInferno();
            case "焚尽"       -> new FireScorch();
            case "耀斑"       -> new FireFlame();
            case "苍之冕"     -> new IceLord();
            case "冰殛暴风雪" -> new IceStorm();
            case "冰天百华葬" -> new IceFlower();
            default -> null;
        };
    }

    private record BossEntry(String bossName, String s1, String s2, String s3) {
        List<String> skills() { return List.of(s1, s2, s3); }
    }
}

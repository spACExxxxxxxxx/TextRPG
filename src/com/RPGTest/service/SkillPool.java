package com.RPGTest.service;

import com.RPGTest.domain.ElementType;
import com.RPGTest.domain.Skill;
import com.RPGTest.domain.Skills.extral.Caladbolg;
import com.RPGTest.domain.Skills.normal.*;
import com.RPGTest.domain.Skills.special.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 技能池管理
 * 普通技能池：敌人可用，玩家可购买
 * 特色技能池：玩家专属，每10场获得
 */
public class SkillPool {

    // 普通技能池（敌人使用 + 玩家可购买）
    public static final List<Skill> normalPool = new ArrayList<>();
    // 特色技能池（玩家专属）
    public static final List<Skill> specialPool = new ArrayList<>();

    static {
        // === 普通技能池 ===
        normalPool.add(new AttackBreak());    // 破阵击
        normalPool.add(new AttackDual());     // 二连击
        normalPool.add(new AttackRapid());    // 拔刀斩
        normalPool.add(new AttackRegard());   // 盾击
        normalPool.add(new BloodAbsorb());    // 鲜血征收
        normalPool.add(new BloodCrazy());     // 沸血癫狂
        normalPool.add(new CurseOfLife());    // 生命诅咒 - 暗
        normalPool.add(new CurseOfMagic());   // 魔力清洗 - 光
        normalPool.add(new DanceOfSword());   // 剑舞
        normalPool.add(new DanceOfWar());     // 战舞
        normalPool.add(new DarkBall());       // 暗球 - 暗
        normalPool.add(new DarkSilence());    // 沉默禁令 - 暗
        normalPool.add(new DarkWave());       // 黑暗波动 - 暗
        normalPool.add(new EarthBreath());    // 盖亚之息 - 土
        normalPool.add(new EarthFailing());   // 天陨 - 土
        normalPool.add(new EarthQuake());     // 地震 - 土
        normalPool.add(new EarthSpike());     // 地刺 - 土
        normalPool.add(new FireBall());       // 火球术 - 火
        normalPool.add(new FirePhoenix());    // 真凰炎 - 火
        normalPool.add(new FireStorm());      // 烈焰风暴 - 火
        normalPool.add(new Healing());        // 治疗术 - 愈
        normalPool.add(new HealingRain());    // 天降甘露 - 愈
        normalPool.add(new IceAbsolute());    // 绝对零度 - 冰
        normalPool.add(new IceAge());         // 冰河时代 - 冰
        normalPool.add(new IceBlade());       // 冰枪 - 冰
        normalPool.add(new IronField());      // 圣域
        normalPool.add(new IronSkin());       // 铁壁盾防
        normalPool.add(new LightBolt());      // 雷击 - 光
        normalPool.add(new Lighting());       // 霆光闪 - 光
        normalPool.add(new LightingDance());  // 煌雷之天闪 - 光
        normalPool.add(new MagicMode());      // 祭品
        normalPool.add(new WindBlade());      // 风暴之刃 - 风
        normalPool.add(new WindGoing());      // 天狗风 - 风
        normalPool.add(new WindSlash());      // 风刃 - 风

        // === 特色技能池 ===
        specialPool.add(new ArgonCoin());     // 幻兽之龙炎 - 暗
        specialPool.add(new CraneWings());    // 鹤翼三连
        specialPool.add(new Excalibur());     // 咖喱棒 - 光
        specialPool.add(new GodHand());       // 十二试炼 - 愈
        specialPool.add(new MagicBullet());   // 魔弹 - 暗
        specialPool.add(new UBWork());        // 无限剑制
        specialPool.add(new WindField());     // 风王结界 - 风
    }

    private static final Random random = new Random();

    /**
     * 根据元素筛选普通技能
     */
    public static List<Skill> getSkillsByElement(char element) {
        List<Skill> result = new ArrayList<>();
        for (Skill s : normalPool) {
            if (s.element == element) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * 随机获取N个普通技能（用于商店展示）
     * 排除玩家已拥有的技能
     */
    public static List<Skill> getRandomNormalSkills(int count, List<Skill> excludeSkills) {
        List<Skill> available = new ArrayList<>(normalPool);
        available.removeAll(excludeSkills);
        Collections.shuffle(available, random);

        List<Skill> result = new ArrayList<>();
        for (int i = 0; i < Math.min(count, available.size()); i++) {
            result.add(available.get(i));
        }
        return result;
    }

    /**
     * 随机获取1个特色技能
     * 排除玩家已拥有的技能
     */
    public static Skill getRandomSpecialSkill(List<Skill> excludeSkills) {
        List<Skill> available = new ArrayList<>(specialPool);
        available.removeAll(excludeSkills);
        if (available.isEmpty()) {
            return null;
        }
        return available.get(random.nextInt(available.size()));
    }

    /**
     * 为敌人随机获取2个技能：1个本系 + 1个HEAL/NONE
     */
    public static ArrayList<Skill> getRandomEnemySkills(char element, int battleCount) {
        List<Skill> matchingSkills = getSkillsByElement(element);
        List<Skill> healNoneSkills = new ArrayList<>();
        healNoneSkills.addAll(getSkillsByElement(ElementType.HEAL));
        healNoneSkills.addAll(getSkillsByElement(ElementType.NONE));

        Skill selectedElementSkill = weightedRandomByPrice(matchingSkills, battleCount);
        Skill selectedHealNoneSkill = weightedRandomByPrice(healNoneSkills, battleCount);

        ArrayList<Skill> result = new ArrayList<>();
        if (selectedElementSkill != null) result.add(selectedElementSkill);
        if (selectedHealNoneSkill != null) result.add(selectedHealNoneSkill);
        return result;
    }

    /**
     * 根据技能 price 和战斗场次加权随机
     * 第1场: price2=0.7, price3=0.2, price4=0.1
     * 第40场: price2=0.05, price3=0.35, price4=0.6
     */
    private static Skill weightedRandomByPrice(List<Skill> skills, int battleCount) {
        if (skills == null || skills.isEmpty()) return null;

        // 按 price 分组计数，用于归一化
        java.util.Map<Integer, Integer> priceCounts = new java.util.HashMap<>();
        for (Skill s : skills) {
            priceCounts.merge(s.price, 1, Integer::sum);
        }

        double totalWeight = 0.0;
        double[] weights = new double[skills.size()];
        double t = Math.min(battleCount / 30.0, 1.0);

        for (int i = 0; i < skills.size(); i++) {
            int price = skills.get(i).price;
            // price 目标概率
            double priceProb = switch (price) {
                case 2 -> 0.70 - 0.65 * t;
                case 3 -> 0.20 + 0.15 * t;
                case 4 -> 0.10 + 0.50 * t;
                default -> 0.05;
            };
            // 归一化：均分给同 price 的每个技能
            int samePriceCount = priceCounts.getOrDefault(price, 1);
            weights[i] = priceProb / samePriceCount;
            totalWeight += weights[i];
        }

        double rand = random.nextDouble() * totalWeight;
        double cumulative = 0.0;
        for (int i = 0; i < skills.size(); i++) {
            cumulative += weights[i];
            if (rand < cumulative) return skills.get(i);
        }
        return skills.get(skills.size() - 1);
    }
}

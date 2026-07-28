package com.RPGTest.domain;

/**
 * Boss基类：隔一回合行动
 */
public class Boss extends Enemy {
    private boolean actThisTurn = true;
    public boolean isFinalBoss = false;
    public int damageDealt = 0;   // 玩家对 Boss 累计造成的伤害

    public Boss(String firstname,String lastname, int hp, int mp, int atk, int def) {
        super();
        this.firstName = firstname;
        this.lastName = lastname;
        this.isPlayer = false;
        this.hp = hp;
        this.maxHp = hp;
        this.mp = mp;
        this.maxMp = mp;
        this.atk = atk;
        this.def = def;
    }

    /**
     * 敌人回合调用。交替返回 true(行动) / false(休息)
     */
    public boolean takeAction() {
        boolean act = actThisTurn;
        actThisTurn = !actThisTurn;
        if (!act) {
            System.out.println("「" + firstName + "」正在蓄力...");
        }
        return act;
    }

    @Override
    public void takePhysicalDamage(int damage) {
        int hpBefore = this.hp;
        super.takePhysicalDamage(damage);
        damageDealt += (hpBefore - this.hp);
    }

    @Override
    public void takeMagicalDamage(int damage) {
        int hpBefore = this.hp;
        super.takeMagicalDamage(damage);
        damageDealt += (hpBefore - this.hp);
    }

    /**
     * 脚本化 Boss：返回 true 表示走技能脚本，不走随机 AI
     */
    public boolean hasScript() { return false; }

    public boolean scriptedSkip = false; // 脚本化 Boss 当前回合是否跳过（预告用）

    public Skill getScriptedSkill(com.RPGTest.domain.Character target) { return null; }

    /**
     * 击败后可选为奖励的技能列表
     */
    public java.util.ArrayList<Skill> getRewardSkills() {
        return skills;
    }

    /**
     * Boss 战的眷属小怪（默认无）
     */
    public java.util.List<Enemy> getMinions(int battleCount) {
        return new java.util.ArrayList<>();
    }
}

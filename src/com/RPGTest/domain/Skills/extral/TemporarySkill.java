package com.RPGTest.domain.Skills.extral;

import com.RPGTest.domain.Character;
import com.RPGTest.domain.Skill;

/**
 * 临时技能父类——统一处理：自动设置 isTemporary、MP扣费、使用后自毁
 * 子类只需实现 execute() 写伤害/治疗效果
 */
public abstract class TemporarySkill extends Skill {

    public TemporarySkill(String name, double power, int cost, char element) {
        super(name, power, cost, element, true);
        this.isTemporary = true;
    }

    @Override
    public void use(Character owner, Character target) {
        if (!owner.mpCost(this.cost)) {
            System.out.println("释放失败，MP不足！");
            return;
        }
        // 子类的具体效果
        execute(owner, target);
        // 使用后自毁
        if (owner.skills.contains(this)) {
            owner.skills.remove(this);
            System.out.println("「" + this.name + "」已被使用");
        }
    }

    /** 子类只需实现这个——写伤害/治疗效果，不用管 MP 扣费和自毁 */
    protected abstract void execute(Character owner, Character target);

    /**
     * 向角色添加临时技能（已有同名实例则跳过）
     */
    public static void grant(Character owner, TemporarySkill skill) {
        for (Skill s : owner.skills) {
            if (s.getClass() == skill.getClass()) {
                return;  // 已有，不重复添加
            }
        }
        owner.skills.add(skill);
        System.out.println("  解锁临时技能：「" + skill.name + "」！");
    }
}

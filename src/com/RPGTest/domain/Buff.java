package com.RPGTest.domain;

/**
 * 战斗中Buff（增益/减益）
 * value = 百分比换算后的实际属性变化量（用于恢复）
 * percent = 原始百分比（用于显示）
 * turnsLeft = -1 表示整场持续（战后清除）
 */
public class Buff {

    public BuffType type;
    public int value;        // 实际效果量（用于恢复属性）
    public int percent;      // 原始百分比（用于显示）
    public int turnsLeft;    // 剩余回合，-1 = 整场持续

    public Buff(BuffType type, int value, int percent, int turnsLeft) {
        this.type = type;
        this.value = value;
        this.percent = percent;
        this.turnsLeft = turnsLeft;
    }

    /**
     * 回合结束时调用，返回 true 表示已过期应移除
     */
    public boolean tick() {
        if (turnsLeft == -1) return false;   // 整场持续，不过期
        turnsLeft--;
        return turnsLeft <= 0;
    }
}

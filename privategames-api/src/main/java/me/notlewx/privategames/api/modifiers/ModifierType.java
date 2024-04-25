package me.notlewx.privategames.api.modifiers;

public enum ModifierType {
    ONE_HIT_ONE_KILL("oneHitOneKill", ModifierValue.BOOLEAN),
    HEALTH_BUFF("healthBuffLevel", ModifierValue.INTEGER),
    LOW_GRAVITY("lowGravity", ModifierValue.BOOLEAN),
    SPEED("speed", ModifierValue.BOOLEAN),
    RESPAWN_TIME("respawnEventTime", ModifierValue.INTEGER),
    EVENTS_TIME("eventsTime", ModifierValue.INTEGER),
    NO_EMERALDS("noEmeralds", ModifierValue.BOOLEAN),
    NO_DIAMONDS("noDiamonds", ModifierValue.BOOLEAN),
    ALLOW_MAP_BREAK("allowMapBreak", ModifierValue.BOOLEAN),
    INSTANT_BED_BREAK("bedInstaBreak", ModifierValue.BOOLEAN),
    MAX_TEAM_UPGRADES("maxTeamUpgrades", ModifierValue.BOOLEAN);

    public enum ModifierValue {
        BOOLEAN,
        INTEGER;
    }

    private final String name;
    private final ModifierValue value;

    ModifierType(String name, ModifierValue value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public ModifierValue getValue() {
        return value;
    }
}

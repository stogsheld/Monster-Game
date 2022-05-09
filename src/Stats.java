public class Stats {

    private String name;
    private int health;
    private int gold;
    private int healthPotions;
    private int level;
    private int exp;

    public Stats(String name, int health, int gold, int healthPotions, int level, int exp) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.healthPotions = healthPotions;
        this.level = level;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealthPotions() {
        return healthPotions;
    }

    public void setHealthPotions(int healthPotions) {
        this.healthPotions = healthPotions;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}

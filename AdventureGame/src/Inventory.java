import java.util.SplittableRandom;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private String food;
    private String firewood;
    private String water;

    public Inventory() {
        this.weapon = new Weapon(-1, "Yumruk", 0, 0);
        this.armor = new Armor(-1, "Paçavra", 0, 0);
        this.food = " ";
        this.firewood = " ";
        this.water = " ";
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFirewood() {
        return firewood;
    }

    public void setFirewood(String firewood) {
        this.firewood = firewood;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }
}



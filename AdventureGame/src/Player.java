import java.util.Scanner;

public class Player {

    private Inventory inventory;
    private int damage;
    private int health;
    private int orjinalHealth;
    private int money;
    private String name;
    private String charName;


    Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOrjinalHealth() {
        return orjinalHealth;
    }

    public void setOrjinalHealth(int orjinalHealth) {
        this.orjinalHealth = orjinalHealth;
    }

    public void selectChar() {

        GameChar[] charlist = {new Samurai(), new Archer(), new Knight()};

        System.out.println("Karakterler");
        System.out.println("------------------------------------------------------");
        for (GameChar gameChar : charlist) {
            System.out.println("İd:" + gameChar.getId() +
                    "\t" + "Karakter:" + gameChar.getName() +
                    "\t" + "Hasar:" + gameChar.getDamage() +
                    "\t" + "Sağlık:" + gameChar.getHealth() +
                    "\t" + "Para:" + gameChar.getMoney());
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("");
        System.out.print("Karakter seçiniz: ");
        int select = input.nextInt();


        switch (select) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Samurai());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("Seçtiğiniz karakter: " + this.getCharName() +
                ", Hasar:" + this.getDamage() +
                " ,Sağlık: " + this.getHealth() +
                ", Para: " + this.getMoney());

    }

    public void initPlayer(GameChar gameChar) {
        this.setCharName(gameChar.getName());
        this.setHealth(gameChar.getHealth());
        this.setOrjinalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setDamage(gameChar.getDamage());
    }

    public void printInfo() {
        System.out.println(
                "Silahınız: " + this.getInventory().getWeapon().getName() +
                        ", Zırhınız: " + this.getInventory().getArmor().getName() +
                        ", Engelleme:" + this.getInventory().getArmor().getBlock() +
                        ", Hasarınız:" + this.getTotalDamage() +
                        ", Sağlık: " + this.getHealth() +
                        ", Para: " + this.getMoney());
    }


}
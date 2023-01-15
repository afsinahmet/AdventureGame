import java.util.Random;
import java.util.Scanner;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;


    Scanner input = new Scanner(System.in);

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;

    }


    @Override
    public boolean onLocation() {
        int obsNumber = randomObstacleNumber();

        System.out.println("Şu an buradasınız: " + getName());
        System.out.println("Dikkatli ol,burada " + obsNumber + " tane " + getObstacle().getName() + " yaşıyor !");
        System.out.print("<S>avaş vaya <K>aç: ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S")) {
            System.out.println("Savaş işlemleri olacak! ");
            if (combat(obsNumber)) {
                System.out.println(this.getName() + " tüm düşmanları yendiniz! ");
                return true;
            }
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz!");
            return false;
        }


        return true;
    }

    public boolean combat(int obsNumber) {

        for (int i = 1; i <= obsNumber; i++) {

            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealth());
            playerStats();
            obstacleStats(i);

            double a = Math.random();
            if (a >= 0.5) {
                System.out.println("İlk vuruş hakkı sizin!");

                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                    System.out.print("<V>ur veya <K>aç: ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println("Siz vurdunuz!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu !");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();

                        }

                    } else {
                        return false;
                    }
                }
            } else {
                System.out.println("İlk vuruş hakkı canavarın!");

                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                    System.out.print("<V>ur veya <K>aç: ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println();
                        System.out.println("Canavar size vurdu !");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                        if (this.getPlayer().getHealth() > 0) {
                            System.out.println("Siz vurdunuz!");
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                        }


                    } else {
                        return false;
                    }
                }


            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth() && this.getName().equals("Maden")) {
                System.out.println("Düşmanı yendiniz!");
                awardGenerator();


            } else if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz!");
                System.out.println(this.getObstacle().getAward() + " para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());

                locationAwards();

            } else {
                return false;
            }
        }

        return true;
    }


    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı: " + this.getObstacle().getHealth());
        System.out.println("-------");

    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri: ");
        System.out.println("---------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println();

    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName() + " Değerleri: ");
        System.out.println("---------------------");
        System.out.println("Sağlık: " + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Ödülü: " + this.getObstacle().getAward());
        System.out.println();
    }

    public void locationAwards() {
        if (this.getName().equals("Mağara")) {
            this.getPlayer().getInventory().setFood(this.award);
        }
        if (this.getName().equals("Orman")) {
            this.getPlayer().getInventory().setFirewood(this.award);
        }
        if (this.getName().equals("Nehir")) {
            this.getPlayer().getInventory().setWater(this.award);
        }

    }

    public void awardGenerator() {
        int number = (int) (Math.random() * 100);
        if (number < 15) {
            int number2 = (int) (Math.random() * 100);
            if (number2 < 20) {
                System.out.println("Tebrikler, Tüfek kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));

            } else if (number2 > 20 && number2 < 50) {
                System.out.println("Tebrikler, Kılıç kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));

            } else {
                System.out.println("Tebrikler, tabanca kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));

            }
        } else if (number > 15 && number < 30) {
            int number2 = (int) (Math.random() * 100);
            if (number2 < 20) {
                System.out.println("Tebrikler, Ağır Zırh kazandınız");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));

            } else if (number2 > 20 && number2 < 50) {
                System.out.println("Tebrikler, Orta Zırh kazandınız");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));

            } else {
                System.out.println("Tebrikler, Hafif Zırh kazandınız");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));

            }

        } else if (number > 30 && number < 55) {
            int number2 = (int) (Math.random() * 100);
            if (number2 < 20) {
                System.out.println("Tebrikler, 10 para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else if (number2 > 20 && number2 < 50) {
                System.out.println("Tebrikler, 5 para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                System.out.println("Tebrikler, 1 para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());

            }

        } else {
            System.out.println("Bu canavardan eşya ya da para düşmedi!");
        }
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}

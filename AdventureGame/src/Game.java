import java.util.Scanner;

public class Game {
    private Player player;
    private Location location;


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void start() {
        Scanner input = new Scanner(System.in);


        System.out.println("Macera oyununa hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = input.nextLine();
        this.player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " bu karanlık adaya hoşgeldiniz !");
        System.out.println("Lütfen bir karakter seçiniz ! ");
        System.out.println("------------------------------------------------------");
        player.selectChar();


        Location location = null;


        while (true) {

            player.printInfo();

            System.out.println();
            System.out.println("########## Bölgeler ##########");
            System.out.println();
            System.out.println("1-Güvenli Ev ");
            System.out.println("2-Mağaza");
            System.out.println("3-Mağara --> Ödül<Yemek>, dikkatli ol karşına zombi çıkabilir!");
            System.out.println("4-Orman--> Ödül<Odun>, dikkatli ol karşına vampir çıkabilir!");
            System.out.println("5-Nehir--> Ödül<Su>, dikkatli ol karşına ayı çıkabilir!");
            System.out.println("6-Maden --> Ödül<Hediye İtem >, dikkatli ol karşına yılan çıkabilir");
            System.out.println("0-Oyunu Sonlandır!");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz:");
            int selectLoc = input.nextInt();


            if (isWin() && selectLoc == 1) {
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("TEBRİK EDERİZ !!");
                System.out.println("Oyunu kazandınız !!");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                break;
            }


            switch (selectLoc) {

                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new Toolstore(player);
                    break;
                case 3:
                    if (this.player.getInventory().getFood().equals("Yemek")) {
                        System.out.println("Bu bölgedeki tüm düşmanları öldürdünüz!!");
                        System.out.println("Buraya tekrar giremezsiniz! Sizi güvenli eve yönlendiriyorum");
                        location = new SafeHouse(player);
                        break;
                    } else {
                        location = new Cave(player);
                        break;
                    }

                case 4:
                    if (this.player.getInventory().getFirewood().equals("Odun")) {
                        System.out.println("Bu bölgedeki tüm düşmanları öldürdünüz!!");
                        System.out.println("Buraya tekrar giremezsiniz! Sizi güvenli eve yönlendiriyorum");
                        location = new SafeHouse(player);
                        break;
                    } else {
                        location = new Forest(player);
                        break;
                    }
                case 5:
                    if (this.player.getInventory().getWater().equals("Su")) {
                        System.out.println("Bu bölgedeki tüm düşmanları öldürdünüz!!");
                        System.out.println("Buraya tekrar giremezsiniz! Sizi güvenli eve yönlendiriyorum");
                        location = new SafeHouse(player);
                        break;
                    } else {
                        location = new River(player);
                        break;
                    }
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    location = new SafeHouse(player);
                    System.out.println("Lütfen geçerli bir ifade giriniz!");
            }
            if (location == null) {
                System.out.println("Bu karanlık adadan çabuk vazgeçtin!");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("GAME OVER!");
                break;
            }

        }


    }

    public boolean isWin() {
        if (this.player.getInventory().getFood().equals("Yemek")
                && this.player.getInventory().getFirewood().equals("Odun")
                && this.player.getInventory().getWater().equals("Su")) {

            return true;
        }
        return false;
    }

    public boolean isTakeAward() {
        if (this.player.getInventory().getFood().equals("Yemek")
                || this.player.getInventory().getFirewood().equals("Odun")
                || this.player.getInventory().getWater().equals("Su")) {
            System.out.println("Bu bölgedeki tüm canavarları öldürdünüz!");
            System.out.println("Bu bölgeye tekrar giremezsiniz! Sizi güvenli eve yönlendiriyorum. ");
            return false;
        }
        return true;
    }

}

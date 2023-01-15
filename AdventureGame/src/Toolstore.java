public class Toolstore extends NormalLoc {

    public Toolstore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {

        System.out.println("------ Mağazaya hoşgeldiniz! ------");

        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1-Silahlar");
            System.out.println("2-Zırhlar");
            System.out.println("3-Çıkış");
            System.out.print("Seçiminiz: ");
            int selectCase = input.nextInt();

            while (selectCase < 1 || selectCase > 3) {
                System.out.println("Geçersiz değer, tekrar giriniz!");
                selectCase = input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Bir daha bekleriz!");
                    showMenu = false;
                    return true;
            }
        }
        return true;
    }


    public void printWeapon() {
        System.out.println("------Silahlar------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + "- "
                    + w.getName() + " <Para: " + w.getPrice() + " <Hasar: " + w.getDamage());
        }
        System.out.println("0 - Çıkış yap");

    }

    public void printArmor() {
        System.out.println("------Zırhlar------");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + "- "
                    + a.getName() + " <Para: " + a.getPrice() + " <Engelleme: " + a.getBlock());
        }
        System.out.println("0 - Çıkış yap");

    }

    public void buyArmor() {
        System.out.println("Bir zırh seçiniz: ");
        int selectArmor = input.nextInt();
        while (selectArmor < 0 || selectArmor > 3) {
            System.out.println("Geçersiz  değer, tekrar giriniz!");
            selectArmor = input.nextInt();
        }
        if (selectArmor != 0) {
            Armor selectedArmor = Armor.getArmorObjById(selectArmor);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Bakiyeniz yetersiz! ");
                } else {
                    System.out.println(selectedArmor.getName() + " zırhını satın aldınız");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("Kalan Paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }
        }

    }


    public void buyWeapon() {
        System.out.print("Bir silah seçiniz: ");
        int selectWeapon = input.nextInt();
        while (selectWeapon < 0 || selectWeapon > 3) {
            System.out.println("Geçersiz değer, tekrar giriniz!");
            selectWeapon = input.nextInt();
        }
        if (selectWeapon != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeapon);

            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Bakiyeniz yetersiz! ");
                } else {

                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız!  ");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }

            }
        }
    }

}






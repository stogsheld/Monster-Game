import java.util.Random;
import java.util.Scanner;

public class PlayGame {

    public PlayGame(int floorLevel, Stats player) {
        if (floorLevel == 1) {
            System.out.println("Get as far as you can!");
        } else {
            System.out.println("***************************************");
            System.out.println("You are on level " + floorLevel + ".");
            System.out.println("***************************************");
        }
        chooseLevel(floorLevel, player);
    }

    private void chooseLevel(int floorLevel, Stats player) {
        Random rand = new Random();
        int upperbound = 10;
        int playerLevelChooser = rand.nextInt(upperbound);
        if (playerLevelChooser < 5) {
            playJungleLevel(floorLevel, 2, player);
        } else {
            playJungleLevel(floorLevel, 2, player);
        }
    }

    public void playJungleLevel(int floorLevel, int startingTile, Stats player) {
        int exitTile = setExitTile(startingTile);
        navigateLevel(floorLevel, startingTile, exitTile, player);
    }

    private void navigateLevel(int floorLevel, int currentPosition, int exitTile, Stats player) {
        if (currentPosition == exitTile) {
            displayMap(currentPosition);
            runExitBattle(floorLevel, player);
        } else {
            Scanner scn = new Scanner(System.in);
            System.out.println();
            System.out.println();
            displayMap(currentPosition);
            System.out.println("Your current position is " + currentPosition);
            System.out.println("Pick a direction to go in.");
            System.out.println("1. Up");
            System.out.println("2. Down");
            System.out.println("3. Left");
            System.out.println("4. Right");
            System.out.println("5. Use a health potion (restores 25HP).");
            String choice = scn.nextLine();
            switch (choice) {
                case "1":
                    if (currentPosition > 12) {
                        System.out.println();
                        System.out.println("You can't move in this direction.");
                        break;
                    }
                    currentPosition = currentPosition + 4;
                    generateEvent(floorLevel, currentPosition, exitTile, player);
                    break;
                case "2":
                    if (currentPosition < 5) {
                        System.out.println();
                        System.out.println("You can't move in this direction.");
                        break;
                    }
                    currentPosition = currentPosition - 4;
                    generateEvent(floorLevel, currentPosition, exitTile, player);
                    break;
                case "3":
                    if ((currentPosition - 1) % 4 == 0) {
                        System.out.println();
                        System.out.println("You can't move in this direction.");
                        break;
                    }
                    currentPosition = currentPosition - 1;
                    generateEvent(floorLevel, currentPosition, exitTile, player);
                    break;
                case "4":
                    if (currentPosition % 4 == 0) {
                        System.out.println();
                        System.out.println("You can't move in this direction.");
                        break;
                    }
                    currentPosition = currentPosition + 1;
                    generateEvent(floorLevel, currentPosition, exitTile, player);
                    break;
                case "5":
                    if (player.getHealthPotions() > 0) {
                        player.setHealthPotions(player.getHealthPotions() - 1);
                        player.setHealth(player.getHealth() + 25);
                        System.out.println();
                        System.out.println("Health restored! You now have " + player.getHealth() + "HP.");
                    } else {
                        System.out.println("No potions available!");
                    }
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid option, please try again.");
            }
            scn.close();
        }
        navigateLevel(floorLevel, currentPosition, exitTile, player);
    }

    private void generateEvent(int floorLevel, int currentPosition, int exitTile, Stats player) {
        Random eventGenerator = new Random();
        int upperbound = 10;
        int eventGen = eventGenerator.nextInt(upperbound);
        if (eventGen < 6) {
            easyFight(floorLevel, currentPosition, exitTile, player);
        } else if (eventGen < 8) {
            harderFight(floorLevel, currentPosition, exitTile, player);
        } else if (eventGen < 9) {
            lootRoom(floorLevel, currentPosition, exitTile, player);
        } else {
            shopkeeperRoom(floorLevel, currentPosition, exitTile, player);
        }
        navigateLevel(floorLevel, currentPosition, exitTile, player);
    }

    private void shopkeeperRoom(int floorLevel, int currentPosition, int exitTile, Stats player) {
        System.out.println("Shopkeeper found!");

        navigateLevel(floorLevel, currentPosition, exitTile, player);
    }

    private void lootRoom(int floorLevel, int currentPosition, int exitTile, Stats player) {
        System.out.println("Loot room!");

        navigateLevel(floorLevel, currentPosition, exitTile, player);
    }

    private void harderFight(int floorLevel, int currentPosition, int exitTile, Stats player) {
        GenerateMonster spider = new GenerateMonster((floorLevel * 25), (floorLevel * 12), "Spider");
        System.out.println("You come across a " + spider.getName() + "!");
        handleFight(player, spider.getHealth(), spider.getDamage(), spider.getName());
        System.out.println(spider.getName() + " defeated!");
        player.setExp(player.getExp() + (floorLevel * 50));
        player.setGold(player.getGold() + (floorLevel * 10));
        System.out.println("You gained " + (floorLevel * 50) + " exp!");
        System.out.println("You found " + (floorLevel * 10) + " gold!");
        navigateLevel(floorLevel, currentPosition, exitTile, player);
    }

    private void easyFight(int floorLevel, int currentPosition, int exitTile, Stats player) {
        GenerateMonster slime = new GenerateMonster((floorLevel * 15), (floorLevel * 10), "Slime");
        System.out.println("You come across a " + slime.getName() + "!");
        handleFight(player, slime.getHealth(), slime.getDamage(), slime.getName());
        System.out.println(slime.getName() + " defeated!");
        player.setExp(player.getExp() + (floorLevel * 50));
        player.setGold(player.getGold() + (floorLevel * 10));
        System.out.println("You gained " + (floorLevel * 25) + " exp!");
        System.out.println("You found " + (floorLevel * 5) + " gold!");
        navigateLevel(floorLevel, currentPosition, exitTile, player);
    }

    private void handleFight(Stats player, int monsterHealth, int slimeDamage, String monsterName) {
        Scanner fightScanner = new Scanner(System.in);
        while (monsterHealth > 0 && player.getHealth() > 0) {
            System.out.println("Your health = " + player.getHealth());
            System.out.println(monsterName + "'s health = " + monsterHealth);
            System.out.println("What will you do?");
            System.out.println("1. Slash at the monster (Deals less damage, but is more accurate).");
            System.out.println("2. Uppercut (Deals more damage, but is less accurate).");
            System.out.println("3. Use a health potion.");
            Random rand = new Random();
            int upperbound = 100;
            int playerLevelChooser = rand.nextInt(upperbound);
            String fightChoice = fightScanner.nextLine();
            switch (fightChoice) {
                case "1":
                    if (playerLevelChooser < 90) {
                        System.out.println("Monster hit!");
                        monsterHealth -= 10;
                    } else {
                        System.out.println("Missed!");
                    }
                    player.setHealth(player.getHealth() - slimeDamage);
                    break;
                case "2":
                    if (playerLevelChooser < 75) {
                        System.out.println("Monster hit!");
                        monsterHealth -= 15;
                        ;
                    } else {
                        System.out.println("Missed!");
                    }
                    player.setHealth(player.getHealth() - slimeDamage);
                    break;
                case "3":
                    if (player.getHealthPotions() > 0) {
                        player.setHealthPotions(player.getHealthPotions() - 1);
                        player.setHealth(player.getHealth() + 25);
                        System.out.println("Health potion used! Your health is now " + player.getHealth());
                    } else {
                        System.out.println("No potions available!");
                    }
                    break;
                default:
                    System.out.println("Invalid option provided!");
                    break;

            }
        }
        fightScanner.close();
    }

    private void runExitBattle(int floorLevel, Stats player) {
        System.out.println("Exit battle run here");
        floorLevel++;
        new PlayGame(floorLevel, player);
    }

    private int setExitTile(int startingTile) {
        Random exitTileRand = new Random();
        int upperbound = 15;
        int exit = exitTileRand.nextInt(upperbound);
        if (exit == startingTile) {
            return exit - 1;
        } else {
            return exit;
        }
    }

    private void displayMap(int currentPosition) {
        String[] map = new String[16];
        for (int i = 0; i <= 15; i++) {
            if (i + 1 == currentPosition) {
                map[i] = "X";
            } else {
                map[i] = "-";
            }
        }

        System.out.println(map[12] + " " + map[13] + " " + map[14] + " " + map[15]);
        System.out.println(map[8] + " " + map[9] + " " + map[10] + " " + map[11]);
        System.out.println(map[4] + " " + map[5] + " " + map[6] + " " + map[7]);
        System.out.println(map[0] + " " + map[1] + " " + map[2] + " " + map[3]);
        System.out.println();
    }
}

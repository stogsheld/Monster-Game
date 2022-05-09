import java.util.Random;
import java.util.Scanner;

public class PlayGame {

    public PlayGame(int floorLevel, String playerName, int playerHealth, int playerGold,
                    int playerHealthPotions, int playerLevel, int playerExp) {
        if (floorLevel == 1) {
            System.out.println("Get as far as you can!");
        } else {
            System.out.println("***************************************");
            System.out.println("You are on level " + floorLevel + ".");
            System.out.println("***************************************");
        }
        chooseLevel(floorLevel, playerName, playerHealth, playerGold, playerHealthPotions, playerLevel, playerExp);
    }

    private void chooseLevel(int floorLevel, String playerName, int playerHealth, int playerGold,
                             int playerHealthPotions, int playerLevel, int playerExp) {
        Random rand = new Random();
        int upperbound = 10;
        int playerLevelChooser = rand.nextInt(upperbound);
        if (playerLevelChooser < 5) {
            playJungleLevel(floorLevel, 2, playerName, playerHealth, playerGold, playerHealthPotions,
                    playerLevel, playerExp);
        } else {
            playJungleLevel(floorLevel, 2, playerName, playerHealth, playerGold, playerHealthPotions,
                    playerLevel, playerExp);
        }
    }

    public void playJungleLevel(int floorLevel, int startingTile, String playerName, int playerHealth,
                                int playerGold, int playerHealthPotions, int playerLevel, int playerExp) {
        int exitTile = setExitTile(startingTile);
        navigateLevel(floorLevel, startingTile, exitTile, playerName, playerHealth, playerGold, playerHealthPotions,
                playerLevel, playerExp);
    }

    private void navigateLevel(int floorLevel, int currentPosition, int exitTile, String playerName, int playerHealth,
                               int playerGold, int playerHealthPotions, int playerLevel, int playerExp) {
        if (currentPosition == exitTile) {
            displayMap(currentPosition);
            runExitBattle(floorLevel, playerName, playerHealth, playerGold, playerHealthPotions,
                    playerLevel, playerExp);
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
                    generateEvent(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold,
                            playerHealthPotions, playerLevel, playerExp);
                    break;
                case "2":
                    if (currentPosition < 5) {
                        System.out.println();
                        System.out.println("You can't move in this direction.");
                        break;
                    }
                    currentPosition = currentPosition - 4;
                    generateEvent(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold,
                            playerHealthPotions, playerLevel, playerExp);
                    break;
                case "3":
                    if ((currentPosition - 1) % 4 == 0) {
                        System.out.println();
                        System.out.println("You can't move in this direction.");
                        break;
                    }
                    currentPosition = currentPosition - 1;
                    generateEvent(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold,
                            playerHealthPotions, playerLevel, playerExp);
                    break;
                case "4":
                    if (currentPosition % 4 == 0) {
                        System.out.println();
                        System.out.println("You can't move in this direction.");
                        break;
                    }
                    currentPosition = currentPosition + 1;
                    generateEvent(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold,
                            playerHealthPotions, playerLevel, playerExp);
                    break;
                case "5":
                    if (playerHealthPotions > 0) {
                        playerHealthPotions--;
                        playerHealth += 25;
                        System.out.println();
                        System.out.println("Health restored! You now have " + playerHealth + "HP.");
                    } else {
                        System.out.println("No potions available!");
                    }
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid option, please try again.");
            }
        }
        navigateLevel(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold, playerHealthPotions,
                playerLevel, playerExp);
    }

    private void generateEvent(int floorLevel, int currentPosition, int exitTile, String playerName, int playerHealth,
                               int playerGold, int playerHealthPotions, int playerLevel, int playerExp) {
        Random eventGenerator = new Random();
        int upperbound = 10;
        int eventGen = eventGenerator.nextInt(upperbound);
        if (eventGen < 6) {
            easyFight(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold,
                    playerHealthPotions, playerLevel, playerExp);
        } else if (eventGen < 8) {
            harderFight(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold,
                    playerHealthPotions, playerLevel, playerExp);
        } else if (eventGen < 9) {
            lootRoom(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold, playerHealthPotions,
                    playerLevel, playerExp);
        } else {
            shopkeeperRoom(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold,
                    playerHealthPotions, playerLevel, playerExp);
        }
        navigateLevel(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold, playerHealthPotions,
                playerLevel, playerExp);
    }

    private void shopkeeperRoom(int floorLevel, int currentPosition, int exitTile, String playerName,
                                int playerHealth, int playerGold, int playerHealthPotions,
                                int playerLevel, int playerExp) {
        System.out.println("Shopkeeper found!");

        navigateLevel(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold, playerHealthPotions,
                playerLevel, playerExp);
    }

    private void lootRoom(int floorLevel, int currentPosition, int exitTile, String playerName,
                          int playerHealth, int playerGold, int playerHealthPotions, int playerLevel, int playerExp) {
        System.out.println("Loot room!");

        navigateLevel(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold, playerHealthPotions,
                playerLevel, playerExp);
    }

    private void harderFight(int floorLevel, int currentPosition, int exitTile, String playerName, int playerHealth,
                             int playerGold, int playerHealthPotions, int playerLevel, int playerExp) {
        GenerateMonster spider = new GenerateMonster((floorLevel * 25), (floorLevel * 12), "Spider");
        System.out.println("You come across a " + spider.getName() + "!");
        handleFight(playerHealth, playerHealthPotions, spider.getHealth(), spider.getDamage(), spider.getName());
        System.out.println(spider.getName() + " defeated!");
        playerExp += (floorLevel * 50);
        playerGold += (floorLevel * 10);
        System.out.println("You gained " + (floorLevel * 50) + " exp!");
        System.out.println("You found " + (floorLevel * 10) + " gold!");
        navigateLevel(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold, playerHealthPotions,
                playerLevel, playerExp);
    }

    private void easyFight(int floorLevel, int currentPosition, int exitTile, String playerName, int playerHealth,
                           int playerGold, int playerHealthPotions, int playerLevel, int playerExp) {
        GenerateMonster slime = new GenerateMonster((floorLevel * 15), (floorLevel * 10), "Slime");
        System.out.println("You come across a " + slime.getName() + "!");
        handleFight(playerHealth, playerHealthPotions, slime.getHealth(), slime.getDamage(), slime.getName());
        System.out.println(slime.getName() + " defeated!");
        playerExp += (floorLevel * 25);
        playerGold += (floorLevel * 5);
        System.out.println("You gained " + (floorLevel * 25) + " exp!");
        System.out.println("You found " + (floorLevel * 5) + " gold!");
        navigateLevel(floorLevel, currentPosition, exitTile, playerName, playerHealth, playerGold, playerHealthPotions,
                playerLevel, playerExp);
    }

    private void handleFight(int playerHealth, int playerHealthPotions,
                             int monsterHealth, int slimeDamage, String monsterName) {
        while (monsterHealth > 0 && playerHealth > 0) {
            Scanner fightScanner = new Scanner(System.in);
            System.out.println("Your health = " + playerHealth);
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
                    playerHealth -= slimeDamage;
                    break;
                case "2":
                    if (playerLevelChooser < 75) {
                        System.out.println("Monster hit!");
                        monsterHealth -= 15;
                        ;
                    } else {
                        System.out.println("Missed!");
                    }
                    playerHealth -= slimeDamage;
                    break;
                case "3":
                    if (playerHealthPotions > 0) {
                        playerHealthPotions--;
                        playerHealth += 25;
                        System.out.println("Health potion used! Your health is now " + playerHealth);
                    } else {
                        System.out.println("No potions available!");
                    }
                    break;
                default:
                    System.out.println("Invalid option provided!");
                    break;

            }
        }
    }

    private void runExitBattle(int floorLevel, String playerName, int playerHealth, int playerGold,
                               int playerHealthPotions, int playerLevel, int playerExp) {
        System.out.println("Exit battle run here");
        floorLevel++;
        new PlayGame(floorLevel, playerName, playerHealth, playerGold, playerHealthPotions, playerLevel, playerExp);
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

import java.util.Scanner;

public class Startup {

    public static void main(String[] args) {
        displayTitleScreen();
        Stats player = new Stats(enterName(), 50, 10, 3, 1, 0);
        System.out.println();
        System.out.println("Welcome " + player.getName() + ", taking you to the dungeon now...");
        new PlayGame(1, player);
    }

    private static void displayTitleScreen() {
        System.out.println("************************");
        System.out.println("*     Monster Game     *");
        System.out.println("*     By Stogsheld     *");
        System.out.println("************************");
    }

    private static String enterName() {
        Scanner scn = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to Monster Game, adventurer! What is your name? ");
        String name = scn.nextLine();
        scn.close();
        return name;
    }


}

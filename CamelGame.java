import java.util.Random;
import java.util.Scanner;

/**
 * Created by student on 2/17/18.
 */
public class CamelGame {
    private int milesTraveled = 0;
    private int thirst = 0;
    private int camelFatigue = 0;
    private int nativesDistance = 20;
    private int canteen = 3;
    private boolean stillPlaying = true;
    int moderateSpeed;
    int nativesTraveled;
    int fullSpeed;
    int randomFatigue;

    public void setRand(Random seed) {
        moderateSpeed = seed.nextInt(8) + 5;
        fullSpeed = seed.nextInt(10) + 10;
        nativesTraveled = seed.nextInt(7) + 7;
        randomFatigue = seed.nextInt(3) + 1;
    }

    public void statusCheck() {
        if (this.thirst >= 4 && this.thirst <= 6) {
            System.out.println("You are getting thirsty.");
        }
        if (this.camelFatigue >= 5 && this.camelFatigue <= 8) {
            System.out.println("Your camel is getting tired.");
        }
        if (this.nativesDistance < 16) {
            System.out.println("The natives are getting closer.");
        }
    }
    public CamelGame() {

    }
    public boolean isStillPlaying() {
        if (this.milesTraveled > 200) {
            this.stillPlaying = false;
            System.out.println("You have successfully escaped from the Natives. You win!");
        }
        if (this.nativesDistance <= 0) {
            this.stillPlaying = false;
            System.out.println("You have been caught! You lose.");
        }
        if (this.camelFatigue > 8) {
            this.stillPlaying = false;
            System.out.println("Your camel has died. You lose.");
        }
        if (this.thirst > 6) {
            this.stillPlaying = false;
            System.out.println("You have died of thirst. You lose.");
        }
        return stillPlaying;
    }
    public void menu() {
        System.out.println("(A) Drink from canteen");
        System.out.println("(B) Ahead at moderate speed");
        System.out.println("(C) Ahead at full speed");
        System.out.println("(D) Stop for the night");
        System.out.println("(E) Status check");
        System.out.println("(Q) Quit");
    }
    public void gameLoop(String input) {
        if (input.equalsIgnoreCase("a")) {
            if (canteen == 0) {
                System.out.println("You are out of water and cannot drink.");;
            }
            else {
                this.thirst = 0;
                this.canteen = canteen - 1;
                System.out.println("You now have " + this.canteen + " canteen(s) left.");
            }
        }

        else if (input.equalsIgnoreCase("b")) {
            this.milesTraveled = moderateSpeed + milesTraveled;
            System.out.println("You have traveled " + this.milesTraveled + " miles.");
            this.thirst += 1;
            this.camelFatigue += 1;
            this.nativesDistance += moderateSpeed;
            this.nativesDistance -= nativesTraveled;
        }

        else if (input.equalsIgnoreCase("c")) {
            this.milesTraveled = fullSpeed + milesTraveled;
            System.out.println("You have traveled " + this.milesTraveled + " miles.");
            this.camelFatigue += randomFatigue;
            this.nativesDistance += fullSpeed;
            this.nativesDistance -= nativesTraveled;
        }

        else if (input.equalsIgnoreCase("d")) {
            this.camelFatigue = 0;
            System.out.println("Your camel's fatigue is now " + this.camelFatigue + ".");
            this.nativesDistance -= nativesTraveled;
        }

        else if (input.equalsIgnoreCase("e")) {
            System.out.println("You have traveled " + this.milesTraveled +
                    " miles," + " have " + this.canteen + " canteens left, " + this.camelFatigue + " camel fatigue, and the natives are "
                    + this.nativesDistance + " miles away.");
        }

        else if (input.equalsIgnoreCase("q")) {
            this.stillPlaying = false;
            System.out.println("You have quit.");
        }

        else {
            System.out.println("Please choose a valid choice.");
        }
    }



    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random seed = new Random();
        CamelGame newGame = new CamelGame();
        System.out.println("Welcome to the Camel Game!");
        while (newGame.isStillPlaying()) {
            newGame.statusCheck();
            System.out.println();
            newGame.menu();
            newGame.setRand(seed);
            newGame.gameLoop(userInput.next());
        }
    }
}

package Game;

import Util.Move;

import java.util.Scanner;
import static Util.Messages.*;
import static Util.Move.*;

public class Game {

    private static int playerScore;
    private static int computerScore;


    private Move computerChoice(){

        return Move.values()[(int) (Math.random() * 3)];
    }

    private Move playerChoice(){

        System.out.println(PICK_MOVE);
        String move = new Scanner(System.in).nextLine().toLowerCase();

        return switch (move) {
            case "rock" -> ROCK;
            case "paper" -> PAPER;
            case "scissors" -> SCISSORS;
            default -> playerChoice();
        };

    }

    private String checkWinner(Move computerMove, Move playerMove){

        if(playerMove == computerMove) return DRAW;

        if(playerMove == ROCK && computerMove == SCISSORS ||
            playerMove == PAPER && computerMove == ROCK ||
            playerMove == SCISSORS && computerMove == PAPER){
            playerScore++;
            return WON;
        }
        else{
            computerScore++;
            return LOST;
        }
    }

    private String anotherGame(){

        System.out.println(CHECK_FOR_ANOTHER_GAME);
        String response = new Scanner(System.in).nextLine().toLowerCase();

        if(response.equals(NO)){
            System.out.printf(SCORE, computerScore, playerScore);
            return COME_AGAIN;
        }
        if(response.equals(YES)){
            System.out.println(PLAY_MORE);
            return this.play();
        }
        else{
            System.out.println(INVALID_OPTION);
            return anotherGame();
        }
    }

    public String play(){

        Move computerMove = computerChoice();
        Move playerMove = playerChoice();
        System.out.printf(RESULT, computerMove, playerMove);
        String result = checkWinner(computerMove, playerMove);
        System.out.println(result);
        return anotherGame();
    }

}

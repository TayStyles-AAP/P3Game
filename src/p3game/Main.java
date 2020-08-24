
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P3Game;
import java.util.*;

/**
 *
 * @author taystyles
 */
public class Main {
    public static void main(String[] args) throws java.lang.Exception{
        SavedData gameData = new SavedData();        
        System.out.println("Easy(E) or Hard(H)?");
        char difficulty = (char) System.in.read();
        
        difficulty = Character.toUpperCase(difficulty);
        gameData.setDifficulty(difficulty);
        System.out.println("~~~~~~~~~~~~ | Generated Gameboard | ~~~~~~~~~~~~\n\n"
            + "Key: \n1 = YOU\n2 = Forrest\n3 = Beach\n4 = Enemy");
        gameData.setEnemyLives(5);
        Gameboard.generateBoard();
        Gameboard.startGame(0);
    }
}
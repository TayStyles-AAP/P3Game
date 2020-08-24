/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P3Game;

import java.util.concurrent.*;
import java.util.*;

/**
 *
 * @author taystyles
 */
public class Gameboard{    
    public void generateBoard(SavedData gameData){
        if (gameData.isInGame() == false){
            gameData.setArr(new int[gameData.getX()][gameData.getY()]);
            genTerrain(gameData);
            genEntitys('P', gameData);
            genEntitys('E', gameData);
        }
        
        for (int i = 0; i < gameData.getArr().length; i++){
            for (int j = 0; j < gameData.getArr()[i].length; j++){
                switch(gameData.getArr()[i][j]){
                    case 0:
                        System.out.print("_ ");
                        break;
                    case 1:
                        System.out.print("♿ ");
                        break;
                    case 2:
                        System.out.print("☠ ");
                        break;
                    case 3:
                        System.out.print("⛰ ");
                        break;
                    case 4:
                        System.out.print("~ ");
                        break;
                }
            }
            System.out.println();
        }
    }

/*
 * This startGame() is the loop that starts the game/ is the workspace for the rest of the game.
 */
    public void startGame(int f, SavedData gameData){
        Scanner scan = new Scanner(System.in);
        gameData.setInGame(true);
        
        if (f == 1){
            System.out.println("~~~~~~~~~~~~ | ORIGINAL Gameboard | ~~~~~~~~~~~~");
            System.out.println("New player position = Same as before, you messed up!");
            generateBoard(gameData);
            System.out.print("Enter next movement...");
            System.out.println("EnemyLives: "); 
            enemyLives(gameData);
            //char movement = scan.next().charAt(0);
        }
        
        int i = 0;
        System.out.println("EnemyLives: "); 
        enemyLives(gameData);
        while (gameData.isInGame() == true){
            if (i == 0 && f != 1){
                System.out.println("[W] = UP . [S] = Down . [A] = Left . [D] = Right");
                i++;
            }
            else{
                System.out.println("Enter next movement...");
                System.out.print("EnemyLives: "); 
                enemyLives(gameData);
            }
            
            char movement = scan.next().charAt(0);
            
            switch (movement) {
                case 'W':
                case 'w':
                    updateGameboard(0,-1, gameData); //ARR x = Same, y = -1.
                    break;
                case 'S':
                case 's':
                    updateGameboard(0,1, gameData); //ARR x = Same, y = +1.
                    break;
                case 'A':
                case 'a':
                    updateGameboard(-1,0, gameData); //ARR x -1, y = same.
                    break;
                case 'D':
                case 'd':
                    updateGameboard(1,0, gameData); //ARR x = +1, y = same.
                    break;
                case 'Q':
                case 'q':
                    gameData.setInGame(false);
                    break;
                default:
                    System.out.println("~~Error~~ Enter a valid key.");
                    break;
            }
        }
        scan.close();
    }
    
    /*
    * This updateGameboard() is in charge of updating the board with the most up-to-date user/ enemy information/ locations.
    */
    public void updateGameboard(int changePlayerX, int changePlayerY, SavedData gameData){
        System.out.println("~~~~~~~~~~~~ | UPDATED Gameboard | ~~~~~~~~~~~~");
        
        if (gameData.getEnemyLives() == 0){
            gameData.setInGame(false);
        }
        
        //<<--ENEMY MOVEMENTS BELOW --//>>
        int randMove = ThreadLocalRandom.current().nextInt(0,4);
        int enemyTemp[][] = gameData.getArr();
        switch (randMove) {
            case 0:{
                    //Enemy up
                    int newPos = gameData.getEnemyY() - 1;
                    if (newPos > 0 && newPos <= gameData.getY()-1){                                // Checking if the newPos value is not out of bounds.
                        enemyTemp[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        System.out.println("New enemy position = "+ gameData.getEnemyX() + " : " + newPos); // Print the X/Y co-ords for reference purpose.
                        enemyTemp[newPos][gameData.getEnemyX()] = gameData.getEnemyToken();         // Keeps players X the same and applies newPos to players Y.
                        gameData.setEnemyY(newPos);                                            // Set the gamedata to reflect the new player Y position.
                    }       
                    break;
                }
            case 1:{
                    //Enemy Down
                    int newPos = gameData.getEnemyY() + 1;
                    if (newPos >= 0 && newPos <= gameData.getY()-1){
                        
                        enemyTemp[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        System.out.println("New enemy position = "+ gameData.getEnemyX() +" : "+ newPos);
                        enemyTemp[newPos][gameData.getEnemyX()] = gameData.getEnemyToken();
                        gameData.setEnemyY(newPos);     
                    }
                    break;
                }
            case 2:{
                    //Enemy Left
                    int newPos = gameData.getEnemyX() - 1;
                    if (newPos >= 0 && newPos <= gameData.getX()-1){
                        enemyTemp[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        System.out.println("New enemy position = "+ newPos +" : "+ gameData.getEnemyY());
                        enemyTemp[gameData.getEnemyY()][newPos] = gameData.getEnemyToken();
                        gameData.setEnemyX(newPos);
                    }       
                    break;
                }
            case 3:{
                    //Enemy Right
                    int newPos = gameData.getEnemyX() + 1;
                    if (newPos >= 0 && newPos <= gameData.getX()-1){
                        enemyTemp[gameData.getEnemyY()][gameData.getEnemyX()] = 0;
                        System.out.println("New enemy position = "+ newPos +" : "+ gameData.getEnemyY());
                        enemyTemp[gameData.getEnemyY()][newPos] = gameData.getEnemyToken();
                        gameData.setEnemyX(newPos);
                    }       
                    break;
                }
            default:
                System.err.println("ERROR... WRONG NUMBER GENERATED: " + randMove);
                break;
        }
        gameData.setArr(enemyTemp);
        // </--Enemy movments end --/>
        
        // <-- Player movments start -->
        int playerTemp[][] = gameData.getArr();
        
        if (changePlayerY != 0){
            int newPos = gameData.getPlayerY() + changePlayerY;                                     // Create new int and assign it the players current YPos -1, this is to move the player up the array.
            if (newPos >= 0 && newPos <= gameData.getY()-1){                                // Checking if the newPos value is not out of bounds.
                if (gameData.getEnemyX() == gameData.getPlayerX() && gameData.getEnemyY() == gameData.getPlayerY()){
                    playerTemp[gameData.getPlayerY()][gameData.getPlayerX()] = 2;                      // Append the tempArray to remove the current players marker.
                }
                else{
                    playerTemp[gameData.getPlayerY()][gameData.getPlayerX()] = 0; 
                }
                System.out.println("New player position = "+ gameData.getPlayerX() +" : "+ newPos); // Print the X/Y co-ords for reference purpose.
                playerTemp[newPos][gameData.getPlayerX()] = gameData.getPlayerToken();         // Keeps players X the same and applies newPos to players Y.
                gameData.setPlayerY(newPos);                                            // Set the gamedata to reflect the new player Y position.
            }
        }
        else if (changePlayerX != 0){
            int newPos = gameData.getPlayerX() + changePlayerX;
            if (newPos >= 0 && newPos <= gameData.getX()-1){
                if (gameData.getEnemyX() == gameData.getPlayerX() && gameData.getEnemyY() == gameData.getPlayerY()){
                    playerTemp[gameData.getPlayerY()][gameData.getPlayerX()] = 2;                      // Append the tempArray to remove the current players marker.
                }
                else{
                    playerTemp[gameData.getPlayerY()][gameData.getPlayerX()] = 0; 
                }
                System.out.println("New player position = "+ newPos +" : "+ gameData.getPlayerY());
                playerTemp[gameData.getPlayerY()][newPos] = gameData.getPlayerToken();
                gameData.setPlayerX(newPos);
            }
        }
        else{
            System.out.println("Error in the Gameboard.java class, in the UpdateGameboard() function.");
        }
        gameData.setArr(playerTemp);
        
        // </-- Player movments end --/>
        
        if (gameData.getEnemyX() == gameData.getPlayerX() && gameData.getEnemyY() == gameData.getPlayerY()){
            int tempLives = gameData.getEnemyLives();
            tempLives--;
            gameData.setEnemyLives(tempLives);
            gameData.getArr();
            genEntitys('E', gameData);
        }
        generateBoard(gameData);
    }
    
    public void genEntitys(char playerType, SavedData gameData){
        int randX = ThreadLocalRandom.current().nextInt(0,gameData.getX()); //Generate random value between 0 and max X value and assign it to RandX
        int randY = ThreadLocalRandom.current().nextInt(0,gameData.getY()); //Generate random value between 0 and max Y value and assign it to RandY
        int tempArr[][] = gameData.getArr(); //Make new temp array and assign it the value of the main array
        int randLoop = 0;

        while (randLoop == 0){
            if (tempArr[randY][randX] == 0) {

                switch(playerType){
                    case('P'):
                        System.out.println("Player Co-ords - "+ randX + ":" + randY);
                        gameData.setPlayerX(randX); //Initilise Enemy X to the prevously random number
                        gameData.setPlayerY(randY); //Initilise Enemy Y to the prevously random number
                        tempArr[randY][randX] = gameData.getPlayerToken();
                    break;
                    case('E'):
                        System.out.println("Enemy Co-ords - "+ randX + ":" + randY);
                        gameData.setEnemyX(randX); //Initilise Enemy X to the prevously generated random number
                        gameData.setEnemyY(randY); //Initilise Enemy Y to the prevously generated random number
                        tempArr[randY][randX] = gameData.getEnemyToken();
                }
                randLoop = 1;
            }
            else{
                randX = ThreadLocalRandom.current().nextInt(0,gameData.getX()); //Generate random value between 0 and max X value and assign it to RandX
                randY = ThreadLocalRandom.current().nextInt(0,gameData.getY()); //Generate random value between 0 and max Y value and assign it to RandY
            }
        }                
        gameData.setArr(tempArr); //assign main array the values of temp Array
    }
    
    public static void genTerrain(SavedData gameData){
        int tempArr[][] = gameData.getArr();

        for (int i = 0; i < tempArr.length; i++){
            tempArr[i][0] = 3;
        }
        for (int j = 0; j < tempArr.length; j++){
            tempArr[0][j] = 4;
        }
        gameData.setArr(tempArr);
    }
    
    public static void enemyLives(SavedData gameData){
        int count = 0;
        for(int i = 0; i < gameData.getEnemyLives(); i++){
            System.out.print("♥ ");
            count++;
        }
        while(count < 5){
            System.out.print("♡ ");
            count++;
        }
        if (gameData.getEnemyLives() ==0){
            System.exit(0);
        }       
        System.out.println();   
    }
}
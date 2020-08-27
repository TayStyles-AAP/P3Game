/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3game;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Taylor
 */
public class Generator {
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
}

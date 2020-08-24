package P3Game;

public class SavedData{
    private boolean debug;
    private int x;
    private int y;
    private char difficulty;
    private int playerX;
    private int playerY;
    private int enemyX;
    private int enemyY;
    private int[][] arr;
    private boolean inGame;
    private int playerToken = 1;
    private int EnemyToken = 2;
    private int enemyLives;
    
    public boolean isDebug() {
        return debug;
    }

    public int getEnemyLives() {
        return enemyLives;
    }
    
    public int getPlayerToken() {
        return playerToken;
    }
    
    public int getEnemyToken() {
        return EnemyToken;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public char getDifficulty() {
        return difficulty;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getEnemyX() {
        return enemyX;
    }

    public int getEnemyY() {
        return enemyY;
    }

    public int[][] getArr() {
        return arr;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setDifficulty(char difficulty) {
        switch (difficulty) {
            case 'E':
            case 'e':
                this.x = 10;
                this.y = 10;
                this.difficulty = difficulty;
                break;
            case 'H':
            case 'h':
                this.x = 25;
                this.y = 25;
                this.difficulty = difficulty;
                break;
            default:
                System.out.println("That wasnt a valid difficulty. Easy selected.");
                this.x = 10;
                this.y = 10;
                this.difficulty = 'E';
                break;
        }
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    } 

    public void setEnemyX(int enemyX) {
        this.enemyX = enemyX;
    }



    public void setEnemyY(int enemyY) {
        this.enemyY = enemyY;
    }

    public void setArr(int[][] arr) {
        this.arr = arr;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
    public void setEnemyLives(int enemyLives) {
        this.enemyLives = enemyLives;
    }
}
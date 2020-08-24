package P3Game;

public class SavedData{
    private static boolean debug;
    private static int x;
    private static int y;
    private static char difficulty;
    private static int playerX;
    private static int playerY;
    private static int enemyX;
    private static int enemyY;
    private static int[][] arr;
    private static boolean inGame;
    private static int playerToken = 1;
    private static int EnemyToken = 2;
    private static int enemyLives;
    
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
        return x;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDifficulty(char difficulty) {
        switch (difficulty) {
            case 'E':
            case 'e':
                setX(10);
                setY(10);
                this.difficulty = difficulty;
                break;
            case 'H':
            case 'h':
                setX(25);
                setY(25);
                this.difficulty = difficulty;
                break;
            default:
                System.out.println("That wasnt a valid difficulty. Easy selected.");
                setX(10);
                setY(10);
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
        SavedData.enemyX = enemyX;
    }

    public void setEnemyY(int enemyY) {
        SavedData.enemyY = enemyY;
    }

    public void setArr(int[][] arr) {
        this.arr = arr;
    }

    public void setInGame(boolean inGame) {
        SavedData.inGame = inGame;
    }
    
    public void setEnemyLives(int enemyLives) {
        SavedData.enemyLives = enemyLives;
    }
}
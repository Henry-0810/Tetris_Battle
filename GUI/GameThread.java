package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;

//https://www.w3schools.com/java/java_threads.asp learn a few basics from this website
public class GameThread extends Thread{
    private GameArea gameArea;
    private int score = 0;
    public GameThread(GameArea gameArea){
        setGameArea(gameArea);
    }

    public void setGameArea(GameArea gameArea) {
        this.gameArea = gameArea;
    }
    public static int speed;

    public void setSpeed(int speed) {
        GameThread.speed = speed;
    }

    public static int getSpeed() {
        return speed;
    }

    public void run() {
        while (true) {
            gameArea.createBlocks();

            while(gameArea.blocksDrop()) {
                try {
                    Thread.sleep(getSpeed());
                } catch (InterruptedException e) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            if(gameArea.isGameOver()){
                GameFrame game = new GameFrame();
                game.dispose();
                GameOver gameOver = new GameOver();
                gameOver.setVisible(true);
                break;
            }

            gameArea.setBlocksToBg();
            score += (gameArea.clearCompleteLines()*100);

        }


    }
}

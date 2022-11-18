package TetrisGUI;

import java.util.logging.Level;
import java.util.logging.Logger;

//https://www.w3schools.com/java/java_threads.asp learn a few basics from this website
public class GameThread extends Thread{
    private GameArea gameArea;
    public GameThread(GameArea gameArea){
        setGameArea(gameArea);
    }

    public void setGameArea(GameArea gameArea) {
        this.gameArea = gameArea;
    }

    public void run() {
        while (true) {
            try {
                gameArea.dropBlocks();
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE,null,e);
            }

        }


    }
}
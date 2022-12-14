package TetrisGame.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameMainMenu extends JFrame {
    public static ImageIcon imageIcon = new ImageIcon("TetrisGame/GUI/additionalFiles/GameIcon.png"); //game icon

    public GameMainMenu() {
        this.setTitle("Tetris 2.0");
        JLabel title = new titleLabel();
        JPanel bg = new JPanel();
        bg.setBackground(Color.black);
        this.setContentPane(bg);
        JPanel titlePic = new TitlePanel();
        JButton start = new JButton("Start");
        start.setBounds(150,200,175,50);
        start.setBackground(Color.black);
        start.setForeground(Color.white);
        start.setFont(new Font("Monospaced",Font.PLAIN,30));
        start.addActionListener(new StartGame());
        JButton history = new JButton("Previous Contender");
        history.setBounds(50,260,400,50);
        history.setBackground(Color.black);
        history.setForeground(Color.white);
        history.setFont(new Font("Monospaced",Font.PLAIN,30));
        history.addActionListener(new ViewPlayer());
        JButton exit = new JButton("Exit");
        exit.setBounds(150,320,175,50);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        exit.setFont(new Font("Monospaced",Font.PLAIN,30));
        exit.addActionListener(new ExitGame());
        this.setIconImage(imageIcon.getImage());
        this.add(title);
        this.add(titlePic);
        this.add(start);
        this.add(history);
        this.add(exit);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class StartGame implements ActionListener
    {
        //@SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent action)
        {
            GameDifficulty gameDifficulty = new GameDifficulty();
            gameDifficulty.setVisible(true);

            GameMainMenu.this.dispose();
        }
    }

    private class ExitGame implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            int exitInterface = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit Tetris 2.0?","Tetris 2.0",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,imageIcon);
            if(exitInterface == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }


    private class ViewPlayer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                File myObj = new File("TetrisGame/GUI/additionalFiles/LastPlayer.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    JOptionPane.showMessageDialog(null,
                            "This is the previous player that played Tetris 2.0:\n"+data,"Tetris 2.0",
                            JOptionPane.INFORMATION_MESSAGE,imageIcon);
                }
                myReader.close();
            } catch (FileNotFoundException exception) {
                System.out.println("An error occurred.");
                exception.printStackTrace();
            }
        }
    }

    private static class TitlePanel extends JPanel
    {
        Image image;
        public TitlePanel()
        {
            this.setBounds(186,0,128,128);
            this.setBackground(new Color(0f,0f,0f,0.5f));
            try
            {
                image = Toolkit.getDefaultToolkit().createImage("TetrisGame/GUI/additionalFiles/GameTitlePic.png");
            }
            catch (Exception e) { /*handled in paintComponent()*/ }
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (image != null)
                g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
        }
    }
    private static class titleLabel extends JLabel{
        public titleLabel(){
            this.setText("<html><font color='#FD1C03'>T</font><font color='#FF6700'>e</font><font color='FFFF33'>t</font>" +
                    "<font color='#16F529'>r</font><font color='#1589FF'>i</font>" +
                    "<font color='7B00FF'>s</font> <font color='9D00FF'>2.0</font></html>");
            this.setBounds(65,108,470,100);
            this.setFont(new Font("comic sans ms",Font.BOLD+Font.ITALIC,70));

        }
    }
}

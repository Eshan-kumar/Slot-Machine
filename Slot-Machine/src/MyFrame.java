
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    int balance = 100;
    JLabel outerLabel= new JLabel();
    JPanel imgPanel =new JPanel();
    JLabel label=new JLabel();
    JLabel innerLabel =new JLabel();
    JLabel slotLabel=new JLabel();
    JPanel slotPanelImg = new JPanel();
    JLabel slotLabelImg1=new JLabel();
    JLabel slotLabelImg2=new JLabel();
    JLabel slotLabelImg3=new JLabel();

    JLabel balanceLabel=new JLabel();
    JTextField betText = new JTextField();
    JLabel resultLabel=new JLabel();
    JButton playAgain = new JButton();

    MyFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 700);
        this.setLocation(300,50);
        this.setLayout(null);

        outerLabel.setLayout(null);
        outerLabel.setBackground(Color.white);
        outerLabel.setBounds(0,0,500,700);
        outerLabel.setText("Welcome To Slot-machine");
        outerLabel.setVerticalAlignment(JLabel.TOP);
        outerLabel.setHorizontalAlignment(JLabel.CENTER);
        outerLabel.setFont(new Font("MV Boli", Font.PLAIN,30));


        label.setLayout(null);
        label.setBounds(50,50,400,600);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK,5,true));
        label.setText("Symbols:");
        label.setFont(new Font("MV Boli", Font.PLAIN,30));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon cherry=loadIcon("/slotImages/cherry.jpg");
        ImageIcon watermelon= loadIcon("/slotImages/watermelon.jpg");
        ImageIcon lemon= loadIcon("/slotImages/lemon.jpg");
        ImageIcon bell= loadIcon("/slotImages/bell.jpg");
        ImageIcon star= loadIcon("/slotImages/star.jpg");

        imgPanel.setBounds(5,50,380,100);
        imgPanel.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        JLabel symbol1=new JLabel(cherry);
        JLabel symbol2=new JLabel(watermelon);
        JLabel symbol3=new JLabel(lemon);
        JLabel symbol4=new JLabel(bell);
        JLabel symbol5=new JLabel(star);

        imgPanel.add(symbol1);
        imgPanel.add(symbol2);
        imgPanel.add(symbol3);
        imgPanel.add(symbol4);
        imgPanel.add(symbol5);
        label.add(imgPanel);


        slotLabel.setLayout(null);
        slotLabel.setBounds(10,10,320,120);
        slotLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5,true));
        slotPanelImg.setBounds(10,10,300,90);
        slotPanelImg.setLayout(new FlowLayout());
        slotPanelImg.add(slotLabelImg1);
        slotPanelImg.add(slotLabelImg2);
        slotPanelImg.add(slotLabelImg3);
        slotLabel.add(slotPanelImg);
        innerLabel.add(slotLabel);

        balanceLabel.setLayout(null);
        balanceLabel.setBounds(10,100,320,90);
        balanceLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        balanceLabel.setText("Current Balance: $"+balance);
        innerLabel.add(balanceLabel);

        betText.setLayout(null);
        betText.setBounds(10,180,320,60);
        betText.setBorder(BorderFactory.createLineBorder(Color.BLACK,5,true));
        betText.setText("");
        betText.setText("Enter Bet here");
        betText.setFont(new Font("MV Boli", Font.PLAIN,20));
        innerLabel.add(betText);


        resultLabel.setLayout(new FlowLayout());
        resultLabel.setBounds(10,250,320,100);
        resultLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5,true));
        resultLabel.setFont(new Font("MV Boli", Font.PLAIN,30));
        innerLabel.add(resultLabel);

        playAgain.setFocusable(false);
        playAgain.setBounds(10,360,320,30);
        playAgain.setText("Spin");
        playAgain.setHorizontalTextPosition(JLabel.CENTER);
        playAgain.addActionListener(this);

        innerLabel.setLayout(null);
        innerLabel.setBounds(30,150,340,400);
        innerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5,true));
        innerLabel.add(playAgain);
        label.add(innerLabel);

        outerLabel.add(label);
        this.add(outerLabel);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==playAgain){
            int payout;
            String[] symbols;
            String bettext= betText.getText();
            int bet;
            try{
                bet=Integer.parseInt(bettext);
            }catch(NumberFormatException q){
                exception();
                return;
            }
            if(bet > balance || bet <= 0){
                Error(bet,balance);
                return;
            }
            balance-=bet;
            balanceLabel.setText("Current Balance: $"+balance);

            symbols= Main.spinRow();
            Main.printRow(symbols,this);

            payout= Main.getPayout(symbols,bet);
            if(payout>0){
                resultLabel.setText("You won $"+payout);
                balance+=payout;
            }else{
                this.resultLabel.setText("Lost this round");
            }
            balanceLabel.setText("Current Balance: $"+balance);
            if(balance<=0){
                End(balance);
                return;
            }
            if(playAgain()==JOptionPane.NO_OPTION){
                End(balance);
                playAgain.setEnabled(false);
            }
        }
    }

    public void Error(int bet,int balance){
        if(bet > balance){
            JOptionPane.showMessageDialog(null,"Insufficient Balance","Error",JOptionPane.ERROR_MESSAGE);
        }else if(bet <=0){
            JOptionPane.showMessageDialog(null,"Invalid Bet amount","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void End(int balance){
        JOptionPane.showMessageDialog(null,"GAME OVER\nYour final balance is: "+balance,"Thank You for playing",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
    public int playAgain(){
        return JOptionPane.showConfirmDialog(null,"Do you want to play again?","Play Again?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    }
    public void exception(){
        JOptionPane.showMessageDialog(null,"Number Not found!","Error",JOptionPane.ERROR_MESSAGE);
    }
    public static ImageIcon loadIcon(String path) {
        java.net.URL imgURL = MyFrame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Image not found: " + path);
            return new ImageIcon(); // return empty icon instead of null
        }
    }

}
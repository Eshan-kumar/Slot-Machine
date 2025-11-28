import javax.swing.*;
import java.util.Random;

public class Main{
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            MyFrame frame=new MyFrame();
        });
    }
    public static String[] spinRow(){

        String[] symbols={
                "cherry.jpg",
                "watermelon.jpg",
                "lemon.jpg",
                "bell.jpg",
                "star.jpg"};

        String[] row=new String[3];
        Random random = new Random();


        for(int i=0;i<3;i++){
            row[i]=symbols[random.nextInt(symbols.length)];
        }
        return row;
    }
    public static void printRow(String[] row,MyFrame frame){
        ImageIcon row1=MyFrame.loadIcon("/slotImages/"+row[0]);
        ImageIcon row2=MyFrame.loadIcon("/slotImages/"+row[1]);
        ImageIcon row3=MyFrame.loadIcon("/slotImages/"+row[2]);

        frame.slotLabelImg1.setIcon(row1);
        frame.slotLabelImg2.setIcon(row2);
        frame.slotLabelImg3.setIcon(row3);

    }
    static int getPayout(String[] row,int bet){
        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            return switch(row[0]){
                case "cherry.jpg"-> bet*3;
                case "watermelon.jpg"-> bet*4;
                case "lemon.jpg"-> bet*5;
                case "bell.jpg"-> bet*10;
                case "star.jpg"-> bet*20;
                default -> 0;
            };
        }else if(row[0].equals(row[1])){
            return switch(row[0]){
                case "cherry.jpg"-> bet*2;
                case "watermelon.jpg"-> bet*3;
                case "lemon.jpg"-> bet*4;
                case "bell.jpg"-> bet*5;
                case "star.jpg"-> bet*10;
                default -> 0;
            };
        }else if(row[1].equals(row[2])){
            return switch(row[1]){
                case "cherry.jpg"-> bet*2;
                case "watermelon.jpg"-> bet*3;
                case "lemon.jpg"-> bet*4;
                case "bell.jpg"-> bet*5;
                case "star.jpg"-> bet*10;
                default -> 0;
            };
        }
        return 0;
    }
}

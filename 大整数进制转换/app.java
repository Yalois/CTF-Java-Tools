import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigInteger;
class frame extends JFrame{
    JTextArea input,output;
    JButton hexToDecl,DeclToHex,BinToHex,HexToBin,DecToBin,BinToDec;
    ActionListener listener=new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String return_S="";
            if(e.getSource()==HexToBin){return_S=(Trans(16, 2));}
            else if(e.getSource()==BinToHex){return_S=Trans(2, 16);}
            else if(e.getSource()==DecToBin){return_S=Trans(10, 2);}
            else if(e.getSource()==BinToDec){return_S=Trans(2, 10);}
            else if(e.getSource()==hexToDecl){return_S=Trans(16, 10);}
            else if(e.getSource()==DeclToHex){return_S=Trans(10, 16);}
            output.setText(return_S);
        };
    };
    
    public String Trans(int from,int to)
    {
        try {
            BigInteger i=new BigInteger(input.getText(), from);
            return i.toString(to);
        } catch (Exception Ee) {
            return ("转换出错，请检查输入内容;");
        }
    }
    
    frame(){

        //UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("大整数进制转换[Github地址:https://github.com/Yalois/CTF-Java-Tools]");
        //组件
        input=new JTextArea();
        input.setFont (new Font ("宋体", Font.PLAIN, 16));
        output=new JTextArea();
        output.setFont (new Font ("宋体", Font.PLAIN, 16));
        hexToDecl=new JButton("十六进制-->十进制");
        DeclToHex=new JButton("十进制-->十六进制");
        BinToHex=new JButton("二进制-->十六进制");
        HexToBin=new JButton("十六进制-->二进制");
        DecToBin=new JButton("十进制-->二进制");
        BinToDec=new JButton("二进制-->十进制");
        hexToDecl.addActionListener(listener);
        DeclToHex.addActionListener(listener);
        BinToHex.addActionListener(listener);
        HexToBin.addActionListener(listener);
        DecToBin.addActionListener(listener);
        BinToDec.addActionListener(listener);

        setLayout(new BorderLayout());
        input.setLineWrap(true);
        output.setLineWrap(true);

        Box line1=Box.createHorizontalBox();
        line1.add(new JLabel("输入:"));
        line1.add(new JScrollPane(input));
        Box line2=Box.createHorizontalBox();
        line2.add(hexToDecl);
        line2.add(DeclToHex);
        Box line2_1=Box.createHorizontalBox();
        line2_1.add(HexToBin);
        line2_1.add(BinToHex);
        Box line2_2=Box.createHorizontalBox();
        line2_2.add(BinToDec);
        line2_2.add(DecToBin);

        Box line3=Box.createHorizontalBox();
        line3.add(new JLabel("输出:"));
        line3.add(new JScrollPane(output));

        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(line1);
        verticalBox.add(line2);
        verticalBox.add(line2_1);
        verticalBox.add(line2_2);
        verticalBox.add(line3);
        add(verticalBox,BorderLayout.CENTER);
        
        
        pack();
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        
    }
       
}

public class app{
    public static void main(String[] args) {
        new frame();
    }
}
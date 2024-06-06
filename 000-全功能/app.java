import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;

public class app {
    public static void main(String args[]) {
        new MainWin();
    }
}

class MainWin extends JFrame {
    MainWin() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setTitle("CTF文本处理工具");
        this.setLayout(new BorderLayout());
        Container c = this.getContentPane();
        JTabbedPane tab = new JTabbedPane();
        tab.addTab("基础处理", new StringAddressPanel());
        tab.addTab("进制转换", new NumberTrans());
        tab.addTab("关于", new AboutPanel());
        c.add(tab, BorderLayout.CENTER);

        pack();
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

    }
}
class NumberTrans extends JPanel{
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
    
    NumberTrans(){
        input=new JTextArea();
        output=new JTextArea();
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
    }
}
class StringAddressPanel extends JPanel {
    StringAddressPanel() {
        JTextArea inPut = new JTextArea();
        setLayout(new BorderLayout());
        JScrollPane inScroll = new JScrollPane(inPut);
        add(inScroll,BorderLayout.CENTER);
        add(new OperateionSelector(inPut),BorderLayout.NORTH);
    }
}

class AboutPanel extends JPanel{
    AboutPanel()
    {
        setLayout(new BorderLayout());
        String about="作者:Yalois";
        add(new JLabel(about));
        Box h=Box.createVerticalBox();
        h.add(new JLabel(about));
        h.add(new JLabel("在JavaGUI学习中写的一个小工具"));
        for(Component c:h.getComponents())
        {
            if(c instanceof JLabel)
            {
                ((JLabel)c).setFont(new Font("宋体",Font.PLAIN, 18));
            }
        }
        JButton g=new JButton("跳转Github主页");
        g.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Desktop.getDesktop().browse(new URL("https://github.com/Yalois/CTF-Java-Tools").toURI());
            }catch(Exception eee){
                
            }
        }
        });
        h.add(g);
        add(h,BorderLayout.CENTER);
    }
}
class TextAreaDialog extends JDialog{
    TextAreaDialog(String content,String title){
        setLayout(new BorderLayout());
        JTextArea c=new JTextArea();
        c.setLineWrap(true);
        JScrollPane jp=new JScrollPane(c);
        add(jp);
        setTitle(title);
        c.setText(content);
        pack();
        setVisible(true);
        setSize(400,300);
        setLocationRelativeTo(null);
    }
}

class OperateionSelector extends JPanel {
    public String insert0x(String input) {
        input=input.replaceAll(" ", "");
        if(input.length()%2!=0){input="0"+input;}
        String res="";
        for(int i=0;i<input.length();i++)
        {
            if(i%2==0)
            {
                res+="0x"+input.substring(i, i+2)+" ";
            }
        }
        return res.substring(0,res.length()-1);
    }
    OperateionSelector(JTextArea jta) {
        
        setLayout(new FlowLayout());
            
        JButton Remove0x=new JButton("16进制移除0x");
        Remove0x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText(jta.getText().replaceAll("0x", ""));
            }
        });
        JButton ADD0x=new JButton("16进制添加0x");
        ADD0x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText(insert0x(jta.getText()));
            }
        });
        JButton Tre=new JButton("文本逆序");
        Tre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText(new StringBuffer(jta.getText()).reverse().toString());
            }
        });
        JButton space=new JButton("删除空格");
        space.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jta.setText(jta.getText().replaceAll(" ", ""));
            };
        });
        JButton line=new JButton("删除换行");
        line.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jta.setText(jta.getText().replaceAll("\n", ""));
            };
        });
        JButton frequcey=new JButton("字频统计");
        frequcey.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
              //为所有字母创建一个Map
              String t=jta.getText();
              t=t.replaceAll("\n", "");
              HashMap<Character,Integer> map=new HashMap<>();
              for(int i=0;i<t.length();i++)
              {
                if(map.containsKey(t.charAt(i)))
                {
                    int b=map.get(t.charAt(i))+1;
                    map.put(t.charAt(i), b);
                }else{
                    map.put(t.charAt(i), 1);
                }
              }
              
              List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
              list.sort(new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                } 
              });
              String result="";
              System.out.println(map.entrySet());
              for(Map.Entry<Character, Integer> s:list)
              {
                result+=s.getKey()+" 出现了"+s.getValue()+"次"+"频率:"+Float.valueOf(s.getValue())/t.length()+"\n";
              }
              TextAreaDialog tad=new TextAreaDialog(result, "统计");
              
            };
        });
        add(space);
        add(line);
        add(frequcey);
        add(Tre);
        add(Remove0x);
        add(ADD0x);
    }
}
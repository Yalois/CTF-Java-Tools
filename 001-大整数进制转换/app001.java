import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigInteger;
class frame extends JFrame{
    JTextArea input, output;
    JButton TransN;
    JComboBox Number_From = new JComboBox<Integer>(new Integer[] { 16, 10, 8, 2 });
    JComboBox Number_To = new JComboBox<Integer>(new Integer[] { 2, 8, 10, 16 });
    ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            output.setText(Trans((int) Number_From.getSelectedItem(), (int) Number_To.getSelectedItem()));
        };
    };
    public String Trans(int from, int to) {
        try {
            BigInteger i = new BigInteger(input.getText(), from);
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
        TransN = new JButton("转换");
        TransN.addActionListener(listener);
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        input.setLineWrap(true);
        output.setLineWrap(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // 组件所在的列
        gbc.gridy = 0; // 组件所在的行
        gbc.gridwidth = 1; // 组件占据的列数
        gbc.gridheight = 1; // 组件占据的行数
        gbc.weightx = 1.0; // 组件在水平方向上的拉伸权重
        gbc.weighty = 0.48; // 组件在垂直方向上的拉伸权重
        gbc.fill = GridBagConstraints.BOTH; // 组件如何填充空间
        gbc.anchor = GridBagConstraints.CENTER; // 组件在网格中的对齐方式
        Box line1 = Box.createHorizontalBox();
        line1.add(new JLabel("输入:"));
        line1.add(new JScrollPane(input));
        add(line1, gbc);
        Box line2 = Box.createHorizontalBox();
        line2.add(new JLabel("从"));
        line2.add(Number_From);
        line2.add(new JLabel("进制"));
        line2.add(new JLabel("到"));
        line2.add(Number_To);
        line2.add(new JLabel("进制"));
        line2.add(TransN);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0; // 组件所在的列
        gbc1.gridy = 1; // 组件所在的行
        gbc1.gridwidth = 1; // 组件占据的列数
        gbc1.gridheight = 1; // 组件占据的行数
        gbc1.weightx = 0.8; // 组件在水平方向上的拉伸权重
        gbc1.weighty = 0.04; // 组件在垂直方向上的拉伸权重s
        gbc1.anchor = GridBagConstraints.CENTER; // 组件在网格中的对齐方式
        add(line2, gbc1);
        Box line3 = Box.createHorizontalBox();
        line3.add(new JLabel("输出:"));
        line3.add(new JScrollPane(output));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0; // 组件所在的列
        gbc2.gridy = 2; // 组件所在的行
        gbc2.gridwidth = 1; // 组件占据的列数
        gbc2.gridheight = 1; // 组件占据的行数
        gbc2.weightx = 1.0; // 组件在水平方向上的拉伸权重
        gbc2.weighty = 0.48; // 组件在垂直方向上的拉伸权重
        gbc2.fill = GridBagConstraints.BOTH; // 组件如何填充空间
        gbc2.anchor = GridBagConstraints.CENTER; // 组件在网格中的对齐方式
        add(line3, gbc2);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        
    }
       
}

public class app001{
    public static void main(String[] args) {
        new frame();
    }
}
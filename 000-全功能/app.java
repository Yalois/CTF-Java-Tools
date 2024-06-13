import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.event.EventListenerList;

public class app {
    public static void main(String args[]) {
        new MainWin();
    }
}

class MainWin extends JFrame {
    MainWin() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setTitle("CTF文本处理工具");
        this.setLayout(new BorderLayout());
        Container c = this.getContentPane();
        JTabbedPane tab = new JTabbedPane();
        tab.addTab("基础处理", new StringAddressPanel(this));
        tab.addTab("进制转换", new NumberTrans());
        tab.addTab("编码解码", new EncAndDec());

        tab.addTab("关于", new AboutPanel());
        c.add(tab, BorderLayout.CENTER);

        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

    }
}

class EncAndDec extends JPanel {
    JTextArea input, output;
    JButton commit;
    JComboBox listx;
    ActionListener listener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(listx.getSelectedItem().toString()=="URL编码")
            {
                try {
                    String r = URLEncoder.encode(input.getText().toString(), "UTF-8");
                    output.setText(r);
                } catch (Exception eee) {
                    JOptionPane.showMessageDialog(listx.getParent(), "编码出错，请检查内容", "标题", JOptionPane.WARNING_MESSAGE);
                }
            }
            if(listx.getSelectedItem().toString()=="URL解码")
            {
                try {
                    output.setText(URLDecoder.decode(input.getText().toString(), "UTF-8"));
                } catch (Exception eee) {
                    JOptionPane.showMessageDialog(listx.getParent(), "解码出错，请检查内容", "标题", JOptionPane.WARNING_MESSAGE);
                }
            }
        };
    };
    EncAndDec() {
        input = new JTextArea();
        output = new JTextArea();
        listx=new JComboBox<String>(new String[]{"URL编码","URL解码"});
        commit = new JButton("调用");
        commit.addActionListener(listener);
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
        line2.add(listx);
        line2.add(commit);
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
    }
}

class NumberTrans extends JPanel {
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

    NumberTrans() {
        input = new JTextArea();
        output = new JTextArea();

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

    }
}

class StringAddressPanel extends JPanel {
    StringAddressPanel(JFrame windows) {
        JTextArea inPut = new JTextArea();
        setLayout(new BorderLayout());
        JScrollPane inScroll = new JScrollPane(inPut);
        add(inScroll, BorderLayout.CENTER);
        add(new OperateionSelector(windows, inPut), BorderLayout.NORTH);
    }
}

class AboutPanel extends JPanel {
    AboutPanel() {
        setLayout(new BorderLayout());
        String about = "作者:Yalois";
        add(new JLabel(about));
        Box h = Box.createVerticalBox();
        h.add(new JLabel(about));
        h.add(new JLabel("在JavaGUI学习中写的一个小工具"));
        for (Component c : h.getComponents()) {
            if (c instanceof JLabel) {
                ((JLabel) c).setFont(new Font("宋体", Font.PLAIN, 18));
            }
        }
        JButton g = new JButton("跳转Github主页");
        g.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/Yalois/CTF-Java-Tools").toURI());
                } catch (Exception eee) {

                }
            }
        });
        h.add(g);
        add(h, BorderLayout.CENTER);
    }
}

class TextAreaDialog extends JDialog {
    TextAreaDialog(String content, String title) {
        setLayout(new BorderLayout());
        JTextArea c = new JTextArea();
        c.setLineWrap(true);
        JScrollPane jp = new JScrollPane(c);
        add(jp);
        setTitle(title);
        c.setText(content);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
}

class OperateionSelector extends JPanel {

    public String insert0x(String input) {
        input = input.replaceAll(" ", "");
        if (input.length() % 2 != 0) {
            input = "0" + input;
        }
        String res = "";
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                res += "0x" + input.substring(i, i + 2) + " ";
            }
        }
        return res.substring(0, res.length() - 1);
    }

    OperateionSelector(JFrame windows, JTextArea jta) {
        setLayout(new FlowLayout());
        Box vBox = Box.createVerticalBox();
        Box line_1 = Box.createHorizontalBox();
        Box line_2 = Box.createHorizontalBox();

        JButton stringToAscii = new JButton("文本转ASCII码");
        stringToAscii.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String in = jta.getText();
                // 判断In的字符是否都在ASCII码里
                String result = "";
                boolean inRange = true;
                for (int i = 0; i < in.length(); i++) {
                    if (in.charAt(i) + 0 >= 128 || in.charAt(i) + 0 < 0) {
                        inRange = false;
                        result = "请检查输入的值是否在ASCII范围内";
                        break;
                    }
                }
                if (inRange) {

                    result += "十进制\n";
                    for (int i = 0; i < in.length(); i++) {
                        result += String.valueOf(in.charAt(i) + 0) + " ";
                    }
                    result += "\n八进制\n";
                    for (int i = 0; i < in.length(); i++) {
                        result += "\\0" + Integer.toOctalString(in.charAt(i) + 0) + " ";
                    }
                    result += "\n十六进制\n";
                    for (int i = 0; i < in.length(); i++) {
                        result += "\\x" + Integer.toHexString(in.charAt(i) + 0) + " ";
                    }
                }
                jta.setText(result);
            }
        });

        JButton Remove0x = new JButton("16进制移除0x");
        Remove0x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText(jta.getText().replaceAll("0x", ""));
            }
        });
        JButton ADD0x = new JButton("16进制添加0x");
        ADD0x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText(insert0x(jta.getText()));
            }
        });
        JButton Tre = new JButton("文本逆序");
        Tre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText(new StringBuffer(jta.getText()).reverse().toString());
            }
        });

        JButton Split = new JButton("Split");
        Split.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 输入界限字符串，然后输出
                String splitSTR = JOptionPane.showInputDialog("输入要拆分字符串的分割字符/字符串(换行\\n或者空格):");
                String splitNew = JOptionPane.showInputDialog("输入区分界限的字符/字符串:");

                if (splitNew != null && splitNew != null && splitNew.length() > 0 && splitSTR.length() > 0) {
                    String sp[] = jta.getText().split(splitSTR);
                    String result = "";
                    for (String i : sp) {
                        result += "" + i + splitNew;
                    }
                    result = result.replaceAll("\\\\n", "\n");
                    result = result.replaceAll("\\\\t", "\t");
                    jta.setText(result);
                } else {
                    JOptionPane.showMessageDialog(jta, "内容输入不正确");
                }

            }
        });

        JButton space = new JButton("删除空格");
        space.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jta.setText(jta.getText().replaceAll(" ", ""));
            };
        });
        JButton line = new JButton("删除换行");
        line.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jta.setText(jta.getText().replaceAll("\n", ""));
            };
        });
        JButton Delete_headchar_per_line = new JButton("删除每行首字符");
        Delete_headchar_per_line.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String[] lines = jta.getText().split("\n");
                System.out.println(lines);
                String result = "";
                for (String perLine : lines) {
                    if (perLine.length() >= 1) {
                        result += perLine.substring(1);
                    }
                    result += "\n";
                }
                jta.setText(result);
            };
        });

        JButton Delete_tailchar_per_line = new JButton("删除每行尾字符");
        Delete_tailchar_per_line.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String[] lines = jta.getText().split("\n");
                System.out.println(lines);
                String result = "";
                for (String perLine : lines) {
                    if (perLine.length() >= 1) {
                        result += perLine.substring(0, perLine.length() - 1);
                    }
                    result += "\n";
                }
                jta.setText(result);
            };
        });

        JButton frequcey = new JButton("字频统计");
        frequcey.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 为所有字母创建一个Map
                String t = jta.getText();
                t = t.replaceAll("\n", "");
                HashMap<Character, Integer> map = new HashMap<>();
                for (int i = 0; i < t.length(); i++) {
                    if (map.containsKey(t.charAt(i))) {
                        int b = map.get(t.charAt(i)) + 1;
                        map.put(t.charAt(i), b);
                    } else {
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
                String result = "";
                for (Map.Entry<Character, Integer> s : list) {
                    result += s.getKey() + " 出现了" + s.getValue() + "次" + "频率:"
                            + Float.valueOf(s.getValue()) / t.length() + "\n";
                }
                TextAreaDialog tad = new TextAreaDialog(result, "统计");
                tad.setVisible(true);
            };
        });
        line_1.add(space);
        line_1.add(line);
        line_1.add(frequcey);
        line_1.add(Tre);
        line_1.add(Remove0x);
        line_1.add(ADD0x);
        line_2.add(Delete_headchar_per_line);
        line_2.add(Delete_tailchar_per_line);
        line_2.add(stringToAscii);
        line_2.add(Split);
        vBox.add(line_1);
        vBox.add(line_2);
        add(vBox);
    }
}

class InputDialog extends JDialog {
    JTextField inputField;

    InputDialog(JFrame parent, String title) {
        super(parent, title, true);
        JDialog jd = this;
        setLayout(new BorderLayout());
        Box buttons = Box.createHorizontalBox();
        JButton confrim = (new JButton("确认"));
        JButton cancel = (new JButton("取消"));
        buttons.add(confrim);
        buttons.add(cancel);
        inputField = new JTextField(1);
        confrim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jd.dispose();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jd.dispose();
            }
        });
        add(inputField, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        setSize(200, 100);
        setVisible(true);
    }
}
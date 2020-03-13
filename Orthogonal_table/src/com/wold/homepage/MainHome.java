package com.wold.homepage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.wold.control.CreatUseCase;
import com.wold.control.ReadRule;
import com.wold.file.FileRead;

@SuppressWarnings("serial")
public class MainHome extends JFrame {
	private JTextArea area1;
	private JButton create;
	private JLabel label4;
	private JLabel label5;
	private JTextArea area2;
	private JTextArea area3;

	public MainHome() {
		init();
	}

	public static void main(String[] args) {
		new MainHome();
	}

	/**
	 * 主界面加载
	 */
	public void init() {
		this.setTitle("正交表测试生成器");// 设置标题
		this.setBounds(500, 100, 800, 900);
		JPanel panel = new JPanel();// 创建面板
		this.add(panel);
		
		//设计面板
		setJPanel(panel);	
		
		//开启点击事件监听
		clickAction();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 点击事件
	 */
	public void clickAction() {
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str=area1.getText();
				if(!str.equals("")) {
					ReadRule readrule=new ReadRule();
					//规则化输入的因子和水平
					readrule.analysisStr(str);
					FileRead fileread=new FileRead();
					try {
						//将读取出来的规则和正则表的规则匹配，查询是否存在
						fileread.query(readrule.getRulestr());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(fileread.getHave()) {	//规则存在，结果输入
						CreatUseCase cuc=new CreatUseCase();
						cuc.create(readrule.getListrule(), readrule.getMap(), readrule.getSpecial(), fileread.getRuletable());
						label4.setText(fileread.getRulestr());
						area2.setText(fileread.getRuletable());
						label5.setText(cuc.getLabel5show());
						area3.setText(cuc.getUsecase());
					}else {	//规则不存在，结果输出
						label4.setText("正交表中没有此规则！");
						area2.setText("");
						area3.setText("");
					}
				}
			}
		});
	}

	/**
	 * 面板设计
	 */
	public void setJPanel(JPanel panel) {
		panel.setLayout(null);// 设置绝对布局
		// label1显示输入提示
		JLabel label1 = new JLabel("使用:分开因子和水平，使用,分开各水平");
		label1.setFont(new Font("宋体", Font.BOLD, 16));
		label1.setBounds(10, 0, 800, 30);
		panel.add(label1);

		area1 = new JTextArea();
		area1.setFont(new Font("宋体", 0, 20));
		JScrollPane scrollpane1 = new JScrollPane(area1);	//将TextArea包装到JScrollPane中实现滚轮效果
		scrollpane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//设置水平滚动条总是出现(默认自动)
		scrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置垂直滚动条总是出现
		scrollpane1.setBounds(10, 40, 760, 200);
		panel.add(scrollpane1);

		JLabel label2 = new JLabel("正交表:");
		label2.setFont(new Font("宋体", Font.BOLD, 16));
		label2.setBounds(10, 250, 100, 30);
		panel.add(label2);

		area2 = new JTextArea(1, 1);
		area2.setFont(new Font("宋体", 0, 20));
		JScrollPane scrollpane2 = new JScrollPane(area2);	//将TextArea包装到JScrollPane中实现滚轮效果
		scrollpane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//设置水平滚动条总是出现(默认自动)
		scrollpane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置垂直滚动条总是出现
		scrollpane2.setBounds(10, 290, 760, 200);
		panel.add(scrollpane2);

		JLabel label3 = new JLabel("正交表生成的测试用例:");
		label3.setFont(new Font("宋体", Font.BOLD, 16));
		label3.setBounds(10, 500, 200, 30);
		panel.add(label3);

		area3 = new JTextArea(1, 1);
		area3.setFont(new Font("宋体", 0, 20));
		JScrollPane scrollpane3 = new JScrollPane(area3);	//将TextArea包装到JScrollPane中实现滚轮效果
		scrollpane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//设置水平滚动条总是出现(默认自动)
		scrollpane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置垂直滚动条总是出现
		scrollpane3.setBounds(10, 540, 760, 300);
		panel.add(scrollpane3);

		create = new JButton("生成测试用例");
		create.setBounds(600, 5, 150, 30);
		panel.add(create);
		
		label4=new JLabel();
		label4.setFont(new Font("宋体", Font.BOLD, 16));
		label4.setBounds(110, 250, 500, 30);
		panel.add(label4);
		
		label5=new JLabel();
		label5.setFont(new Font("宋体", Font.BOLD, 16));
		label5.setBounds(220, 500, 500, 30);
		panel.add(label5);
	}
}

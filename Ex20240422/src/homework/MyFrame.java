package homework;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id_tf;
	private JPasswordField pw_tf;
	CardLayout card;
	SqlSessionFactory factory;
	private JTextField textField_emp;
	private JTable table;
	JComboBox comboBox_emp, comboBox_dept, comboBox_loca;
	List<EmpVO> list;
	List<DeptVO> list2;
	List<LocaVO> list3;

	boolean login_chk;
	private JTextField textField_dept;
	private JTable table2;
	private JTable table3;
	private JTextField textField_loca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 472);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Control");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("사원 정보");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (login_chk) {
					totalEmp(null);
					card.show(contentPane, "card2");
				} else {
					JOptionPane.showMessageDialog(MyFrame.this, "로그인이 필요합니다.");
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("부서 정보");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (login_chk) {
					totalDept(null);
					viewTable2();
					card.show(contentPane, "card3");
				} else {
					JOptionPane.showMessageDialog(MyFrame.this, "로그인이 필요합니다.");
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("도시 정보");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (login_chk) {
					totalLoca(null);
					viewTable3();
					card.show(contentPane, "card4");
				} else {
					JOptionPane.showMessageDialog(MyFrame.this, "로그인이 필요합니다.");
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		card = new CardLayout();
		contentPane.setLayout(card);

		JPanel panel = new JPanel();
		contentPane.add(panel, "card1");
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MyFrame.class.getResource("/images/chat.png")));
		panel.add(lblNewLabel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("ID:");
		panel_2.add(lblNewLabel_1);

		id_tf = new JTextField();
		panel_2.add(id_tf);
		id_tf.setColumns(10);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_3);

		JLabel lblNewLabel_2 = new JLabel("PW:");
		panel_3.add(lblNewLabel_2);

		pw_tf = new JPasswordField();
		pw_tf.setColumns(10);
		panel_3.add(pw_tf);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_4);

		JButton login_bt = new JButton("LOGIN");
		login_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 사용자가 입력한 아이디와 비밀번호를 가져온다.
				String id = id_tf.getText().trim();
				String pw = new String(pw_tf.getPassword()).trim();

				// 아이디와 비밀번호가 입력되었는지? 유효성 검사
				if (id.length() < 1) {
					JOptionPane.showMessageDialog(MyFrame.this, "아이디를 입력하세요");
					id_tf.setText("");// 입력란 지워주기
					id_tf.requestFocus();// 커서 가져다 놓기
					return;
				}

				if (pw.length() < 1) {
					JOptionPane.showMessageDialog(MyFrame.this, "비밀번호를 입력하세요");
					pw_tf.setText("");// 입력란 지워주기
					pw_tf.requestFocus();// 커서 가져다 놓기
					return;
				}

				// 아이디와 비밀번호 모두 입력한 상황일 때만 현재 위치로 온다.
				MemberVO vo = login(id, pw);

				if (vo != null) {
					login_chk = true;
					// emp테이블에 있는 모든 자원들을 가져오는
					// 기능을 호출한다.
					totalEmp(null);

					// 현재 창의 contentPane을 얻어내어
					// Layout을 얻어낸 후
					// 원하는 카드를 보여달라고 하면 된다.
					card.show(contentPane, "card2");
				} else {
					// vo가 null이라는 것은 아이디 또는
					// 비밀번호가 맞지 않은 경우다.
					JOptionPane.showMessageDialog(MyFrame.this, "아이디 또는 비밀번호가 틀립니다.");
				}
			}
		});
		panel_4.add(login_bt);

		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5, "card2");
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_6.getLayout();
		panel_5.add(panel_6, BorderLayout.NORTH);

		comboBox_emp = new JComboBox();
		comboBox_emp.setModel(new DefaultComboBoxModel(new String[] { "사번", "이름", "직종", "부서" }));
		panel_6.add(comboBox_emp);

		textField_emp = new JTextField();
		textField_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData("emp");
			}
		});
		panel_6.add(textField_emp);
		textField_emp.setColumns(10);

		JButton search_btn1 = new JButton("검색");
		search_btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData("emp");
			}
		});
		panel_6.add(search_btn1);

		JButton add_bt = new JButton("추가");
		add_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmpDialog(MyFrame.this);
			}
		});
		panel_6.add(add_bt);

		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 선택된 행의 번호를 얻어낸다.
				int row = table.getSelectedRow();
				// int col = table.getSelectedColumn();

				// System.out.println(row+","+col);

				// 멤버변수인 list에서 row번째에 있는 EmpVO를
				// 얻어낸다.
				EmpVO vo = list.get(row);
				// System.out.println(vo.getEname());
				/*
				 * String n = (String) table.getValueAt(row, 1); System.out.println(n);
				 */
				EmpDialog md = new EmpDialog(vo);
			}
		});
		scrollPane.setViewportView(table);

		JPanel panel_5_1 = new JPanel();
		contentPane.add(panel_5_1, "card3");
		panel_5_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_6_1 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_6_1.getLayout();
		panel_5_1.add(panel_6_1, BorderLayout.NORTH);

		comboBox_dept = new JComboBox();
		comboBox_dept.setModel(new DefaultComboBoxModel(new String[] { "부서코드", "부서명", "도시코드" }));
		panel_6_1.add(comboBox_dept);

		textField_dept = new JTextField();
		textField_dept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData("dept");
			}
		});
		textField_dept.setColumns(10);
		panel_6_1.add(textField_dept);

		JButton search_btn2 = new JButton("검색");
		search_btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData("dept");
			}
		});
		panel_6_1.add(search_btn2);

		JButton add_bt_1 = new JButton("추가");
		add_bt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeptDialog(MyFrame.this);
			}
		});
		panel_6_1.add(add_bt_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5_1.add(scrollPane_1, BorderLayout.CENTER);

		table2 = new JTable();
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = table2.getSelectedRow();

				DeptVO vo = list2.get(row);

				DeptDialog md = new DeptDialog(vo);
			}
		});
		scrollPane_1.setViewportView(table2);

		JPanel panel_5_2 = new JPanel();
		contentPane.add(panel_5_2, "card4");
		panel_5_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_6_2 = new JPanel();
		panel_5_2.add(panel_6_2, BorderLayout.NORTH);

		comboBox_loca = new JComboBox();
		comboBox_loca.setModel(new DefaultComboBoxModel(new String[] {"도시코드", "도시명"}));
		panel_6_2.add(comboBox_loca);

		textField_loca = new JTextField();
		textField_loca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData("loca");
			}
		});
		panel_6_2.add(textField_loca);
		textField_loca.setColumns(10);

		JButton search_btn3 = new JButton("검색");
		search_btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData("loca");
			}
		});
		panel_6_2.add(search_btn3);

		JButton add_bt_2 = new JButton("추가");
		add_bt_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LocaDialog(MyFrame.this);
			}
		});
		panel_6_2.add(add_bt_2);

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_5_2.add(scrollPane_2, BorderLayout.CENTER);

		table3 = new JTable();
		table3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table3.getSelectedRow();
				LocaVO vo = list3.get(row);
				LocaDialog md = new LocaDialog(vo);
			}
		});
		scrollPane_2.setViewportView(table3);
		// DB연결을 위해 factory만들기
		try {
			makeFactory();
			this.setTitle("준비완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void makeFactory() throws Exception {
		// 환경설정파일과 연결된 스트림 준비
		Reader r = Resources.getResourceAsReader("homework/config.xml");
		factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
	}

	private MemberVO login(String id, String pw) {
		// 인자로 넘어온 id와 pw를 Map구조에 담아서
		// mapper인 member.login이라는 SQL문을 수행하는 것이
		// 목적이다.
		SqlSession ss = factory.openSession();

		Map<String, String> map = new HashMap<>();
		map.put("key_id", id);
		map.put("key_pw", pw);// 앞의 키가 mybatis의
		// sql문장에서 mybqtis변수로 쓰인다.
		MemberVO vo = ss.selectOne("member.login", map);

		if (ss != null)
			ss.close();

		return vo;
	}

	public void totalEmp(Map<String, String> map) {
		// MyBatis환경의 SQL문을 호출하기 위해
		// SqlSession을 준비하자
		SqlSession ss = factory.openSession();
		list = ss.selectList("emp.search", map);

		// 받은 list를 JTable로 표현해야 한다.
		viewTable();

		if (ss != null)
			ss.close();
	}

	public void totalDept(Map<String, String> map) {
		// MyBatis환경의 SQL문을 호출하기 위해
		// SqlSession을 준비하자
		SqlSession ss = factory.openSession();
		list2 = ss.selectList("dept.search", map);

		// 받은 list를 JTable로 표현해야 한다.
		viewTable2();

		if (ss != null)
			ss.close();
	}

	public void totalLoca(Map<String, String> map) {
		// MyBatis환경의 SQL문을 호출하기 위해
		// SqlSession을 준비하자
		SqlSession ss = factory.openSession();
		list3 = ss.selectList("loca.search", map);

		// 받은 list를 JTable로 표현해야 한다.
		viewTable3();

		if (ss != null)
			ss.close();
	}

	private void viewTable() {
		String[] c_name = { "사번", "이름", "직종", "부서코드" };

		// 인자로 받은 list를 2차원배열로 만들어보자!
		String[][] data = new String[list.size()][c_name.length];

		for (int i = 0; i < list.size(); i++) {
			// list로부터 EmpVO를 하나 얻어낸다.
			EmpVO vo = list.get(i);

			// 얻어낸 사원 정보를 JTable에 하나의 행으로 표현하기
			// 위해 1차원배열에 채워 넣는다.
			data[i][0] = vo.getEmpno();// 사번
			data[i][1] = vo.getEname();// 이름
			data[i][2] = vo.getJob();// 직종
			data[i][3] = vo.getDeptno();// 부서코드
		} // for의 끝
		table.setModel(new DefaultTableModel(data, c_name));
	}

	private void viewTable2() {
		String[] c_name = { "부서코드", "부서명", "도시코드" };

		// 인자로 받은 list를 2차원배열로 만들어보자!
		String[][] data = new String[list2.size()][c_name.length];

		for (int i = 0; i < list2.size(); i++) {
			// list로부터 EmpVO를 하나 얻어낸다.
			DeptVO vo = list2.get(i);

			// 얻어낸 사원 정보를 JTable에 하나의 행으로 표현하기
			// 위해 1차원배열에 채워 넣는다.
			data[i][0] = vo.getDeptno();// 부서코드
			data[i][1] = vo.getDname();// 부서명
			data[i][2] = vo.getLoc_code();// 도시코드
		} // for의 끝
		table2.setModel(new DefaultTableModel(data, c_name));
	}

	private void viewTable3() {
		String[] c_name = { "도시코드", "도시명" };

		// 인자로 받은 list를 2차원배열로 만들어보자!
		String[][] data = new String[list3.size()][c_name.length];

		for (int i = 0; i < list3.size(); i++) {
			// list로부터 EmpVO를 하나 얻어낸다.
			LocaVO vo = list3.get(i);

			// 얻어낸 사원 정보를 JTable에 하나의 행으로 표현하기
			// 위해 1차원배열에 채워 넣는다.
			data[i][0] = vo.getLoc_code();// 부서코드
			data[i][1] = vo.getCity();// 부서명
		} // for의 끝
		table3.setModel(new DefaultTableModel(data, c_name));
	}

	private void searchData(String control) {
		// 1)콤보박스에 선택된 index값 얻기
		int idx = 0;
		// System.out.println(idx);
		String[] keyList = null;
		String str = null;
		switch (control) {
		case "emp" :
			idx = comboBox_emp.getSelectedIndex();
			keyList  = new String[]{"empno","ename","job","deptno"};
			str = textField_emp.getText().trim();
			break;
		case "dept":
			idx = comboBox_dept.getSelectedIndex();
			keyList  = new String[]{"deptno","dname","loc_code"};
			str = textField_dept.getText().trim();
			break;
		case "loca":
			idx = comboBox_loca.getSelectedIndex();
			keyList  = new String[]{"loc_code","city"};
			str = textField_loca.getText().trim();
			break;
		}
		
		String key = new String(keyList[idx]);
		
		// 2)텍스트필드에서 사용자가 입력한 검색어를 얻기
		
		if (str.length() > 0) {
			// 3)emp.search에 전달할 인자 Map객체 준비
			Map<String, String> map = new HashMap<String, String>();

			// 4)준비된 Map에 콤보박스에서 얻은 index값이
			// 0이면 empno라는 key에 2)의 검색어를 저장
			// 1이면 ename라는 key에 2)의 검색어를 저장
			// 2이면 job라는 key에 2)의 검색어를 저장
			// 3이면 deptno라는 key에 2)의 검색어를 저장
			
			map.put(key, str);
			/*
			 * SqlSession ss = factory.openSession(); List<EmpVO> list = ss.selectList(
			 * "emp.search", map); viewTable(list);
			 */
			switch (control) {
			case "emp" :
				totalEmp(map);
				break;
			case "dept":
				totalDept(map);
				break;
			case "loca":
				totalLoca(map);
				break;
			}
			
			
		}
	}

	// 추가하는 기능 : MyDialog에서 저장버튼을 클릭했을 때 호출
	public int addEmp(EmpVO vo) {
		SqlSession ss = factory.openSession();
		// 기본적으로 factory를 통해 openSession()을 호출하면
		// commit이 false이며 openSession(true)을 호출하면
		// 자동 commit이 적용된다.

		int cnt = ss.insert("emp.add", vo);
		if (cnt > 0)
			ss.commit();
		else
			ss.rollback();

		// cnt가 0보다 크다면 저장성공!
		if (ss != null)
			ss.close();

		return cnt;
	}
	
	public int addDept(DeptVO vo) {
		SqlSession ss = factory.openSession();
		// 기본적으로 factory를 통해 openSession()을 호출하면
		// commit이 false이며 openSession(true)을 호출하면
		// 자동 commit이 적용된다.

		int cnt = ss.insert("dept.add", vo);
		if (cnt > 0)
			ss.commit();
		else
			ss.rollback();

		// cnt가 0보다 크다면 저장성공!
		if (ss != null)
			ss.close();

		return cnt;
	}
	
	public int addLoca(LocaVO vo) {
		SqlSession ss = factory.openSession();
		// 기본적으로 factory를 통해 openSession()을 호출하면
		// commit이 false이며 openSession(true)을 호출하면
		// 자동 commit이 적용된다.

		int cnt = ss.insert("loca.add", vo);
		if (cnt > 0)
			ss.commit();
		else
			ss.rollback();

		// cnt가 0보다 크다면 저장성공!
		if (ss != null)
			ss.close();

		return cnt;
	}
	
}// 클래스의 끝

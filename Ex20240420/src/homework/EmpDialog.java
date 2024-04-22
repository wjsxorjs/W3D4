package homework;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EmpDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField empno_tf;
	private JTextField ename_tf;
	private JTextField job_tf;
	private JTextField mgr_tf;
	private JTextField sal_tf;
	private JTextField hiredate_tf;
	private JTextField comm_tf;
	private JTextField deptno_tf;

	EmpVO vo;
	JButton okButton;
	JButton cancelButton;
	
	MyFrame f;// [저장]버튼을 클릭할 때 사용자가 입력한
	// 사원의 정보를 EmpVO로 만든 후 MyFrame의 addEmp를
	// 호출하기 위해 필요함

	/**
	 * Create the dialog.
	 */
	public EmpDialog(EmpVO vo) {
		this.vo = vo;
		
		init();
		
		empno_tf.setText(vo.getEmpno());//사번
		ename_tf.setText(vo.getEname());//이름
			
		
	}//생성자의 끝
	
	public EmpDialog(MyFrame f) {
		this.f = f;
		init();
		
		// 사번입력란을 활성화 시킨다.
		empno_tf.setEditable(true);
		
		okButton.setText("저장");
		cancelButton.setText("취소");
		
		//이벤트 감지자 등록
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 사용자가 입력한 사원의 정보들을 받아낸다.
				
				// null 허용 X
				String empno = 
					empno_tf.getText().trim();
				if(empno.length() == 0)
					empno = null;
				
				String ename =
					ename_tf.getText().trim();
				if(ename.length() == 0)
					ename = null;
				
				String job = 
					job_tf.getText().trim();
				if(job.length() == 0)
					job = null;

				String deptno =
					deptno_tf.getText().trim();
				if(deptno.length() == 0)
					deptno = null;
				
				// null 허용 O
				String mgr =
					mgr_tf.getText().trim();
				if(mgr.length() == 0)
					mgr = null;
				
				String sal =
					sal_tf.getText().trim();
				if(sal.length() == 0)
					sal = null;
				
				String hiredate =
					hiredate_tf.getText().trim();
				if(hiredate.length() == 0)
					hiredate = null;
				
				String comm =
					comm_tf.getText().trim();
				if(comm.length() == 0)
					comm = null;
				
				
				
				EmpVO vo = new EmpVO(empno, ename, 
					job, mgr, sal, hiredate, 
					comm, deptno);
				
				//MyFrame의 addEmp함수 호출
				
				int cnt = 0;
				try {
					cnt = f.addEmp(vo);// DB에 저장하는 기능!
				} catch (Exception e2) {
//					e2.printStackTrace();
				}
				
				if(cnt > 0) {
					f.totalEmp(null);
					
					JOptionPane.showMessageDialog(
						EmpDialog.this,"저장완료!");
					dispose();
				} else {
					StringBuffer sb = new StringBuffer("해당 오류는 아래 이유 중 하나로 발생합니다.\n\n");
					sb.append(" 1) 필수값을 모두 입력하지 않았습니다.\n\n");
					sb.append(" 2) 사번이 중복되었습니다.\n\n");
					sb.append(" 3) 입력된 자료형이 잘못되었습니다.\n\n");
					sb.append(" 4) 존재하지 않는 부서코드입니다.\n\n");
					String exMsg = sb.toString();
					JOptionPane.showMessageDialog(EmpDialog.this, exMsg,
					"오류! DB에 저장할 수 없습니다.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 취소는 대화창을 닫는다.
				dispose();
			}
		});
	}
	
	private void init() {
		setBounds(300, 120, 163, 321);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(8, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("*사번");
				panel.add(lblNewLabel);
			}
			{
				empno_tf = new JTextField();
				empno_tf.setEditable(false);
				panel.add(empno_tf);
				empno_tf.setColumns(6);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("*이름");
				panel_1.add(lblNewLabel);
			}
			{
				ename_tf = new JTextField();
				ename_tf.setColumns(6);
				panel_1.add(ename_tf);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("*직종");
				panel_1.add(lblNewLabel);
			}
			{
				job_tf = new JTextField();
				job_tf.setColumns(6);
				panel_1.add(job_tf);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("부서장");
				panel_1.add(lblNewLabel);
			}
			{
				mgr_tf = new JTextField();
				mgr_tf.setColumns(6);
				panel_1.add(mgr_tf);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("급여");
				panel_1.add(lblNewLabel);
			}
			{
				sal_tf = new JTextField();
				sal_tf.setColumns(6);
				panel_1.add(sal_tf);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("입사일");
				panel_1.add(lblNewLabel);
			}
			{
				hiredate_tf = new JTextField();
				hiredate_tf.setColumns(6);
				panel_1.add(hiredate_tf);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("보너스");
				panel_1.add(lblNewLabel);
			}
			{
				comm_tf = new JTextField();
				comm_tf.setColumns(6);
				panel_1.add(comm_tf);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("*부서코드");
				panel_1.add(lblNewLabel);
			}
			{
				deptno_tf = new JTextField();
				deptno_tf.setColumns(6);
				panel_1.add(deptno_tf);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		
			
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
		}
	}
}

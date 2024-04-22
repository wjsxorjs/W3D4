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

public class DeptDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField deptno_tf;
	private JTextField dname_tf;
	private JTextField loc_code_tf;

	DeptVO vo;
	JButton okButton;
	JButton cancelButton;
	
	MyFrame f;// [저장]버튼을 클릭할 때 사용자가 입력한
	// 사원의 정보를 EmpVO로 만든 후 MyFrame의 addEmp를
	// 호출하기 위해 필요함

	/**
	 * Create the dialog.
	 */
	public DeptDialog(DeptVO vo) {
		this.vo = vo;
		
		init();
		
		deptno_tf.setText(vo.getDeptno());//부서번호
		dname_tf.setText(vo.getDname());//부서이름
		loc_code_tf.setText(vo.getLoc_code());//도시코드
			
		
	}//생성자의 끝
	
	public DeptDialog(MyFrame f) {
		this.f = f;
		init();
		
		// 사번입력란을 활성화 시킨다.
		deptno_tf.setEditable(true);
		
		okButton.setText("저장");
		cancelButton.setText("취소");
		
		//이벤트 감지자 등록
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 사용자가 입력한 사원의 정보들을 받아낸다.
				String deptno = 
					deptno_tf.getText().trim();
				if(deptno.length() == 0)
					deptno = null;
				String dname =
					dname_tf.getText().trim();
				if(dname.length() == 0)
					dname = null;
				String loc_code = 
					loc_code_tf.getText().trim();
				if(loc_code.length() == 0)
					loc_code = null;
				
				
				DeptVO vo = new DeptVO(deptno,dname,loc_code);
				
				//MyFrame의 addEmp함수 호출
				
				int cnt = 0;
				try {
					cnt = f.addDept(vo);// DB에 저장하는 기능!
				} catch (Exception e2) {
//					e2.printStackTrace();
				}
				
				
				
				
				if(cnt > 0) {
					f.totalDept(null);
					
					JOptionPane.showMessageDialog(
						DeptDialog.this,"저장완료!");
					dispose();
				} else {
					StringBuffer sb = new StringBuffer("해당 오류는 아래 이유 중 하나로 발생합니다.\n\n");
					sb.append(" 1) 필수값을 모두 입력하지 않았습니다.\n\n");
					sb.append(" 2) 부서코드가 중복되었습니다.\n\n");
					sb.append(" 3) 입력된 자료형이 잘못되었습니다.\n\n");
					sb.append(" 4) 존재하지 않는 도시코드입니다.\n\n");
					String exMsg = sb.toString();
					JOptionPane.showMessageDialog(DeptDialog.this, exMsg,
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
				JLabel lblNewLabel = new JLabel("*부서코드");
				panel.add(lblNewLabel);
			}
			{
				deptno_tf = new JTextField();
				deptno_tf.setEditable(false);
				panel.add(deptno_tf);
				deptno_tf.setColumns(6);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("*부서명");
				panel_1.add(lblNewLabel);
			}
			{
				dname_tf = new JTextField();
				dname_tf.setColumns(6);
				panel_1.add(dname_tf);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("*도시코드");
				panel_1.add(lblNewLabel);
			}
			{
				loc_code_tf = new JTextField();
				loc_code_tf.setColumns(6);
				panel_1.add(loc_code_tf);
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

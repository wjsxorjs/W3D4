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
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LocaDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField loc_code_tf;
	private JTextField city_tf;

	LocaVO vo;
	JButton okButton;
	JButton cancelButton;
	
	MyFrame f;// [저장]버튼을 클릭할 때 사용자가 입력한
	// 사원의 정보를 EmpVO로 만든 후 MyFrame의 addEmp를
	// 호출하기 위해 필요함

	/**
	 * Create the dialog.
	 */
	public LocaDialog(LocaVO vo) {
		this.vo = vo;
		
		init();
		
		loc_code_tf.setText(vo.getLoc_code());//사번
		city_tf.setText(vo.getCity());//이름
			
		
	}//생성자의 끝
	
	public LocaDialog(MyFrame f) {
		this.f = f;
		init();
		
		// 사번입력란을 활성화 시킨다.
		loc_code_tf.setEditable(true);
		
		okButton.setText("저장");
		cancelButton.setText("취소");
		
		//이벤트 감지자 등록
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 사용자가 입력한 사원의 정보들을 받아낸다.
				String loc_code = 
					loc_code_tf.getText().trim();
				if(loc_code.length() == 0)
					loc_code = null;
				String city =
					city_tf.getText().trim();
				if(city.length() == 0)
					city = null;
				
				
				LocaVO vo = new LocaVO(loc_code, city);
				
				//MyFrame의 addEmp함수 호출
				
				int cnt =  0 ;
				
				try {
					cnt = f.addLoca(vo);// DB에 저장하는 기능!
				} catch (Exception e2) {
//					e2.printStackTrace();
				}
				
				
				
				if(cnt > 0) {
					f.totalLoca(null);
					
					JOptionPane.showMessageDialog(
						LocaDialog.this,"저장완료!");
					dispose();
				} else {
					StringBuffer sb = new StringBuffer("해당 오류는 아래 이유 중 하나로 발생합니다.\n\n");
					sb.append(" 1) 필수값을 모두 입력하지 않았습니다.\n\n");
					sb.append(" 2) 도시코드가 중복되었습니다.\n\n");
					sb.append(" 3) 입력된 자료형이 잘못되었습니다.\n\n");
					String exMsg = sb.toString();
					JOptionPane.showMessageDialog(LocaDialog.this, exMsg,
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
				JLabel lblNewLabel = new JLabel("*도시코드");
				panel.add(lblNewLabel);
			}
			{
				loc_code_tf = new JTextField();
				loc_code_tf.setEditable(false);
				panel.add(loc_code_tf);
				loc_code_tf.setColumns(6);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel = new JLabel("*도시명");
				panel_1.add(lblNewLabel);
			}
			{
				city_tf = new JTextField();
				city_tf.setColumns(6);
				panel_1.add(city_tf);
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

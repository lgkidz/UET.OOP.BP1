package app.bp1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class About_us extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame. Actually all plain text, nothing to comment
	 */
	public About_us() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("About us");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 475, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLetsLearnEnglish = new JLabel("Let's Learn English!");
		lblLetsLearnEnglish.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel lblLetsLearnEnglish_1 = new JLabel("Let's Learn English\u00A9 2017  ");
		
		JLabel lblThisSmallApp = new JLabel("This small app to help you learn English was originaly developed by a team of 2 members:");
		
		JLabel lblNewLabel = new JLabel("Nguy\u1EC5n B\u00ECnh D\u01B0\u01A1ng - 1600913");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblContactUsAt = new JLabel("Contact us at : letslearnenglish@gmail.com\r\n");
		
		JLabel lblTel = new JLabel("Tel : 555 - xxx - xxx");
		
		JLabel lblCaocMnh = new JLabel("Cao \u0110\u1EE9c M\u1EA1nh - 16021042");
		
		JLabel lblCheers = new JLabel("Cheers!");
		lblCheers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(149)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLetsLearnEnglish_1)
						.addComponent(lblLetsLearnEnglish))
					.addContainerGap(165, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addComponent(lblThisSmallApp)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(106)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContactUsAt)
						.addComponent(lblTel)
						.addComponent(lblNewLabel)
						.addComponent(lblCaocMnh)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(83)
							.addComponent(lblCheers)))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLetsLearnEnglish)
					.addGap(19)
					.addComponent(lblThisSmallApp)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCaocMnh)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblContactUsAt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTel)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(lblCheers)
					.addGap(18)
					.addComponent(lblLetsLearnEnglish_1)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

}

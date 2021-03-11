package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ingame.CookieImg;
import panels.GamePanel;
import panels.IntroPanel;
import panels.SelectPanel;
import main.listenAdapter;

import java.awt.CardLayout;

// windowBuilder �� �����Ӹ� �����ϰ� �������� �Է�

public class Main extends listenAdapter {
	

	private JFrame frame; // â�� ���� ���� ������

	private IntroPanel introPanel; // ��Ʈ��

	private SelectPanel selectPanel; // ĳ���� ����

	private GamePanel gamePanel; // ��������


	private CardLayout cl; // ī�� �����̿� ������Ʈ

	private CookieImg ci; // ��Ű�̹���

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1000, 700); // â ������ (100,100��ǥ�� �Ʒ��� frame.setLocationRelativeTo(null) ������ �ǹ̰� ��������)
		frame.setLocationRelativeTo(null); // â�� ȭ�� �߾ӿ� ��ġ
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ư�� ������ ����
		cl = new CardLayout(0, 0); // ī�巹�̾ƿ� ��ü ����
		frame.getContentPane().setLayout(cl); // �������� ī�巹�̾ƿ����� ����

		introPanel = new IntroPanel();
		introPanel.addMouseListener(this); // intro�г��� ���⼭ �ٷ� �ִ� ������� ���콺�����ʸ� �߰���.
		
		selectPanel = new SelectPanel(this); // Main�� �����ʸ� �ֱ����� this
		gamePanel = new GamePanel(frame, cl, this); // Main�� ������ �� ī�巹�̾ƿ��� �̿��ϰ� �����ʸ� �ֱ����� this

		// ��� �г��� ���̾ƿ��� null�� ��ȯ
		introPanel.setLayout(null);
		selectPanel.setLayout(null);
		gamePanel.setLayout(null);

		// �����ӿ� �гε��� �߰��Ѵ�.(ī�� ���̾ƿ��� ���� �гε�)
		frame.getContentPane().add(introPanel, "intro");
		frame.getContentPane().add(selectPanel, "select");
		frame.getContentPane().add(gamePanel, "game");


	}

	@Override
	public void mousePressed(MouseEvent e) { // mouseClicked�� ���氡��
		if (e.getComponent().toString().contains("IntroPanel")) { // IntroPanel���� ���콺�� �����ٸ�
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			cl.show(frame.getContentPane(), "select"); // select�г��� ī�巹�̾ƿ� �ֻ������ ����
			selectPanel.requestFocus(); // �����ʸ� select�гο� ������ �� 
		} else if (e.getComponent().getName().equals("StartBtn")) { // StartBtn�̶�� �̸��� ���� ��ư�� �����ٸ�
			selectPanel.setBtnImgClicked();
			if (selectPanel.Logined() == 0) {
				cl.show(frame.getContentPane(), "game"); // ĳ���͸� ����ٸ� �����г��� ī�巹�̾ƿ� �ֻ������ ����
				gamePanel.gameSet(); // ��Ű�̹����� �Ѱ��ְ� �����г� ����
				gamePanel.gameStart(); // ���ӽ���
				gamePanel.requestFocus(); // �����ʸ� game�гο� ������ ��
			} else {
				JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			}
			
		} else if (e.getComponent().getName().equals("endAccept")) { // endAccept �̶�� �̸��� ���� ��ư�� �����ٸ�
			frame.getContentPane().remove(gamePanel); // ��� �ߴ� ���� �г��� �����ӿ��� ����
			gamePanel = new GamePanel(frame, cl, this); // �����г��� �� �гη� ��ü
			gamePanel.setLayout(null);
			frame.getContentPane().add(gamePanel, "game"); // �����ӿ� �� �����г� �߰�(ī�巹�̾ƿ� �ϴ�)
			
			frame.getContentPane().remove(selectPanel); // ��� �����ߴ� select�г��� ����
			selectPanel = new SelectPanel(this); // select �г��� �� �гη� ��ü
			selectPanel.setLayout(null);
			frame.getContentPane().add(selectPanel, "select"); // �����ӿ� �� select�г� �߰�(ī�巹�̾ƿ� �ϴ�)
			cl.show(frame.getContentPane(), "select"); // �� select�г��� ī�巹�̾ƿ� �ֻ������ �̵� (ȭ�鿡 ����)
			selectPanel.requestFocus(); // �����ʸ� select�гο� ������ ��
		}
	}
}

package com.codefans.swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DragingFrame extends JFrame {

	public DragingFrame() throws HeadlessException {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new ImagePanel(), BorderLayout.CENTER);

	}

	// 程序入口

	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		DragingFrame frame = new DragingFrame();

		frame.setSize(400, 300);

		frame.setLocation(300, 300);

		frame.setResizable(false);

		frame.setTitle("双击打开图片，然后拖拽");

		frame.setVisible(true);

	}

}

/**
 * 
 * 能够拖拽图片的面板
 * 
 */

class ImagePanel extends JPanel {

	private DragStatus status = DragStatus.Ready; // 拖拽状态

	private Image image; // 要显示的图片

	private Point imagePosition = new Point(0, 0), // 图片的当前 位置

			imageStartposition = new Point(0, 0), // 每 次拖拽开始时图片的位置（也就是上次拖拽后的位置）

			mouseStartposition; // 每次拖拽开始时鼠标的位置

	ImagePanel() {

		addMouseListener(new MouseListener() {

			// 双击鼠标时打开图片

			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					openImage();

				}

			}

			// 按下鼠标时，更改状态，并且记录拖拽起始位置。

			public void mousePressed(MouseEvent e) {

				if (status == DragStatus.Ready) {

					status = DragStatus.Dragging;

					mouseStartposition = e.getPoint();

					imageStartposition.setLocation(imagePosition.getLocation());

				}

			}

			// 松开鼠标时更改状态

			public void mouseReleased(MouseEvent e) {

				if (status == DragStatus.Dragging) {

					status = DragStatus.Ready;

				}

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

		});

		addMouseMotionListener(new MouseMotionListener() {

			// Java 有拖拽事件，在这个事件中移动图片位置

			public void mouseDragged(MouseEvent e) {

				if (status == DragStatus.Dragging) {

					moveImage(e.getPoint());

				}

			}

			public void mouseMoved(MouseEvent e) {

			}

		});

	}

	/**
	 * 
	 * 移动图片。实际上画图工作在 paintComponent() 中进行，这里只是计 算图片位置，然后调用该方法。
	 *
	 * 
	 * 
	 * @param point
	 *            当前的鼠标位置
	 * 
	 */

	private void moveImage(Point point) {

		// 图片的当前位置等于图片的起始位置加上鼠标位置的偏移量。

		imagePosition.setLocation(

				imageStartposition.getX() + (point.getX() - mouseStartposition.getX()),

				imageStartposition.getY() + (point.getY() - mouseStartposition.getY())

		);

		repaint();

	}

	// 打开图片

	private void openImage() {

		System.out.println("Opening image...");

		File file = createFileChooser().getSelectedFile();

		if (file != null) {

			image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());

			if (image != null) {

				this.repaint();

			}

		}

	}

	// 创建打开文件对话框

	private JFileChooser createFileChooser() {

		JFileChooser chooser = new JFileChooser();

		chooser.setDialogTitle("请选择图片文件...");

		chooser.addChoosableFileFilter(new FileNameExtensionFilter("常用图片格式", "jpg", "jpeg", "gif", "png"));

		chooser.showOpenDialog(this);

		return chooser;

	}

	@Override

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (image != null) {

			g.drawImage(image, (int) imagePosition.getX(), (int) imagePosition.getY(), this);

		}

	}

	private enum DragStatus {

		Ready, Dragging

	}

}

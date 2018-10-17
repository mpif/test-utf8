package com.test;

import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

//本程序只能打开图片 文件 和显示图片 文件

public class ImageTest extends JFrame {
	private JFileChooser chooser = new JFileChooser(".");
	private FileInputStream readfile;
	private JTextPane jTextPane1 = new JTextPane();
	private JScrollPane jsp = new JScrollPane(jTextPane1);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem open = new JMenuItem("open");
	private JMenuItem save = new JMenuItem("save");

	// 只有部分扩展名的文件是可以显示的。
	private String s = "jpg,bmp,tga,vst,pcd,pct,gif,ai,fpx,img,cal,wi,png";
	private ImageIcon icon;
	private File file;

	public ImageTest() {
		this.setSize(800, 600);
		this.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(open);
		fileMenu.add(save);
		this.add(jsp);

		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton3_actionPerformed(evt);
			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton4_actionPerformed(evt);
			}
		});
	}

	public static void main(String args[]) throws Exception {
		ImageTest test = new ImageTest();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

	public void jButton3_actionPerformed(ActionEvent e) {
		int state = chooser.showOpenDialog(null);
		file = chooser.getSelectedFile();
		if (file != null && state == JFileChooser.APPROVE_OPTION) {
			try {
				String fileName = file.getName();
				jTextPane1.setText(null);
				if (s.indexOf(fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase()) != -1) {
					icon = new ImageIcon(file.getAbsolutePath());
					jTextPane1.insertIcon(icon);
				} else {
					readfile = new FileInputStream(file);
					jTextPane1.read(readfile, file.getName());
				}
				setTitle(chooser.getSelectedFile().getName());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	// 文件保存
	public void jButton4_actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String savefile = fc.getSelectedFile().getPath();
			if (savefile == null) {
				return;
			} else {
				if (icon != null) {
					try {
						FileOutputStream out = new FileOutputStream(savefile);
						out.write(getBytesFromFile(file));
						out.close();
					} catch (Exception ex) {
					}
				} else {
					String docToSave = jTextPane1.getText();
					if (docToSave != null) {
						ObjectOutputStream fstrm = null;
						BufferedOutputStream ostrm = null;
						try {
							fstrm = new ObjectOutputStream(new FileOutputStream(savefile));
							ostrm = new BufferedOutputStream(fstrm);
							byte[] bytes = null;
							try {
								bytes = docToSave.getBytes();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							ostrm.write(bytes);
						} catch (IOException io) {
							System.err.println("IOException: " + io.getMessage());
						} finally {
							try {
								ostrm.flush();
								fstrm.close();
								ostrm.close();
							} catch (IOException ioe) {
								System.err.println("IOException: " + ioe.getMessage());
							}
						}
					}
				}
			}
		} else {
			return;
		}
	}

	public static byte[] getBytesFromFile(File f) {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
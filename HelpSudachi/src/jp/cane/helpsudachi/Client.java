package jp.cane.helpsudachi;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

public class Client extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1772830218613815707L;

	public Client() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}
			}
		}).start();
	}

	@Override
	public void update(Graphics g) {
		try {
			Socket socket = new Socket("192.168.110.104", 8888);
			BufferedImage image = ImageIO.read(socket.getInputStream());
			g.drawImage(image, 0, 0, null);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] argv) throws UnknownHostException,
			IOException {
		Frame frame = new Frame();
		frame.setSize(800, 600);
		frame.add(new Client());
		frame.pack();
		frame.setVisible(true);
	}
}

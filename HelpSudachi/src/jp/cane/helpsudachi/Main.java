package jp.cane.helpsudachi;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class Main implements Runnable {
	private Socket socket = null;

	public Main(Socket socket) {
		this.socket = socket;
		new Thread(this).start();
	}

	@Override
	public void run() {
		// 取得する画面エリア
		java.awt.GraphicsEnvironment env = java.awt.GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		java.awt.DisplayMode displayMode = env.getDefaultScreenDevice()
				.getDisplayMode();
		// 変数widthとheightに画面の解像度の幅と高さを代入
		int width = displayMode.getWidth();
		int height = displayMode.getHeight();
		Rectangle screenSize = new Rectangle(0, 0, width, height);

		try {
			socket.getOutputStream().write("HTTP/1.0 200\n\n".getBytes());

			// java.awt.Robotの生成
			Robot robot = new Robot();
			// イメージを所定の場所に出力
			BufferedImage image = robot.createScreenCapture(screenSize);
			ImageIO.write(image, "png", socket.getOutputStream());

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	public static void main(String[] argv) throws IOException {
		ServerSocket ssocket = new ServerSocket(8888, 0,
				Inet4Address.getByName("0.0.0.0"));

		while (true) {
			Socket socket = ssocket.accept();
			new Main(socket);
		}
	}
}

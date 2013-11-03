package jp.cane.helpsudachi;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class Main {
	public static void main(String[] argv) throws IOException {
		// 取得する画面エリア
		java.awt.GraphicsEnvironment env = java.awt.GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		java.awt.DisplayMode displayMode = env.getDefaultScreenDevice()
				.getDisplayMode();
		// 変数widthとheightに画面の解像度の幅と高さを代入
		int width = displayMode.getWidth();
		int height = displayMode.getHeight();
		Rectangle screenSize = new Rectangle(0, 0, width, height);

		ServerSocket ssocket = new ServerSocket(8888);

		while (true) {
			try {
				Socket socket = ssocket.accept();

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
			}
		}
	}
}

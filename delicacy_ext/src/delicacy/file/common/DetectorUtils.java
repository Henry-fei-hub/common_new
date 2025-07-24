package delicacy.file.common;

import java.awt.Desktop;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.URLEncoder;

import org.apache.log4j.Logger;


public class DetectorUtils {

	private static final Logger LOGGER = Logger.getLogger(DetectorUtils.class);

	private static boolean isWindows = false;
	private static boolean isLinux = false;
	private static boolean isMac = false;

	static {
		String os = System.getProperty("os.name").toLowerCase();
		isWindows = os.contains("win");
		isLinux = os.contains("nux") || os.contains("nix");
		isMac = os.contains("mac");
	}

	public static boolean isWindows() {
		return isWindows;
	}

	public static boolean isLinux() {
		return isLinux;
	}

	public static boolean isMac() {
		return isMac;
	};

	public static boolean open(File file) {

		String fullPath = file.getAbsolutePath();
		LOGGER.info(fullPath);

		try {
			if (DetectorUtils.isWindows()) {
				windowOpenFile(URLEncoder.encode(fullPath, "utf-8"));
//				Desktop.getDesktop().open(file);
//				Runtime.getRuntime().exec(new String[] { "C://Windows//System32//rundll32", "C:\\Windows\\System32\\url.dll,FileProtocolHandler", fullPath });
				return true;
			} else if (DetectorUtils.isLinux() || DetectorUtils.isMac()) {
				Runtime.getRuntime().exec(new String[] { "/usr/bin/open", fullPath });
				return true;
			} else {
				// Unknown OS, try with desktop
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(file);
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
	}
	
	
	public static void windowOpenFile(String fileName) {
        try {           
            int count = 0;
            RandomAccessFile pipe = null;
            boolean loop = true;
            while (loop) {
                if (count >= 5) {
                    return;
                }
                try {
                    pipe = new RandomAccessFile("\\\\.\\pipe\\coworkpipe", "rw");
                    loop = false;
                } catch (java.io.FileNotFoundException fe) {
                    Thread.sleep(50);
                    count++;
                    loop = true;
                }
            }
            if (!fileName.endsWith("\n")) {
                fileName = fileName + "\n";
            }
            String echoText = fileName;
            if (pipe != null) {
                pipe.write(echoText.getBytes());
            }
            String echoResponse = pipe.readLine();
            System.out.println("Response: " + echoResponse);
            pipe.close();
            Thread.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

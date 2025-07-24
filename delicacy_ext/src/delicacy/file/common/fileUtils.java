package delicacy.file.common;

import java.io.File;

public class fileUtils {
	public static void main(String[] args) {
		String dir = "F:/TTT";
		fileUtils.deleteFile(new File(dir));
	}

	/**
	 * 删除目录及目录下的文件
	 * 
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isDirectory())
					deleteFile(f);
				else
					f.delete();
			}
		}
		file.delete();
		return true;
	}

}

package top.flyfire.base.bytecode;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @ClassName DeployJar
 * @Description �ȼ���Jar��
 * @Author weizhi2018
 * @Date 2013-8-30 ����08:59:27
 * 
 */

public class HotDeploy {
	// ��׺
	private final static String CLAZZ_SUFFIX = ".class";
	
	private final static String JAR_SUFFIX = ".jar";

	// �������
	private ClassLoader classLoader;

	/**
	 * @Title loadPath
	 * @Description ����������
	 * @Author weizhi2018
	 * @param jarPath
	 *            jar������·��
	 * @throws
	 */
	public void loadPath(String jarPath) {
		try {
			File jarFiles = new File(jarPath);

			File[] jarFilesArr = jarFiles.listFiles();
			URL[] jarFilePathArr = new URL[jarFilesArr.length];
			int i = 0;
			for (File jarfile : jarFilesArr) {
				String jarname = jarfile.getName();
				if (jarname.endsWith(JAR_SUFFIX)) {
					jarFilePathArr[i] = jarfile.toURI().toURL();
					i++;
				}
				
			}
			classLoader = new URLClassLoader(jarFilePathArr,Thread.currentThread().getContextClassLoader());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Title loadJar
	 * @Description ����jar���µ���
	 * @Author weizhi2018
	 * @param jarName
	 *            jar������ ����·��
	 * @throws
	 */
	public void loadJar(String jarName) {
		if (jarName.indexOf(".jar") < 0) {
			return;
		}
		try {
			JarFile jarFile = new JarFile(jarName);
			Enumeration<JarEntry> em = jarFile.entries();
			while (em.hasMoreElements()) {
				JarEntry jarEntry = em.nextElement();
				String clazzFile = jarEntry.getName();

				if (!clazzFile.endsWith(CLAZZ_SUFFIX)) {
					continue;
				}
				String clazzName = clazzFile.substring(0,
						clazzFile.length() - CLAZZ_SUFFIX.length()).replace(
						'/', '.');
				System.out.println(clazzName);

				// loadClass(clazzName);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Title loadClass
	 * @Description ͨ���������ʵ����
	 * @Author weizhi2018
	 * @param clazzName
	 *            ������
	 * @return
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public <T> T newInstance(String clazzName) {
		if (this.classLoader == null) {
			return null;
		}
		Class<?> clazz = null;
		try {
			clazz = this.classLoader.loadClass(clazzName);
			Object obj = clazz.newInstance();
			return (T) obj;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (NoClassDefFoundError e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}


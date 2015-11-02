package top.flyfire.base.bytecode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import top.flyfire.base.ClassUtil;
import top.flyfire.base.StreamUtil;
import top.flyfire.base.StreamUtil.Task;

public class FPackage {
	
	private String name;
	
	private Map<String,FClass> chClass;
	
	public FPackage(String name){
		this.name = name;
		this.chClass = new HashMap<String,FClass>();
	}
	
	public FClass fclass(String name){
		name = this.name+"."+name;
		FClass fc = new FClass(name);
		chClass.put(name.replace(".", File.separator)+".class",fc);
		return fc;
	}
	
	public void toJar(String jarName){
		File file = new File(ClassUtil.PATH);
		Set<Entry<String,FClass>> set = chClass.entrySet();
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION	, "1.0");
		try {
			JarOutputStream target = new JarOutputStream(new FileOutputStream(ClassUtil.JARPATH+jarName+".jar"),manifest);	
			for(Iterator<Entry<String,FClass>> i = set.iterator();i.hasNext();){
				Entry<String,FClass> entry = i.next();
				entry.getValue().flush(true);
				i.remove();
			}
			toNextDeep(file, target);
			target.flush();
			target.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	
	private void toNextDeep(File source, JarOutputStream target) throws IOException{
		File[] chF = source.listFiles();
		for(int i =  0;i<chF.length;i++){
			writeClass(chF[i], target);
		}
	}
	
	private void writeClass(File source ,final JarOutputStream target) throws IOException{
		String url = source.toURI().toURL().toString();
		url = url.substring(url.indexOf(ClassUtil.PATH));
		String name = url.substring(ClassUtil.PATH.length());
		JarEntry entry = new JarEntry(name);
		entry.setTime(source.lastModified());
		target.putNextEntry(entry);
		if(source.isDirectory()){
			target.closeEntry();
			toNextDeep(source, target);
		}else{
			BufferedInputStream  in = new BufferedInputStream(new FileInputStream(source));
			StreamUtil.run(in, new Task() {
				
				@Override
				public void exec(byte[] by, int len) throws IOException {
					// TODO Auto-generated method stub
					target.write(by,0,len);
				}
			});
			target.closeEntry();
		}
	}
	
	
}

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
import top.flyfire.base.FFContext;
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
		if(!file.exists()||file.isFile()){
			file.mkdirs();
		}
		Set<Entry<String,FClass>> set = chClass.entrySet();
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION	, "1.0");
		try {
			JarOutputStream target = new JarOutputStream(new FileOutputStream(ClassUtil.PATH+jarName+".jar"),manifest);	
			for(Iterator<Entry<String,FClass>> i = set.iterator();i.hasNext();){
				Entry<String,FClass> entry = i.next();
				entry.getValue().flush(true);
				String name = entry.getKey();
				File source = new File(ClassUtil.PATH+name);
				this.writeClass(name, source, target);
				i.remove();
			}
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
	
	private void writeClass(String entryName,File source ,final JarOutputStream target) throws IOException{
		if(source.exists()){
			JarEntry entry = new JarEntry(entryName);
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
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

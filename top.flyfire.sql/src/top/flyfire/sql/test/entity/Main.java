package top.flyfire.sql.test.entity;

import java.util.HashMap;

import top.flyfire.base.ClassUtil;
import top.flyfire.base.bytecode.FPackage;

public class Main {
	public static void main(String[] args) {
		FPackage pckg = new FPackage("top.flyfire.test.entity");
		pckg.fclass("EntityTest1").annotation("top.flyfire.sql.test.entity.Entity", new HashMap<String,Object>())
			.field(ClassUtil.get("java.lang.String"), "id")
				.annotation("top.flyfire.sql.test.entity.Entity", new HashMap<String,Object>()).$()
			.field(ClassUtil.get("java.lang.Integer"), "age").widthSetter(false).$()
			.method("show")
				.bodyAppend("System.out.println(this.age);")
				.annotation("top.flyfire.sql.ann.NotNull", new HashMap<String,Object>())
				.annotation("top.flyfire.sql.ann.Length", new HashMap<String,Object>()).$();
		pckg.toJar("entity");
	}
}

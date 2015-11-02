package top.flyfire.sql.test.entity;

import top.flyfire.base.ClassUtil;
import top.flyfire.base.bytecode.FPackage;
import top.flyfire.base.kval.StringKVal;
import top.flyfire.sql.ann.IdType;

public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("top.flyfire.sql.test.entity.Entity").newInstance();
//		FPackage pckg = new FPackage("top.flyfire.test.entity");
//		pckg.fclass("EntityTest1").annotation("top.flyfire.sql.test.entity.Entity")
//		.field(ClassUtil.get("java.lang.String"), "id")
//		.annotation("top.flyfire.sql.ann.Id", new StringKVal("type", IdType.UUID)).$()
//			.field(ClassUtil.get("java.lang.Integer"), "age").widthSetter(false).$()
//			.method("show")
//				.bodyAppend("System.out.println(this.age);")
//				.annotation("top.flyfire.sql.ann.NotNull")
//				.annotation("top.flyfire.sql.ann.Length").$();
//		pckg.toJar("entity");
	}
}

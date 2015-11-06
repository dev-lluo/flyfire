package top.flyfire.sql.test.entity;


import java.math.BigDecimal;

import top.flyfire.base.ClassUtil;
import top.flyfire.base.bytecode.FPackage;
import top.flyfire.base.bytecode.HotDeploy;
import top.flyfire.base.kval.StringKVal;
import top.flyfire.sql.ann.IdType;
import top.flyfire.sql.resolver.AETResolver;

public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//System.out.println(Main.class.getClassLoader());
		//System.out.println(Thread.currentThread().getContextClassLoader());
		HotDeploy dp = new HotDeploy();
		dp.loadPath("C:\\Users\\h\\git\\flyfire\\ROOT\\WebContent\\WEB-INF\\lib");
		//System.out.println(Thread.currentThread().getContextClassLoader());
		//System.out.println(dp.loadClass("org.apache.commons.fileupload.FileItem"));
		
		
		
//		AETResolver.resolve("top.flyfire.sql.test.entity", "top.flyfire.sql.test.entity");
		
//		FPackage pckg = new FPackage("top.flyfire.test.entity");
//		pckg.fclass("EntityTest1").annotation("top.flyfire.sql.test.entity.Entity")
//		.field(ClassUtil.get("java.lang.String"), "id")
//		.annotation("top.flyfire.sql.ann.Id", new StringKVal("type", IdType.UUID)).$()
//			.field(ClassUtil.get("java.lang.Integer"), "age").widthSetter(false).withGetter(false).isPublic(true).isStatic(true).$()
//			.method("show")
//				.bodyAppend("System.out.println(this.age);")
//				.annotation("top.flyfire.sql.ann.NotNull")
//				.annotation("top.flyfire.sql.ann.Length").$();
//		pckg.toJar("entity");
//		
//		TestEntity testEntity = new TestEntity();
//		System.out.println(testEntity.getETimestamp());
//		testEntity.setECode("1234567890");
//		System.out.println(testEntity.getECode());
//		testEntity.setENum1(new BigDecimal("23.4567"));
//		System.out.println(testEntity.getENum1());
//		testEntity.setENum2(123.2f);
//		System.out.println(testEntity.getENum2());
//		testEntity.setENum3(12313l);
//		System.out.println(testEntity.getENum3());
//		
//		BigDecimal bigDecimal = new BigDecimal(1000.0);
//		bigDecimal = bigDecimal.setScale(3, BigDecimal.ROUND_FLOOR);
//		
//		short a = 10;
//		BigDecimal sh = new BigDecimal(a);
//		Float b = 10f;
//		BigDecimal ft = new BigDecimal(b);
//		System.out.println(bigDecimal.precision());
//		System.out.println(bigDecimal.scale());
//		System.out.println(bigDecimal);
	}
}

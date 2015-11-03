package top.flyfire.sql.test.entity;


import top.flyfire.sql.resolver.AETResolver;

public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//		AETResolver.resolve("top.flyfire.sql.test.entity", "top.flyfire.sql.test.entity");
		
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
		
		TestEntity testEntity = new TestEntity();
		System.out.println(testEntity.getETimestamp());
	}
}

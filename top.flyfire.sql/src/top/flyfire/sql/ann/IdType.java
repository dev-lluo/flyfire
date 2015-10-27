package top.flyfire.sql.ann;

import top.flyfire.sql.api.IdCreate;
import top.flyfire.sql.id.UUIDCreate;

@SuppressWarnings("all")
public enum IdType implements IdCreate{
	
	UUID(UUIDCreate.class)
	
	;private IdType(Class<? extends IdCreate> clzz){
		this.clzz = clzz;
		try {
			this.idCreate = clzz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	private Class<?> clzz;
	
	private IdCreate idCreate;

	@Override
	public String create() {
		// TODO Auto-generated method stub
		return idCreate.create();
	}

	@Override
	public String create(String prefix) {
		// TODO Auto-generated method stub
		return idCreate.create(prefix);
	}

	@Override
	public String create(String prefix, int length) {
		// TODO Auto-generated method stub
		return idCreate.create(prefix, length);
	}
	
}

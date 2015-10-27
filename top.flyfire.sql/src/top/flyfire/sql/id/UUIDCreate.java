package top.flyfire.sql.id;

import top.flyfire.base.UUID;
import top.flyfire.sql.api.IdCreate;

public class UUIDCreate implements IdCreate{

	@Override
	public String create() {
		// TODO Auto-generated method stub
		return UUID.$.create();
	}

	@Override
	public String create(String prefix) {
		// TODO Auto-generated method stub
		return prefix+"-"+UUID.$.create();
	}

	@Override
	public String create(String prefix, int length) {
		// TODO Auto-generated method stub
		return prefix+"-"+UUID.$.create(length, 62);
	}

}

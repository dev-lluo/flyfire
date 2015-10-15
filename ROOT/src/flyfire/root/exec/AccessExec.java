package flyfire.root.exec;


import flyfire.root.context.Exec;
import flyfire.root.context.FlyFire;
import flyfire.root.context.Store;

@Exec(url="access")
public class AccessExec {
	@Exec(url="login")
	public void login(Store store){
		FlyFire.$.print(store);
	}
}

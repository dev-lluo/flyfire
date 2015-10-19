package flyfire.root.exec;


import flyfire.root.context.Exec;
import flyfire.root.context.FlyFire;
import flyfire.root.store.AccessStore;

@Exec(url="access")
public class AccessExec {
	@Exec(url="login",isPost=true)
	public void login(AccessStore store){
		FlyFire.$.print(store);
	}
}

package flyfire.root.context;

import flyfire.root.util.JsonC;

public class JdkPrint implements Print {
	
	@Override
	public void print(Object obj){
		this.print(obj, PRNT_LOG);
	}
	
	@Override
	public void print(Object obj , int level){
		if(level>0){
			System.out.println(JsonC.convert(obj));
		}else{
			System.err.println(JsonC.convert(obj));
		}
	}

}

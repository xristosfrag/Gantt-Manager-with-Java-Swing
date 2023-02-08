package dom;

import java.util.List;

public class RetrieverFactory {

	public RetrieverFactory() {}

	
	public Object constructRetriever(String type,List<String[]> data,int id,String prefix) {
		if(type.equals("taskId")){
				return   new TaskIdRetriever(data,id);
		}
		else if(type.equals("topLevel")){
			return   new TopLevelRetriever(data);
		}
		else if(type.equals("prefix")){
			return   new PrefixRetriever(data,prefix);
	}
		return null;
	}
}

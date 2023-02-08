package dom;

import java.util.ArrayList;
import java.util.List;



public class PrefixRetriever implements IPrefixRetriever {
	private List<String[]> data;
	private String prefix;
	
	public PrefixRetriever(List<String[]> data,String prefix) 
	{
		this.data=data;
		this.prefix=prefix;
	}
	
	
	@Override
	public List<String[]>  getTasksByPrefix() {
		List<String[]> tasks=new ArrayList<String[]>();
		for(String[] task: data) {
			if(task[1].startsWith(prefix)) {
				tasks.add(task);
			}
		}
		return tasks;
	}
		
	
}

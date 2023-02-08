package dom;

import java.util.ArrayList;
import java.util.List;


public class TaskIdRetriever implements ITaskIdRetriever {
	private List<String[]> data;
	private int id;
	
	
	public TaskIdRetriever(List<String[]> data,int id) 
	{
		this.data=data;
		this.id=id;
	}
	
	@Override
	public List<String[]>  getTaskById() {
		List<String[]> tasks=new ArrayList<String[]>();
		for(String[] task: data) {
			if(task[0].equals(id +"")) {
				tasks.add(task);
			}
		}
		return tasks;
	}

}

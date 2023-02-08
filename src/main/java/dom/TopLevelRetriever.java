package dom;

import java.util.ArrayList;
import java.util.List;



public class TopLevelRetriever  implements ITopLevelRetriever{
	private List<String[]> data;

	public TopLevelRetriever(List<String[]> data) {
		this.data=data;
	}
	
	@Override
	public  List<String[]>  getTopLevelTasks() {
		ArrayList<String[]> topLevel=new ArrayList<String[]>();
		for(int i=0; i<data.size(); i++) {
			if(data.get(i)[2].equals("0")) {
				topLevel.add((data.get(i)));
			}
		}
		return topLevel;
	}

	

}

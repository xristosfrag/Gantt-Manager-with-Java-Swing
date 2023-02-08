package reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public  class MdReporter implements IReporter {
	private String path;
	private List<String[]> data;
	private String name;
	
	public MdReporter(String path,List<String[]> data, String name) {
		this.path=path;
		this.data=data;
		this.name=name;
	}
	
	
	String header="TaskId	TaskText	MamaId	Start	End	Cost";
	@Override
	public int createReport(String path, String name) {

		try {
		      FileWriter myWriter = new FileWriter(path+".md");
		      String tasks= "*"+name+"*\nTaskId\tTaskText\tMamaId\tStart\tEnd\tCost\n\n";
		      for(String[] task:data) {
		    	  if(task[2].equals(0+"")) {
		    		  for(String col:task) {
		    			  tasks+="**"+col+"**\t";
		    		  }
		    	  }
		    	  else {
		    		  for(String col:task) {
		    			  tasks+=col +"\t";
		    		  }
		    	  }
		    	  tasks+="\n";
		      }
		      myWriter.write(tasks);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file " + path +".md");
		      return data.size();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return 0;
	}
}

package reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import backend.ReportType;

public  class TxtReporter implements IReporter {
	private String path;
	private List<String[]> data;
	private String name;
	
	public TxtReporter(String path,List<String[]> data, String name) {
		this.path=path;
		this.data=data;
		this.name=name;
	}
	@Override
	public int createReport(String path, String name) {
		try {
		      FileWriter myWriter = new FileWriter(path+".txt");
		      String tasks="TaskId	TaskText	MamaId	Start	End	Cost\n";
		      for(String[] task:data) {
		    	  for(String col:task) {
		    		  tasks+=col +"\t";
		    	  }
		    	  tasks+="\n";
		      }
		      myWriter.write(tasks);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file " + path +".txt");
		      return data.size();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return -1;
	}
	
	
}

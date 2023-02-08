package reporter;

import java.io.FileWriter;

import java.io.IOException;
import java.util.List;

public  class HtmlReporter implements IReporter {
	private String path;
	private List<String[]> data;
	private String name;
	
	public HtmlReporter(String path,List<String[]> data, String name) {
		this.path=path;
		this.data=data;
		this.name=name;
	}
	
	
	@Override
	public int createReport(String path, String name) { 
		try {
		      FileWriter myWriter = new FileWriter(path+".html");
		      String tasks="<!DOCTYPE html> \n"
		      		+ "<html>\n"
		      		+ "<head>\n"
		      +"<meta http-equiv=Content-Type content=text/html; charset=UTF-8>\n"
		      +"<title>"+name+"</title>\n"
		      +"</head>\n"
		      +"<body>\n"
		      +"<table>\n"
		      +"<tr>\n"
		      +"<td><b>TaskId</b></td>\n"
		      +"<td><b>TaskText</b></td>\n"
		      +"<td><b>MamaId</b></td>\n"
		      +"<td><b>Start</b></td>\n"
		      +"<td><b>End</b></td>\n"
		      +"<td><b>Cost</b></td>\n"
		      +"</tr>\n";
		      for(String[] task:data) {
		    	  tasks+="<tr>\n";
		    	  for(String col:task) {
		    		  tasks+="<td>"+col+"</td>\n";
		    	  }
		    	  tasks+="\n";
		    	  tasks+="</tr>\n";
		      }
		      tasks+="</table>\n";
		      tasks+="</body>\n"
		      +"</html>\n";
		      myWriter.write(tasks);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file " + path +".html");
		      return data.size();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return -1;
	}
}
	
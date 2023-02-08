package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dom2app.SimpleTableModel;

public class TsvParser implements  IParser{
	private String fileName;
	
	public TsvParser(String fileName) {
		this.fileName=fileName;
	}
	@Override
	public SimpleTableModel loadFIle(String fileName) {
			File file=new File(fileName);
			System.out.println(fileName);
			
			String name1 = fileName.replace("\\","/");
			String[] name2 = name1.split("/");//[-1].split(".")[0];
			String name3 = name2[name2.length-1];
			if(!name3.contains("tsv"))
				return null;
			String name = name3.replace(".tsv","");
			
			
			ArrayList<ArrayList<String>> fileLines = new ArrayList<ArrayList<String>>();
			String prjName=name; 
			
			String header="TaskId/tTaskText/tMamaId/tStart/tEnd/tCost";
			String line = " ";
			String[] columnNames=header.split("/t");
			try   
			{  
				//parsing a CSV file into BufferedReader class constructor  
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				
				while ((line = br.readLine()) != null)   
				{  
					
					
					if(line.equals(header) ) {
						continue;
					}
					
					ArrayList<String>line_to_arr=new ArrayList<String>();
					String[] words = line.split("\t");  // use comma as separator
					for(int i=0; i<words.length; i++) {
						
							line_to_arr.add(words[i]);
							
					}
	
					
					
					fileLines.add(line_to_arr);
				}  
				br.close();
				/*for(ArrayList<String> lines: fileLines) {
					   
					for(int column=0; column<lines.size();column++) {
						System.out.println(lines.get(column)+ " " + lines.size());
				   }
				   }*/
				if(fileLines.size() == 0)
					return new SimpleTableModel(fileName,prjName,columnNames, new ArrayList<String[]>());
				 List<String[]> lines=convertToListOfArray(fileLines);
				 ArrayList<Integer> tops = new ArrayList<Integer>();
				 tops = findTopLevel(lines);
				 lines = sortTasks(tops,lines);
				 
				 
				 return new SimpleTableModel(fileName,prjName,columnNames,lines);
			}   
			catch (IOException e)   
			{  
				e.printStackTrace();  
			}
			return new SimpleTableModel(fileName,prjName,columnNames,null);
	}
	
	public List<String[]> convertToListOfArray(ArrayList<ArrayList<String>> fileLines){
		List<String[]> lines=new ArrayList<String[]>();
		for(ArrayList<String> line: fileLines) {
			String[] lineArray=new String[line.size()];
			for(int column=0; column<line.size();column++) {
				lineArray[column]=line.get(column);
			}
			lines.add(lineArray);
			
		}
	  
		return lines;
	}
	
	 private ArrayList<String[]> bubbleSortPerStartOfProccess(ArrayList<String[]> lines) {  
	        int n = lines.size();  
	        String[] temp;
	        
	         for(int i=0; i < n; i++)
	         {  
	        	 for(int j=0; j<lines.size()-1; j++) {
	        		 
	        		 if(compareProccesses(lines.get(j), lines.get(j+1)))
	        		 {
	        			 String[] tmp = lines.get(j);
	        			 lines.set(j, lines.get(j+1));
	        			 lines.set(j+1, tmp);
	        		 }
	        	 }
					/*
					 * String[] one = lines.get(i); String[] two = null; if (i==n-1 && n>2) { two =
					 * lines.get(i); } else { two = lines.get(i+1); }
					 * 
					 * if(compareProccesses(one,two)) { tempLines.add(one); }else
					 * tempLines.add(two);
					 */
					/*
					 * int k = 0; for(int j=i+1; j < (n-i); j++) { if(compareProccesses(lines,j)) {
					 * temp = lines.get(j-1); lines.set(j-1, lines.get(j)); lines.set(j, temp);
					 * 
					 * tempLines.add(lines.get(j)); //tempLines.add(lines.get(j-1)); //k = j;
					 * //break; }else { tempLines.add(lines.get(j-1));
					 * //tempLines.add(lines.get(j)); } }
					 */  
	                         
	          }  
	         return lines;
	    }  
	// and dyo ergasies exoun to idio start
	private boolean compareProccesses(String[] one, String[] two)//List<String[]> lines,int j) 
	{
		// an exoun idio start kai h prwth exei mikrotero id apo th defterh
		// h an h prwhgoumenh exei mikrotero start apo thn epomenh
		
		//check start													// then id
		if( (Integer.valueOf(one[3]) == Integer.valueOf(two[3])) &&  (Integer.valueOf(one[0]) > Integer.valueOf(two[0]))) {
			return true;
		}
		if	(Integer.valueOf(one[3]) > Integer.valueOf(two[3])){
			return true;
		}
		return false;
	}
	
	/*
	 * * Get id of the top level tasks
	 */
	public ArrayList<Integer> findTopLevel(List<String[]> lines){
		ArrayList<Integer> topLevel=new ArrayList<Integer>();
		
		//System.out.println("find top level:");
		for(int i=0; i<lines.size(); i++) {
			if(lines.get(i)[2].equals("0")) {
				
				topLevel.add(Integer.parseInt(lines.get(i)[0]));
			}
		}
		/*
		 * for(int i=0; i<topLevel.size(); i++) { System.out.println(topLevel.get(i)); }
		 * System.out.println("-----");
		 */
		return topLevel;
	}
	
	private String[] computeaAttributesForMamaTask(List<String[] >childTasks){
		int start = Integer.MAX_VALUE,end = 0,cost = 0;
		for(String[] child: childTasks) {
			cost += Integer.valueOf(child[5]);
			if(Integer.valueOf(child[3]) < start)
				start = Integer.valueOf(child[3]);
			if(Integer.valueOf(child[4]) > end)
				end = Integer.valueOf(child[4]);
		}
		String[] returnValues =  {start+"", end+"", cost+""};
		return  returnValues;
	}
	
	public List<String[]> sortTasks(ArrayList<Integer> topLevel,List<String[]> lines)
	{
		// exei sorted oles tis ergasies pou anaferontai se mia gonikh
		// gia to eggs arxeio dld, exei tria kleidia
		HashMap<Integer,ArrayList<String[]>> sortedTasks=new HashMap<Integer,ArrayList<String[]>>();
		
		for(int i=0; i<topLevel.size(); i++) // gia ka8e noumero top level
		{
			ArrayList<String[]> childTasks=new ArrayList<String[]>(); // krataei ta paidia ths gonikhs
			for(int j=0; j< lines.size();j++) 
			{
				if(lines.get(j)[2].equals(topLevel.get(i).toString())){ // an h trith sthlh twn tasks isoutai me to noumero ths top level task
					childTasks.add(lines.get(j)); 
				}
			}
		
			
			childTasks=(ArrayList<String[]>) bubbleSortPerStartOfProccess(childTasks);

			
			
			// compute start end and cost for top level task
			String[] attrs = computeaAttributesForMamaTask(childTasks);
						
			//find in lines the parent of the childs
			String[] parent = new String[6];
			for(int j=0; j<lines.size(); j++) {
				//System.out.println(Integer.valueOf(lines.get(j)[0].trim()) +"     "+ topLevel.get(i));
				//if(Integer.valueOf(lines.get(j)[0].trim()) == topLevel.get(i)) {
				if((lines.get(j)[0].trim()).equals(topLevel.get(i)+"")) {
					parent[0] = lines.get(j)[0];
					parent[1] = lines.get(j)[1];
					parent[2] = lines.get(j)[2];

					// set these attributes to topleveltask
					parent[3] = attrs[0];
					parent[4] = attrs[1];
					parent[5] = attrs[2];
					//System.out.println(parent.length);
					break;
				}
			}			
			//System.out.println(attrs[0] +" "+ attrs[1]+" "+ attrs[2]);
			 
			ArrayList<String[]> concatenated_list		// contains both parent and children tasks
            = new ArrayList<String[]>();
            concatenated_list.add(parent);
            concatenated_list.addAll(childTasks);
            
            
			sortedTasks.put(topLevel.get(i), concatenated_list);
		}		
		// end of hashmap
		//////////////////////////////////////////////////////////////////////////////////
		
		List<String[]> sortedLines = new ArrayList<String[]>(); // parent - all childs. again.  parent - all childs

		
		for(Integer taskId: sortedTasks.keySet()) 
		{
			List<String[]> topLevelAndChilds = sortedTasks.get(taskId);
			
			for(String[] task: topLevelAndChilds) {
				sortedLines.add(task);
			}
		}
		
		/*
		 * System.out.println("Sorted Tasks: ");
		 * 
		 * for(int j=0; j<sortedLines.size(); j++) { String[] st = sortedLines.get(j);
		 * for(int k=0; k<st.length; k++) { System.out.print(st[k]+" "); }
		 * System.out.println("\n"); } System.out.println(
		 * "====================================================================");
		 */
		
		return sortedLines;
	}
	
	public String getFileName() {
		return fileName;
	}
}

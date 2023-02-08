package backend;

import java.util.List;



import dom.PrefixRetriever;
import dom.RetrieverFactory;
import dom.TaskIdRetriever;
import dom.TopLevelRetriever;
import dom2app.SimpleTableModel;

import parser.ParserFactory;
import parser.TsvParser;
import reporter.ReportFactory;

public  class MainController implements IMainController {
	private RetrieverFactory retrieverFactory=new RetrieverFactory();
	private SimpleTableModel model;
	private ParserFactory parserFactory=new ParserFactory();
	private ReportFactory reporterFactory=new ReportFactory();
	
	
	 public SimpleTableModel load(String fileName, String delimeter) {
		 TsvParser tsvParser= (TsvParser) parserFactory.constructParser(fileName, delimeter);
		 model = tsvParser.loadFIle(fileName);
		 return model;
	 }
	
	/**
	 * Assuming a Gantt project has been loaded, it returns all the tasks whose TaskTest is prefixed by the method's argument
	 * 
	 * @param prefix A string with the prefix of the task description that we are looking for.
	 * @return a SimpleTableModel (@see dom2app.SimpleTableModel) that represents the retrieved tasks as a List of string arrays
	 */
	public SimpleTableModel getTasksByPrefix(String prefix) {
		List<String[]> fileLines = model.getData();
		PrefixRetriever taskPrefix = (PrefixRetriever) retrieverFactory.constructRetriever("prefix",fileLines,0,prefix);
		SimpleTableModel model_tmp = new SimpleTableModel(model.getName(), model.getPrjName(), model.getColumnNames(), model.getData());
		model_tmp.setData(taskPrefix.getTasksByPrefix());
		return model_tmp;
	}
	
	

	/**
	 * Assuming a Gantt project has been loaded, it returns the task whose taskId is equal to the method's argument
	 * 
	 * @param id An int with the id of the task that we are looking for.
	 * @return a SimpleTableModel (@see dom2app.SimpleTableModel) that represents the retrieved task as a List of string arrays
	 */
	public SimpleTableModel getTaskById(int id) {
		List<String[]> fileLines=model.getData();
		TaskIdRetriever taskId=(TaskIdRetriever) retrieverFactory.constructRetriever("taskId",fileLines,id,null);
		SimpleTableModel model_tmp = new SimpleTableModel(model.getName(), model.getPrjName(), model.getColumnNames(), model.getData());
		model_tmp.setData(taskId.getTaskById());
		return model_tmp;
	}

	/**
	 * Assuming a Gantt project has been loaded, it returns its top-level tasks 
	 * 
	 * @return the top-level tasks of the project as a SimpleTableModel (@see dom2app.SimpleTableModel) 
	 */
	public SimpleTableModel getTopLevelTasks() {
		List<String[]> fileLines = model.getData();
		TopLevelRetriever topLevel=(TopLevelRetriever) retrieverFactory.constructRetriever("topLevel",fileLines,0,null);
		SimpleTableModel model_tmp = new SimpleTableModel(model.getName(), model.getPrjName(), model.getColumnNames(), model.getData());
		model_tmp.setData(topLevel.getTopLevelTasks());
		return model_tmp;
	}

	/**
	 * Assuming a Gantt project has been loaded, it creates a report in a specified format
	 * The report lists, in a sorted fashion, all the tasks of the project.
	 * 
	 * @param path The path for the filename that will be produced
	 * @param type a ReportType (@see backend.ReportType) with the types of reports that can be produced.
	 * @return the number of tasks processed for the file creation and written as lines; -1 if sth goes wrong.  
	 */
	public int createReport(String path, ReportType type) {
		List<String[]> fileLines=model.getData();
		String fileType="";
		switch(type) {
			case TEXT:
				fileType="txt";
				return reporterFactory.constructReporter(path, fileLines, fileType, model.getPrjName()).createReport(path, model.getPrjName());
			case MD:
				fileType="md";
				return reporterFactory.constructReporter(path, fileLines, fileType, model.getPrjName()).createReport(path, model.getPrjName());
			case HTML:
				fileType="html";
				return reporterFactory.constructReporter(path, fileLines, fileType, model.getPrjName()).createReport(path, model.getPrjName());
			default:
				return -1;
			}
	}
	
	public void setModel(SimpleTableModel model) {
		this.model = model;
	}
}

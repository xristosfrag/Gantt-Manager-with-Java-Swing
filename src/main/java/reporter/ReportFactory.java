package reporter;

import java.util.List;

public class ReportFactory {

	
	
	public ReportFactory() {}
	
	public IReporter constructReporter(String path,List<String[]> data,String reportType, String name) {
		if(reportType.equals("txt")) {
			return new TxtReporter(path,data, name);
		}
		else if(reportType.equals("html")) {
			return new HtmlReporter(path,data, name);
		}
		else if(reportType.equals("md")) {
			return new MdReporter(path,data, name);
		}
		return null;
	}
}

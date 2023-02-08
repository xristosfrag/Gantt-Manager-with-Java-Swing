package parser;

public class ParserFactory {
	
	public ParserFactory() {}
	
	public IParser constructParser(String fileName,String delimeter) {
		if(delimeter.equals("\t")) {
			return (IParser) new TsvParser(fileName);
		}
		return null;
	}
}

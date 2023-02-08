package test;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import backend.MainController;
import backend.ReportType;
import dom2app.SimpleTableModel;
import parser.ParserFactory;
import parser.TsvParser;

public class MDReporterTest {

	@Test
	public void test() {
		Path path = Paths.get(System.getProperty("user.dir"));
		 MainController mc = new MainController();
		 
		 ParserFactory parserFactory=new ParserFactory();
		 TsvParser tsvParser= (TsvParser) parserFactory.constructParser(path.toString()+"\\src\\test\\resources\\input\\Eggs.tsv", "\t");
		 
		 SimpleTableModel model = tsvParser.loadFIle(path.toString()+"\\src\\test\\resources\\input\\Eggs.tsv");
		 //System.out.println(model.getData().size());
		 
		 mc.setModel(model);
		 ReportType type = ReportType.MD;
		 
		 int d = mc.createReport(path.toString()+"\\src\\test\\resources\\output\\Eggs_testoutput1", type);
		 
		 assertEquals(d,14);
	}

	@Test
	public void testWrongFileFormat() {
		
		
		Path path = Paths.get(System.getProperty("user.dir"));
		 MainController mc = new MainController();
		 
		 ParserFactory parserFactory=new ParserFactory();
		 TsvParser tsvParser= (TsvParser) parserFactory.constructParser(path.toString()+"\\src\\test\\resources\\input\\Eggs_wrong_format.tsv", "\t");
		 
		 SimpleTableModel model = tsvParser.loadFIle(path.toString()+"\\src\\test\\resources\\input\\Eggs_wrong_format.tsv");
		 
		 mc.setModel(model);
		 ReportType type = ReportType.MD;
		 
		 int d = mc.createReport(path.toString()+"\\src\\test\\resources\\output\\Eggs_output2", type);
		 
		 assertEquals(d,0);
	}

}

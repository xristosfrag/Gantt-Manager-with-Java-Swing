package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import backend.MainController;
import dom2app.SimpleTableModel;
import parser.ParserFactory;
import parser.TsvParser;

public class PrefixRetrieverTest {

		@Test
		public void test() {
			
			Path path = Paths.get(System.getProperty("user.dir"));
			 MainController mc = new MainController();
			 
			 ParserFactory parserFactory=new ParserFactory();
			 TsvParser tsvParser= (TsvParser) parserFactory.constructParser(path.toString()+"\\src\\test\\resources\\input\\Eggs.tsv", "\t");
			 
			 SimpleTableModel model = tsvParser.loadFIle(path.toString()+"\\src\\test\\resources\\input\\Eggs.tsv");
			 //System.out.println(model.getData().size());
			 
			 mc.setModel(model);
			 model = mc.getTasksByPrefix("Perp");
			 List<String[]> data = model.getData();
			 
			 List<String[]> data2 = new ArrayList<String[]>();
			 String[] n1 =  {"100",	"Prepare Fry",	"0",	"1",	"12",	"60"};
			data2.add(n1);
			
			boolean flag = true;
			for(int i=0; i<data.size(); i++) {
				for (int j=0; j< data.get(i).length; j++) {
					if(!data.get(i)[j].equals(data2.get(i)[j])) {
						System.out.println(data.get(i)[j]);
						flag = false;
					}
				}
			}
			assertTrue(flag);
		}
		
		@Test
		public void testWrongFileFormat() {
			
			
			Path path = Paths.get(System.getProperty("user.dir"));
			 MainController mc = new MainController();
			 
			 ParserFactory parserFactory=new ParserFactory();
			 TsvParser tsvParser= (TsvParser) parserFactory.constructParser(path.toString()+"\\src\\test\\resources\\input\\Eggs_wrong_format.tsv", "\t");
			 
			 SimpleTableModel model = tsvParser.loadFIle(path.toString()+"\\src\\test\\resources\\input\\Eggs_wrong_format.tsv");
			 
			 mc.setModel(model);
			 model = mc.getTasksByPrefix("Perp");
			 List<String[]> data = model.getData();
			 
			 assertEquals(data.size()+"", 0+"");
		}
}

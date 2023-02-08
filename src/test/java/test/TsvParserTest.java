package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dom2app.SimpleTableModel;
import parser.TsvParser;

public class TsvParserTest {

	private SimpleTableModel model;

	@Test
	public void tsvParserTestNameHappyDay() throws IOException {
		Path path = Paths.get(System.getProperty("user.dir"));
		
		System.out.println(path.toString());
		TsvParser tsvparser = new TsvParser(path.toString()+"\\src\\test\\resources\\input\\Eggs.tsv");
		try {
			model = tsvparser.loadFIle(path.toString()+"\\src\\test\\resources\\input\\Eggs.tsv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String[]> data = model.getData();
		
		List<String[]> data2 = new ArrayList<String[]>();
		String[] n1 =  {"100",	"Prepare Fry",	"0",	"1",	"12",	"60"};
		String[] n2 =  {"101",	"Turn on burner (low)",	"100",	"1",	"1",	"10"};
		String[] n3 =  {"102",	"Break eggs and pour into fry",	"100",	"2",	"4",	"10"};
		String[] n4 =  {"103",	"Steer mixture to avoid sticking",	"100",	"5",	"10",	"10"};
		String[] n5 =  {"105",	"Salt, pepper",	"100",	"5",	"5",	"10"};
		String[] n6 =  {"104",	"Throw yellow cheese into fry",	"100",	"6",	"12",	"10"};
		String[] n7 =  {"106",	"Turn burner off",	"100",	"12",	"12",	"10"};
		String[] n8 =  {"200",	"Prepare the bread",	"0",	"10",	"12",	"20"};
		String[] n9 =  {"201",	"Heat bread in toaster",	"200",	"10",	"12",	"10"};
		String[] n10 =  {"202",	"Little bit of salt, galric spice to bread",	"200",	"12",	"12",	"10"};
		String[] n11 =  {"300",	"Serve eggs",	"0",	"13",	"20",	"30"};
		String[] n12=  {"301",	"Put bread in plate",	"300",	"13",	"13",	"10"};
		String[] n13 =  {"302",	"Put eggs on bread",	"300",	"14",	"14",	"10"};
		String[] n14 =  {"303",	"Wash fry",	"300",	"15",	"20",	"10"};
		data2.add(n1);
		data2.add(n2);
		data2.add(n3);
		data2.add(n4);
		data2.add(n5);
		data2.add(n6);
		data2.add(n7);
		data2.add(n8);
		data2.add(n9);
		data2.add(n10);
		data2.add(n11);
		data2.add(n12);
		data2.add(n13);
		data2.add(n14);

		
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
	public void noTsvParserTestBadDay() throws IOException {
		Path path = Paths.get(System.getProperty("user.dir"));
		
		TsvParser tsvparser = new TsvParser(path.toString()+"\\src\\test\\resources\\input\\Eggs.md");
		try {
			model = tsvparser.loadFIle(path.toString()+"\\src\\test\\resources\\input\\Eggs.md");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(model);
	}

}
package AlienLanguage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleFileReader {
	public static List<String> ReadFile(String path){
		List<String> lines=new ArrayList<String>();
		try{
			BufferedReader input = new BufferedReader(new FileReader(path));
			try{
				String line=null;
				while((line=input.readLine())!=null){
					lines.add(line);
				}
			}finally{
				input.close();
			}
		}catch(IOException ex){
		}
		return lines;
	}
}

package AlienLanguage;

import java.util.*;

public class AlienLang {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="C:\\Users\\jg\\Downloads\\A-small-practice.in";
		AlienLang al=new AlienLang(GoogleFileReader.ReadFile(path));
	}

	public int L,N,D,Counter,TCase=1;
	public HashSet<String> allwords=new HashSet<String>();
	public  AlienLang(List<String> lines){
		String para[]=lines.get(0).split(" ");
		L=Integer.parseInt(para[0]);
		D=Integer.parseInt(para[1]);
		N= Integer.parseInt(para[2]);

		//read the next D lines into dictionary
		for(int i=1;i<D+1;i++){
			allwords.add(lines.get(i));
		}
		//the possibles begins at line {D+1}
		for(int j=D+1;j<lines.size();j++){
			//for each one, get all the possibles and see if there's one in dictionary
			ArrayList<StringBuilder> allpossible=GetAllPossibles(lines.get(j));
			for(StringBuilder sb:allpossible){
				if(allwords.contains(sb.toString()))
					Counter++;
			}
			System.out.println(lines.get(j));
			System.out.println("Case #"+TCase+": "+Counter);
			TCase++;	
			Counter=0;
		}
	}
	public ArrayList<StringBuilder> GetAllPossibles(String word){
		int currentParenth=-1;
		ArrayList<StringBuilder> allpossible=new ArrayList<StringBuilder>();
		int i=0;
		while(i<word.length()){
			currentParenth=word.indexOf("(",i);
			if(currentParenth!=-1){
				String chars=word.substring(i,currentParenth);
				allpossible=AddCharacterToBase(chars,allpossible);
				int currentEndParenth=word.indexOf(")",currentParenth);
				chars=word.substring(currentParenth+1,currentEndParenth);
				allpossible=IncreaseBase(chars,allpossible);
				i=currentEndParenth+1;
			}else{
				String chars=word.substring(i);
				allpossible=AddCharacterToBase(chars,allpossible);
				i=word.length();
			}
		}
		return allpossible;

		/*currentParenth=word.indexOf("(");
		while(currentParenth!=-1){//if there's ( inside this word.
			//get the characters before this (;
			String chars=word.substring(currentIndex,currentParenth);
			int currentEndParenth=word.indexOf(")",currentParenth);
			allpossible=AddCharacterToBase(chars,allpossible);
			//add all the chars between (....)
			chars=word.substring(currentParenth+1,currentEndParenth);
			allpossible=IncreaseBase(chars,allpossible);


			currentIndex=currentEndParenth+1;
			currentParenth=word.indexOf("(",currentIndex);
		}
		//right here means no more ( inside this word.
		 */
	}
	public ArrayList<StringBuilder> IncreaseBase(String thischars,ArrayList<StringBuilder> base){
		ArrayList<StringBuilder> temp=new ArrayList<StringBuilder>();
		if(base.size()==0){
			for(char s :thischars.toCharArray()){
				temp.add(new StringBuilder(String.valueOf(s)));
			}
		}else{
			for(StringBuilder sb:base){
				for(char s :thischars.toCharArray()){
					temp.add(new StringBuilder(sb.append(s).toString()));
					sb.deleteCharAt(sb.length()-1);
			
				}
			}
		}
		return temp;
	}
	public ArrayList<StringBuilder> AddCharacterToBase(String thischars,ArrayList<StringBuilder> base){
		for(char s :thischars.toCharArray()){
			if(base.size()==0){
				//StringBuilder tempsb=new StringBuilder(s);
				base.add(new StringBuilder( String.valueOf(s)));
			}else{
				for(StringBuilder sb :base){
					sb.append(s);
				}
			}
		}

		return base;
	}
}

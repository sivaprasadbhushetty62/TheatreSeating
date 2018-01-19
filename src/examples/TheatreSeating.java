package examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TheatreSeating {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		HashMap<String,ArrayList<String>>  layOut = new HashMap<String,ArrayList<String>>();
		ArrayList<String> patrons = new ArrayList<>();
		HashMap<String,Integer> patronsRequest = new HashMap<String,Integer>();
		ArrayList<String> columns = null;
		int count=0;
		
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			if(line.equalsIgnoreCase("exit"))
				break;
			count++;
			columns = new ArrayList<>();
			String rows[] = line.split(" ");
			for(String string:rows) {
				columns.add(string);
			}
			layOut.put("ROW"+count, columns);
		}
		
		//System.out.println(layOut);
		//scanner.close();
		/*
		layOut.forEach((K,V)->{
			System.out.print(K+":");
			ArrayList<String> row = V;
			row.forEach((string)->{System.out.print(string +" ");});
			System.out.println();
		});
		*/
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			if(line.equalsIgnoreCase("exit"))
				break;
			String rows[] = line.split(" ");
			patrons.add(rows[0]);
			patronsRequest.put(rows[0], Integer.valueOf(rows[1]));
		}
		scanner.close();
		/*
		patrons.forEach((key)->{
			System.out.print(key+":"+patronsRequest.get(key));
			System.out.println();
		});
		*/
		
		patrons.forEach((name) ->{
			int value = patronsRequest.get(name);
			boolean serviceFlag=false;
			int newValue=0;
			int indexPosition=0;
			int sumOfSeats=0;
			for(int i=0;i<layOut.size();i++) {
				ArrayList<String> row = layOut.get("ROW"+(i+1));
				for(int j=0;j<row.size();j++) {
					if(Integer.valueOf(row.get(j)) > value) {
						System.out.println(name +" Row "+(i+1)+" Section "+(j+1));
						serviceFlag=true;
						newValue=Integer.valueOf(row.get(j)) - value;
						indexPosition = j;
						break;
					}else {
						sumOfSeats+=Integer.valueOf(row.get(j));
						continue;
					}
				}
				if(serviceFlag) {
					row.set(indexPosition, String.valueOf(newValue));
					break;
				}	
			}
			if(!serviceFlag) {
				if(value > sumOfSeats) {
					System.out.println(name+", we can't handle your party");
				} else {
					System.out.println(name+" Call to split party.");
				}
			}
		});
		
	}
}

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;


public class Main {
	public static void main(String args[]) {
		try {
			PUT("", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void PUT(String strPath, String serverUrl) throws IOException {
		try {

			 
			  HttpClient http = new DefaultHttpClient();
			    HttpPut putmethod = new HttpPut("http://125.209.193.11:8080/etbike/boards?title=sex&content=sex&writer=sex&categoryValue=SHARE");
			    //compelete
			    HttpResponse response = http.execute(putmethod);
			    HttpEntity resEntity = response.getEntity();
				InputStream stream = resEntity.getContent();
				Scanner sc = new Scanner(stream);

				while (sc.hasNextLine()) {
					System.out.println(sc.nextLine());
				}
			
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}

package org.openjfx.ReadJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*Alunos:
 * Gustavo de Jesus Furtado ,   202176024;
 * Pedro Andrade Pereira Leão , 202035008.
 */

public class GetJson {
    
	private static HttpURLConnection conn;
	public static String Get(String link) {
		
		BufferedReader reader;
		String line;
		StringBuilder responseContent = new StringBuilder();
		try{
            // "https://brapi.dev/api/quote/list?sortBy=name&sortOrder=asc&limit=1500" returns the list of stoks
			URL url = new URL(link);
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);

			int status = conn.getResponseCode();
			
			if (status >= 300) {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			else {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			conn.disconnect();
		}	
        return responseContent.toString();
	}
}


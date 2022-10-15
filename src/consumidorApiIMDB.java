import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class consumidorApiIMDB {

	public static void main(String[] args) throws Exception {
		String apiKey = <Inserir seu código aqui>;
		URI apiUrl = URI.create("https://imdb-api.com/en/API/Top250TVs/" + apiKey);
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest
				.newBuilder()
				.uri(apiUrl)
				.GET()
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String json = response.body();
		
//		System.out.println("Resposta: " + json.substring(0, 20000));
		
		String[] moviesArray = parseJsonMovies(json);
		
//		for (String string : moviesArray) {
//			System.out.println(string);
//		}
		
//		List<String> titles = parseTitle(moviesArray);
//		for (String title : titles) {
//			System.out.println(title);
//		}
		
		List<String> images = parseImage(moviesArray);
		for (String image : images) {
			System.out.println(image);
		}
		
		
	}
	
	private static String[] parseJsonMovies(String json) {
		Matcher match = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
		if(!match.matches()) {
			throw new IllegalArgumentException("no match in " + json);
		}
		
		String[] moviesArray = match.group(1).split("\\},\\{");
		moviesArray[0] = moviesArray[0].substring(1);
		
		int lastNumber = moviesArray.length - 1;
		String lastString = moviesArray[lastNumber];
		moviesArray[lastNumber] = lastString.substring(0, lastString.length() - 1);
		
		return moviesArray;
		
	}
	
	private static List<String> parseTitle(String[] moviesArray) {
		
		return parseAtribute(moviesArray, 3);
	}
	
	private static List<String> parseImage(String[] moviesArray) {
		
		return parseAtribute(moviesArray, 5);
	}
	
	private static List<String> parseAtribute(String[] moviesArray, int pos) {
		return Stream.of(moviesArray)
				.map(e -> e.split("\",\"")[pos])
				.map(e -> e.split(":\"")[1])
				.map(e -> e.replaceAll("\"", ""))
				.collect(Collectors.toList());
	}

}


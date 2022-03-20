package Spring.covidTracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import Spring.covidTracker.models.LocationStats;

@Service
public class CovidService {

	private static String url= "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/bab84e04e7178d969cc28270a0889f5cdfe4a1cd/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> allstats= new ArrayList<>();
	
	public List<LocationStats> getAllstats() {
		return allstats;
	}

	public void setAllstats(List<LocationStats> allstats) {
		this.allstats = allstats;
	}

	@PostConstruct
	@Scheduled( cron = " * * 1 * * * ")
	public void fetchdata() throws IOException, InterruptedException {
	List<LocationStats> newstats= new ArrayList<>();	
	HttpClient client= HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
	
	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	
	//System.out.println(response.body());
	
	StringReader csvReader =new StringReader(response.body());
	Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
	for (CSVRecord record : records) {
		LocationStats locationstat = new LocationStats();
		
	    locationstat.setState(record.get("Province/State"));
	    locationstat.setCountry(record.get("Country/Region"));
	    int latestcases=Integer.parseInt(record.get(record.size()-1));
	    int prevcases=Integer.parseInt(record.get(record.size()-2));
	    locationstat.setLatestnocases(latestcases);
	    locationstat.setDiffromprevday(latestcases-prevcases);
	 //   System.out.println(locationstat);
	    newstats.add(locationstat);
	 
	}
	this.allstats=newstats;
	}
	
}

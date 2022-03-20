package Spring.covidTracker.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Spring.covidTracker.models.LocationStats;
import Spring.covidTracker.services.CovidService;

@Controller
public class HomeController {
	
	@Autowired
	CovidService coviddata;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStats> allstats=coviddata.getAllstats();
		int totalcases= allstats.stream().mapToInt(stat ->stat.getLatestnocases()).sum();
		int totalnewcases= allstats.stream().mapToInt(stat ->stat.getDiffromprevday()).sum();
		model.addAttribute("locationStats",allstats);
		model.addAttribute("totalreportedcases",totalcases);
		model.addAttribute("totalnewcases",totalnewcases);
		return "home";
	}
}

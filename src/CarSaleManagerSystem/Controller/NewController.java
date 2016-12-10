package CarSaleManagerSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * CarSaleManagerSystem.Controller in 4s-mini
 *
 * @author hasee
 * @since 2016/12/10.
 */
@Controller
@RequestMapping("/New")
public class NewController
{
	/************************************* PLAN ********************************************/
	// create year plan     /plan/year
	// update month plan    /plan/month

	/**
	 * Get the Plan Of the year and with the month
	 *
	 */
	@RequestMapping(value = "/plan/{year}/{month}", method = RequestMethod.GET)
	public void a(@PathVariable Integer year, @PathVariable Integer month, HttpSession session)
	{

	}

	@RequestMapping(value = "/plan/year", method = RequestMethod.POST)
	public void createYearPlan(@RequestBody String name, HttpSession session)
	{

	}

	@RequestMapping(value = "/plan/month", method = RequestMethod.POST)
	public void updateMonthPlan(@RequestBody String name, HttpSession session)
	{

	}

	/************************************* SALE ********************************************/
	// create a order       /sale/order

	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public void b(){}

	/************************************* STORAGE ********************************************/
	// create a car storage
	// update a car storage
	@RequestMapping(value = "/storage", method = RequestMethod.GET)
	public void c(){}

}

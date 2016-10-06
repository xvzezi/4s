package CarSaleManagerSystem.Controller;

import CarSaleManagerSystem.Bean.SoleService;
import CarSaleManagerSystem.Bean.SoleServiceContent;
import CarSaleManagerSystem.Service.SSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * CarSaleManagerSystem.Controller in 4s-mini
 *
 * @author hasee
 * @since 2016/10/6.
 */
@Controller
@RequestMapping("/Sole")

public class SoleController
{
	/**
	 * Get all the list
	 *      > Page: Sole/services
	 * @return
	 */
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public ModelAndView getAllServices()
	{
		ModelAndView modelAndView = new ModelAndView("Sole/service");

		// > services list
		modelAndView.addObject("services", ssService.getAllServices());
		return modelAndView;
	}

	/**
	 *  Get a service's content,
	 *      > you should specified the service_id
	 *      > you will have to compare type id in contents to change it into words
	 * @param service_id
	 * @return
	 */
	@RequestMapping(value = "/services/{service_id}", method = RequestMethod.GET)
	public ModelAndView getServiceContent(@PathVariable int service_id)
	{
		ModelAndView modelAndView = new ModelAndView("Sole/content");
		modelAndView.addObject("contents", ssService.getServiceContent(service_id));
		modelAndView.addObject("types", ssService.getAllTypes());
		return modelAndView;
	}

	/**
	 * Create a Service
	 *      > after creation, it will direct to /services
	 * @param soleService
	 * @return
	 */
	@RequestMapping(value = "/services/create", method = RequestMethod.POST)
	public ModelAndView createService(@RequestBody SoleService soleService)
	{
		ssService.createService(soleService.getCar_id(), soleService.getCar_type(),
				soleService.getCar_class(), soleService.getLease_start(), soleService.getLease_end());
		return new ModelAndView("redirect: /Sole/services");
	}

	/**
	 * Update a Service
	 *      > you are only allowed to change rebate and profit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/services/update", method = RequestMethod.PUT)
	public ModelAndView updateService(HttpServletRequest request)
	{
		Integer sole_id = Integer.getInteger(request.getParameter("id"));
		Integer rebate = Integer.getInteger(request.getParameter("rebate"));
		Integer profit = Integer.getInteger(request.getParameter("profit"));
		if(sole_id == null) return new ModelAndView("redirect: /Sole/services");
		if(rebate == null) rebate = -1;
		if(profit == null) profit = -1;
		ssService.updateRebateOrProfit(sole_id, rebate, profit);
		return new ModelAndView("redirect: /Sole/services");
	}

	/**
	 * Create a Type Name
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/types/{name}", method = RequestMethod.POST)
	@ResponseBody
	public String createType(@PathVariable String name)
	{
		return ssService.createType(name);
	}

	/**
	 * Create a content of a service
	 *      > after creation, it will be directed to /services/{service_id} GET above.
	 * @param service_id
	 * @param soleServiceContent
	 * @return
	 */
	@RequestMapping(value = "/services/{service_id}", method = RequestMethod.POST)
	public ModelAndView createContent(@PathVariable int service_id, @RequestBody SoleServiceContent soleServiceContent)
	{
		soleServiceContent.setSole_service_id(service_id);
		ssService.createServiceContent(service_id, soleServiceContent.getSole_service_type_id(),
				soleServiceContent.getIncome(), soleServiceContent.getRaw_profit());
		return new ModelAndView("redirect: /Sole/services/" + service_id);
	}



	@Autowired
	private SSService ssService;

	public void setSsService(SSService ssService)
	{
		this.ssService = ssService;
	}
}

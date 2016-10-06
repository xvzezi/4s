package CarSaleManagerSystem.Controller;

import CarSaleManagerSystem.Bean.MarketActivity;
import CarSaleManagerSystem.Service.MarketActivityService;
import CarSaleManagerSystem.util.StringToDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2016/10/4.
 */
@Controller
@RequestMapping("/market_activity")
public class MarketController
{
	@Autowired
	private MarketActivityService marketActivityService;

	/**
	 * This is the root page of the part of market activity
	 *      > XG should add a web page with method to get
	 *      > object list, with which one could show the
	 *      > data on the web page.
	 * @return A Basic Web Page
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getShowPage()
	{
		return new ModelAndView("Market/activity");
	}

	/***************************************************Get the activities*********************************************/
	/**
	 * Get all the market activity available
	 *      > This is an acquire-data-tunnel,
	 *      > which not suggested to be used
	 *      > alone. Beside, you will get all
	 *      > the data, which means it performs
	 *      > bad with quantities of data.
	 *  ----- New Feature
	 *     -- Do not use the way of restful
	 * @return
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView getAllActivities()
	{
		ModelAndView modelAndView = getShowPage();

		modelAndView.addObject("activities",
				marketActivityService.getActivity(null, null, MarketActivityService.Type.ALL));

		return modelAndView;
	}

	/**
	 * Get the market activity available
	 *      > This is an tunnel which you can get data
	 *      > throught date. The Date Format is
	 *      >           '2009.06.13'
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/date", method = RequestMethod.GET)
	public ModelAndView getThroughDate(HttpServletRequest request)
	{
		Date start = StringToDate.get(request.getParameter("start"));
		Date end = StringToDate.get(request.getParameter("end"));
		ModelAndView modelAndView = getShowPage();
		modelAndView.addObject("activities",
				marketActivityService.getActivity(start, end, MarketActivityService.Type.PART));

		return modelAndView;
	}

	/**************************************************Update PART*****************************************************/
	/**
	 * This is an enter for close an activity.
	 *      > with this, you will set the activity closed, and set the real statistics.
	 *      > for reliability, we should only make one can fix it once.
	 *  ----- Unimplemented part
	 *      -- closed the activity
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/close_activity", method = RequestMethod.POST)
	public ModelAndView finishAnActivity(HttpServletRequest request)
	{
		ModelAndView modelAndView = getShowPage();
		Integer id = Integer.getInteger(request.getParameter("id")),
				invite = Integer.getInteger(request.getParameter("invite")),
				nature = Integer.getInteger(request.getParameter("nature")),
				trans = Integer.getInteger(request.getParameter("trans"));
		if(id == null)
		{
			modelAndView.addObject("fail", "BAD REQUEST");
			return modelAndView;
		}

		modelAndView.addObject("success", marketActivityService.finishPlan(id, invite, nature, trans));
		return modelAndView;
	}

	/**
	 * This an enter for concluding the result of the activity
	 *      > of course, you could set the conclusion frequently.
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conclude", method = RequestMethod.POST)
	public ModelAndView concludeActivity(HttpServletRequest request)
	{
		ModelAndView modelAndView = getShowPage();
		Integer id = Integer.getInteger(request.getParameter("id"));
		String opt = request.getParameter("opt");
		String neg = request.getParameter("neg");

		if(id == null)
		{
			modelAndView.addObject("fail", "BAD REQUEST");
			return modelAndView;
		}
		modelAndView.addObject("success", marketActivityService.conclude(id, opt, neg));
		return modelAndView;
	}

	/**
	 * This is authorized function
	 *      > with no authority, one should not get no access to this function,
	 *      > but still not implemented.
	 * @param marketActivity
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public String updateActivity(@RequestBody MarketActivity marketActivity)
	{
		return marketActivityService.updateActivity(marketActivity);
	}

	/*************************************************create activity**************************************************/
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String createActivity(@RequestBody MarketActivity marketActivity)
	{
		return marketActivityService.createActivity(marketActivity);
	}

	/*************************************************delete part******************************************************/
	/**
	 * delete the activity, with administrator authority
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteActivity(HttpServletRequest request)
	{
		Integer id = Integer.getInteger(request.getParameter("id"));

		if(id == null)
			return "BAD REQUEST";
		MarketActivity marketActivity = new MarketActivity();
		marketActivity.setId(id);
		return marketActivityService.deleteActivity(marketActivity);
	}
}

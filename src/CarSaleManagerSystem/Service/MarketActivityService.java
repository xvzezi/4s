package CarSaleManagerSystem.Service;

import CarSaleManagerSystem.Bean.MarketActivity;
import CarSaleManagerSystem.DAO.MarketActivityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2016/10/4.
 */
@Service
@Transactional
public class MarketActivityService
{
	@Autowired
	private MarketActivityDAO dao;

	public enum Type {ALL, PART}

	// fetch activities
	public List<MarketActivity> getActivity(Date start, Date end, Type type)
	{
		if(type == Type.ALL)
		{
			return dao.getAllMarketActivity();
		}
		else if(type == Type.PART)
		{
			return dao.getActivityAfterDate(start, end, 20);
		}
		return null;
	}

	// update activity
	// control the inside message
	public String finishPlan(int id, int invite, int nature, int trans)
	{
		// get the activity according to id
		MarketActivity marketActivity = dao.getMarketActivityById(id);
		if(marketActivity == null)
		{
			return "Activity Not Exists";
		}

		// change the value
		if(marketActivity.getFact_invite() == 0)
		{
			marketActivity.setFact_invite(invite);
		}
		if(marketActivity.getFact_nature() == 0)
		{
			marketActivity.setFact_nature(nature);
		}
		if(marketActivity.getFact_trans() == 0)
		{
			marketActivity.setFact_trans(trans);
		}

		// store it
		dao.updateActivity(marketActivity);

		return "SUCCESS";
	}

	public String conclude(int id, String con_opt, String con_neg)
	{
		// get the activity according to id
		MarketActivity marketActivity = dao.getMarketActivityById(id);
		if(marketActivity == null)
		{
			return "Activity Not Exists";
		}

		// change the value
		marketActivity.setCon_opt(con_opt);
		marketActivity.setCon_neg(con_neg);
		return "SUCCESS";
	}

	public String updateActivity(MarketActivity marketActivity)
	{
		dao.updateActivity(marketActivity);
		return "SUCCESS";
	}

	// create activity
	public String createActivity(MarketActivity marketActivity)
	{
		dao.createActivity(marketActivity);
		return "SUCCESS";
	}

	// delete activity
	public String deleteActivity(MarketActivity marketActivity)
	{
		dao.deleteActivity(marketActivity);
		return "SUCCESS";
	}

	public void setDao(MarketActivityDAO dao)
	{
		this.dao = dao;
	}
}

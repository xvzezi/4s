package CarSaleManagerSystem.Service;

import CarSaleManagerSystem.Bean.SoleService;
import CarSaleManagerSystem.Bean.SoleServiceContent;
import CarSaleManagerSystem.Bean.SoleServiceType;
import CarSaleManagerSystem.DAO.SoleServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * CarSaleManagerSystem.Service in 4s-mini
 *
 * @author hasee
 * @since 2016/10/6.
 */
@Service
@Transactional
public class SSService
{
	@Autowired
	private SoleServiceDAO soleServiceDAO;

	public void setSoleServiceDAO(SoleServiceDAO soleServiceDAO)
	{
		this.soleServiceDAO = soleServiceDAO;
	}

	/***********************************************************************************************************/

	public List<SoleService> getAllServices()
	{
		return soleServiceDAO.getAllServices(0, 20);
	}

	public List<SoleServiceContent> getServiceContent(int service_id)
	{
		return soleServiceDAO.getContentById(service_id);
	}

	public void createService(String car_id, String car_type, String car_class,
	                          Date start, Date end)
	{
		SoleService soleService = new SoleService();
		soleService.setCar_class(car_class);
		soleService.setCar_id(car_id);
		soleService.setCar_type(car_type);
		soleService.setLease_start(start);
		soleService.setLease_end(end);
		soleServiceDAO.createService(soleService);
	}

	public void updateRebateOrProfit(int sole_id, int rebate, int profit)
	{
		SoleService soleService = soleServiceDAO.getServiceById(sole_id);
		if(rebate >= 0) soleService.setRebate(rebate);
		if(profit >= 0) soleService.setProfit(profit);
		soleServiceDAO.updateService(soleService);
	}

	public List<SoleServiceType> getAllTypes()
	{
		return soleServiceDAO.getAllType();
	}

	public String createType(String type)
	{
		int result = soleServiceDAO.createType(type);
		if(result < 0)
		{
			return "failed for bad type name";
		}
		return "success";
	}

	public void createServiceContent(int service_id, int type_id, int income, int raw_profit)
	{
		SoleServiceContent soleServiceContent = new SoleServiceContent();
		soleServiceContent.setSole_service_id(service_id);
		soleServiceContent.setSole_service_type_id(type_id);
		soleServiceContent.setIncome(income);
		soleServiceContent.setRaw_profit(raw_profit);
		soleServiceDAO.createContent(soleServiceContent);
	}
}

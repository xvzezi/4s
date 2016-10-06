package CarSaleManagerSystem.Bean;

import java.util.Date;

/**
 * CarSaleManagerSystem.Bean in 4s-mini
 *
 * @author hasee
 * @since 2016/10/6.
 */
public class SoleService
{
	private int sole_service_id;
	private String car_id;
	private String car_type;
	private String car_class;
	private Date lease_start;
	private Date lease_end;
	private int rebate;
	private int profit;

	public int getSole_service_id()
	{
		return sole_service_id;
	}

	public void setSole_service_id(int sole_service_id)
	{
		this.sole_service_id = sole_service_id;
	}

	public String getCar_id()
	{
		return car_id;
	}

	public void setCar_id(String car_id)
	{
		this.car_id = car_id;
	}

	public String getCar_type()
	{
		return car_type;
	}

	public void setCar_type(String car_type)
	{
		this.car_type = car_type;
	}

	public String getCar_class()
	{
		return car_class;
	}

	public void setCar_class(String car_class)
	{
		this.car_class = car_class;
	}

	public Date getLease_start()
	{
		return lease_start;
	}

	public void setLease_start(Date lease_start)
	{
		this.lease_start = lease_start;
	}

	public Date getLease_end()
	{
		return lease_end;
	}

	public void setLease_end(Date lease_end)
	{
		this.lease_end = lease_end;
	}

	public int getRebate()
	{
		return rebate;
	}

	public void setRebate(int rebate)
	{
		this.rebate = rebate;
	}

	public int getProfit()
	{
		return profit;
	}

	public void setProfit(int profit)
	{
		this.profit = profit;
	}
}

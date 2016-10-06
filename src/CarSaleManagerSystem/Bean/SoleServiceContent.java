package CarSaleManagerSystem.Bean;

/**
 * CarSaleManagerSystem.Bean in 4s-mini
 *
 * @author hasee
 * @since 2016/10/6.
 */
public class SoleServiceContent
{
	private int sole_service_id;
	private int sole_service_type_id;
	private int income;
	private int raw_profit;

	public int getSole_service_id()
	{
		return sole_service_id;
	}

	public void setSole_service_id(int sole_service_id)
	{
		this.sole_service_id = sole_service_id;
	}

	public int getSole_service_type_id()
	{
		return sole_service_type_id;
	}

	public void setSole_service_type_id(int sole_service_type_id)
	{
		this.sole_service_type_id = sole_service_type_id;
	}

	public int getIncome()
	{
		return income;
	}

	public void setIncome(int income)
	{
		this.income = income;
	}

	public int getRaw_profit()
	{
		return raw_profit;
	}

	public void setRaw_profit(int raw_profit)
	{
		this.raw_profit = raw_profit;
	}
}

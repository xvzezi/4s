package CarSaleManagerSystem.Bean;

import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 *
 * ${PACKAGE_NAME} in 4s-mini
 * @author hasee
 * @since 2016/10/5.
 */
public class Bean
{
	private int bean_id;
	private int user_id;
	private Date sale_date;
	private String brand;

	// whole car
	private int opt_whole = 0;
	// substitution
	private int opt_substitute = 0;
	// gift
	private int opt_gift = 0;
	// insurance
	private int opt_insurance = 0;
	// financial
	private int opt_fin = 0;
	// serve fee
	private int opt_serve = 0;
	// delayed insurance
	private int opt_delay_insur = 0;
	// vip
	private int opt_vip = 0;
	// lease
	private int opt_lease = 0;

	// CR
	private int neg_CR = 0;
	// management
	private int neg_man = 0;

	// sale achieve
	private int sale_achieve = 0;
	// profit achieve
	private  int profit_achieve = 0;

	public int getBean_id()
	{
		return bean_id;
	}

	public void setBean_id(int bean_id)
	{
		this.bean_id = bean_id;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

	public Date getSale_date()
	{
		return sale_date;
	}

	public void setSale_date(Date sale_date)
	{
		this.sale_date = sale_date;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public int getOpt_whole()
	{
		return opt_whole;
	}

	public void setOpt_whole(int opt_whole)
	{
		this.opt_whole = opt_whole;
	}

	public int getOpt_substitute()
	{
		return opt_substitute;
	}

	public void setOpt_substitute(int opt_substitute)
	{
		this.opt_substitute = opt_substitute;
	}

	public int getOpt_gift()
	{
		return opt_gift;
	}

	public void setOpt_gift(int opt_gift)
	{
		this.opt_gift = opt_gift;
	}

	public int getOpt_insurance()
	{
		return opt_insurance;
	}

	public void setOpt_insurance(int opt_insurance)
	{
		this.opt_insurance = opt_insurance;
	}

	public int getOpt_fin()
	{
		return opt_fin;
	}

	public void setOpt_fin(int opt_fin)
	{
		this.opt_fin = opt_fin;
	}

	public int getOpt_serve()
	{
		return opt_serve;
	}

	public void setOpt_serve(int opt_serve)
	{
		this.opt_serve = opt_serve;
	}

	public int getOpt_delay_insur()
	{
		return opt_delay_insur;
	}

	public void setOpt_delay_insur(int opt_delay_insur)
	{
		this.opt_delay_insur = opt_delay_insur;
	}

	public int getOpt_vip()
	{
		return opt_vip;
	}

	public void setOpt_vip(int opt_vip)
	{
		this.opt_vip = opt_vip;
	}

	public int getOpt_lease()
	{
		return opt_lease;
	}

	public void setOpt_lease(int opt_lease)
	{
		this.opt_lease = opt_lease;
	}

	public int getNeg_CR()
	{
		return neg_CR;
	}

	public void setNeg_CR(int neg_CR)
	{
		this.neg_CR = neg_CR;
	}

	public int getNeg_man()
	{
		return neg_man;
	}

	public void setNeg_man(int neg_man)
	{
		this.neg_man = neg_man;
	}

	public int getSale_achieve()
	{
		return sale_achieve;
	}

	public void setSale_achieve(int sale_achieve)
	{
		this.sale_achieve = sale_achieve;
	}

	public int getProfit_achieve()
	{
		return profit_achieve;
	}

	public void setProfit_achieve(int profit_achieve)
	{
		this.profit_achieve = profit_achieve;
	}
}

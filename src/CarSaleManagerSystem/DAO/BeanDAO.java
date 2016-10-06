package CarSaleManagerSystem.DAO;

import CarSaleManagerSystem.Bean.Bean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * CarSaleManagerSystem.DAO in 4s-mini
 *
 * @author hasee
 * @since 2016/10/5.
 */
@Repository
public class BeanDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	/*******************************************BEGIN*************************************************/
	/**
	 * Query getting groups
	 * @return
	 */
	public List<Bean> getAllBeans()
	{
		Session session = sessionFactory.getCurrentSession();

		List<Bean> beanList = session.createQuery("from Bean").list();
		return beanList;
	}

	public Bean getById(int bean_id)
	{
		Session session = sessionFactory.getCurrentSession();


		return (Bean) session.get(Bean.class, bean_id);
	}

	public List<Bean> getByUserIdWithDate(int user_id, Date start)
	{
		Session session = sessionFactory.getCurrentSession();

		// might be null, here
		return session.createQuery("from Bean " +
				"where user_id = ? and month(sale_date) = month(?) and year(sale_date) = year(?)")
				.setInteger(0, user_id).setDate(1, start).setDate(1, start).list();
	}

	public List<Bean> getByDate(Date start)
	{
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from Bean where month(sale_date) = month(?) and year(sale_date) = year(?)")
				.setDate(0, start).setDate(1, start).list();
	}

	public List<Bean> getByBrandWithDate(String brand, Date start)
	{
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from Bean " +
				"where brand = ? and month(sale_date) = ? and year(sale_date) = year(?)").
				setString(0, brand).setDate(1, start).setDate(2, start).list();
	}

	/**
	 * Create Bean or Beans
	 */
	public int createBean(Bean bean)
	{
		// check
		if(bean.getBrand() == null || bean.getSale_date() == null)
			return -1;
		Session session = sessionFactory.getCurrentSession();
		Bean tmp = (Bean) session.createQuery("from Bean " +
				"where user_id = ? and brand = ? and month(sale_date) = month(?) and year(sale_date) = year(?)")
				.setInteger(0, bean.getUser_id()).setString(1, bean.getBrand())
				.setDate(2, bean.getSale_date()).setDate(3, bean.getSale_date()).uniqueResult();
		if(tmp != null)
			return -2;

		session.save(bean);
		return 0;
	}

	public void createBeans(List<Bean> beans)
	{
		for(Bean b : beans)
		{
			createBean(b);
		}
		Session session = sessionFactory.getCurrentSession();
		session.flush();
	}

	/**
	 * Update Or Add
	 */
	public void updateBean(Bean bean)
	{
		Session session = sessionFactory.getCurrentSession();
		session.update(bean);
		session.flush();
	}

	public void addDataToBean(Bean bean)
	{
		if(bean.getSale_date() == null || bean.getBrand() == null)
			return;

		Session session = sessionFactory.getCurrentSession();
		Bean b = (Bean) session.createQuery("from Bean " +
				"where user_id = ? and brand = ? and month(sale_date) = month(?) and year(sale_date) = year(?)")
				.setInteger(0, bean.getBean_id()).setString(1, bean.getBrand())
				.setDate(2, bean.getSale_date()).setDate(3, bean.getSale_date()).uniqueResult();
		if(b == null)
			return;

		// set the data
		b.setOpt_whole(b.getOpt_whole() + bean.getOpt_whole());
		b.setOpt_substitute(b.getOpt_substitute() + bean.getOpt_substitute());
		b.setOpt_gift(b.getOpt_gift() + bean.getOpt_gift());
		b.setOpt_insurance(b.getOpt_insurance() + bean.getOpt_insurance());
		b.setOpt_fin(b.getOpt_fin() + bean.getOpt_fin());
		b.setOpt_serve(b.getOpt_serve() + bean.getOpt_serve());
		b.setOpt_delay_insur(b.getOpt_delay_insur() + bean.getOpt_delay_insur());
		b.setOpt_vip(b.getOpt_vip() + bean.getOpt_vip());
		b.setOpt_lease(b.getOpt_lease() + bean.getOpt_lease());

		b.setNeg_CR(b.getNeg_CR() + bean.getNeg_CR());
		b.setNeg_man(b.getNeg_man() + bean.getNeg_man());

		b.setSale_achieve(b.getSale_achieve() + bean.getSale_achieve());
		b.setProfit_achieve(b.getProfit_achieve() + bean.getProfit_achieve());

		session.save(b);
		session.flush();
	}

	/**
	 * remove part
	 */
	public void removeBean(Bean bean)
	{
		Session session = sessionFactory.getCurrentSession();
		session.delete(bean);
	}

}

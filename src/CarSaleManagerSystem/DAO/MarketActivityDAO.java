package CarSaleManagerSystem.DAO;

import CarSaleManagerSystem.Bean.MarketActivity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2016/10/2.
 */
@Repository
public class MarketActivityDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	// start
	public MarketActivity getMarketActivityById(int id)
	{
		Session session = sessionFactory.getCurrentSession();

		MarketActivity ma = null;
		ma = (MarketActivity)session.get(MarketActivity.class, id);
		return ma;
	}

	public List<MarketActivity> getAllMarketActivity()
	{
		Session session = sessionFactory.getCurrentSession();

		String hql = "from MarketActivity";
		List<MarketActivity> mas = session.createQuery(hql).list();
		return mas;
	}

	public void createActivity(MarketActivity marketActivity)
	{
		Session session = sessionFactory.getCurrentSession();

		session.save(marketActivity);
		session.flush();
	}

	public void updateActivity(MarketActivity marketActivity)
	{
		Session session = sessionFactory.getCurrentSession();

		session.update(marketActivity);
	}

	public void deleteActivity(MarketActivity marketActivity)
	{
		Session session = sessionFactory.getCurrentSession();

		session.delete(marketActivity);
	}

	public List<MarketActivity> getActivityAfterDate(Date start, Date end, int size)
	{
		Session session = sessionFactory.getCurrentSession();
		if(start == null)
		{
			// get latest 20
			try
			{
				Query query = session.createQuery("from MarketActivity " +
						"order by start desc");
				query.setFetchSize(size > 0? size : 20);
				return query.list();
			}catch (Exception e)
			{
				e.printStackTrace();
			}finally
			{
				if(!session.getTransaction().wasCommitted())
					session.getTransaction().commit();
			}
		}
		else
		{
			session.beginTransaction();
			// get according to date
			try
			{
				Query query = session.createQuery("from MarketActivity " +
						"where start between start and end");
				return query.list();
			}catch (Exception e)
			{
				e.printStackTrace();
			}finally
			{
				if(!session.getTransaction().wasCommitted())
					session.getTransaction().commit();
			}
		}
		return null;
	}
}

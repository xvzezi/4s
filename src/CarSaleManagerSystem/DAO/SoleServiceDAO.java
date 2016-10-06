package CarSaleManagerSystem.DAO;

import CarSaleManagerSystem.Bean.SoleService;
import CarSaleManagerSystem.Bean.SoleServiceContent;
import CarSaleManagerSystem.Bean.SoleServiceType;
import org.hibernate.Query;
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
 * @since 2016/10/6.
 */
@Repository
public class SoleServiceDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	/***************************************** Sole Service Type ******************************************************/
	/**
	 * Give out a type with a given name
	 *  > if exists, it returns -2
	 *  > it returns 0 when everything goes well
	 * @param name
	 * @return
	 */
	public int createType(String name)
	{
		// check validation
		if(name == null)
		{
			return -1;
		}

		// check if exists
		Session session = sessionFactory.getCurrentSession();
		SoleServiceType soleServiceType = (SoleServiceType) session.createQuery("from SoleServiceType " +
				"where sole_service_description = ?").setString(0, name).uniqueResult();
		if(soleServiceType != null)
		{
			return -2;
		}

		// store it
		soleServiceType = new SoleServiceType();
		soleServiceType.setSole_service_description(name);
		session.save(soleServiceType);
		session.flush();
		return 0;
	}

	/**
	 * Delete a type
	 * @param name
	 */
	public void deleteType(String name)
	{
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from SoleServiceType where sole_service_description = ?")
				.setString(0, name).executeUpdate();
	}

	/**
	 * Get all the type to check the type
	 * @return
	 */
	public List<SoleServiceType> getAllType()
	{
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from SoleServiceType").list();
	}

	/************************************** Sole Service *********************************************************/

	public List<SoleService> getAllServices(int startLoc, int fetchSize)
	{
		fetchSize = fetchSize > 0 ? fetchSize : 20;
		startLoc = startLoc > 0 ? startLoc : 0;

		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from SoleService ")
			.setFirstResult(startLoc).list();
	}

	public List<SoleService> getServiceByCarId(String car_id)
	{
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from SoleService where car_id = ?").setString(0, car_id).list();
	}

	public List<SoleService> getServiceByDate(Date start, Date end)
	{
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from SoleService where lease_start between ? and ?")
				.setDate(0, start).setDate(1, end).list();
	}

	public SoleService getServiceById(int sole_id)
	{
		Session session = sessionFactory.getCurrentSession();
		return (SoleService)session.get(SoleService.class, sole_id);
	}

	public void createService(SoleService soleService)
	{
		Session session = sessionFactory.getCurrentSession();
		session.save(soleService);
		session.flush();
	}

	public void updateService(SoleService soleService)
	{
		Session session = sessionFactory.getCurrentSession();
		session.update(soleService);
	}

	public void deleteService(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		SoleService soleService = new SoleService();
		soleService.setSole_service_id(id);
		session.delete(soleService);
	}

	/*************************************** Sole Content *******************************************************/

	public List<SoleServiceContent> getContentById(int sole_id)
	{
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from SoleServiceContent where sole_service_id = ?")
				.setInteger(0, sole_id).list();
	}

	public void createContent(SoleServiceContent soleServiceContent)
	{
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(soleServiceContent);
	}

}

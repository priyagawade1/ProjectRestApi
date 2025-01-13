package com.product.main.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.product.main.entity.Product;

@Repository
public class ProductDaompl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveProduct(Product product) {
		Session session = null;
		boolean isAdd = false;

		try {

			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Product productFind = session.get(Product.class, product.getProductId());

			if (productFind == null) {
				session.save(product);
				transaction.commit();
				return isAdd = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.clear();
			}
		}

		return isAdd;
	}

	@Override
	public List<Product> getAllProduct() {

		Session session = null;
		List<Product> list = null;
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Product.class);

			list = criteria.list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.clear();
			}
		}

		return list;
	}

	@Override
	public Product getDataById(String productId) {

		Session session = null;
		Product product = null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			product = session.get(Product.class, productId);
			if (product != null) {
				return product;
			}

		} catch (Exception e) {

		} finally {
			if (session != null) {
				session.close();
			}

		}

		return product;
	}

	@Override
	public boolean deleteProductById(String productId) {

		Session session = null;
		boolean isdelete = false;

		try {
		 session=sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.delete(product);
				transaction.commit();
				isdelete = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

		}

		return isdelete;
	}

	@Override
	public boolean updateProduct(Product product) {
		
		Session session=null;
		boolean isUpdate=false;
		
		 try {
			 
			 session=sessionFactory.openSession();
			 Transaction transaction = session.beginTransaction();
			 
			Product getProduct=session.get(Product.class, product.getProductId());
			 
			
			 if(product!= null)
			 {   session.evict(getProduct);
				 session.update(product);
				 transaction.commit();
				 return isUpdate=true;
			 }
			 
			 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
   finally {
	  if(session != null)
	  {
		  session.close();
	  }
}
		return isUpdate;
	}

	@Override
	public List<Product> getProductByName(String productNamee) {

		Session session = null;
		List<Product> allProductList = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			allProductList = getAllProduct();
			// System.out.println(allProductList);

			for (Product product : allProductList) {

				{

					//
					Criteria criteria = session.createCriteria(Product.class);
					// String productNames=product.getProductName();
					Criterion criterion = Restrictions.eq("productName", productNamee);
					criteria.add(criterion);
					criteria.add(criterion);
					list = criteria.list();
					System.out.println(list.size());
					return list;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		System.out.println(list);
		return list;
	}

	@Override
	public List<Product> getProductMaxPrise() {
	    
		Session session=null;
		List<Product> lists=null;
		
		
		try {
			
		session=sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.setProjection(Projections.max("productPrise"));
	int	maxvalue= (int) criteria.list().get(0);
		
	Criteria criteria1=	session.createCriteria(Product.class);
	criteria1.add(Restrictions.eq("productPrise", maxvalue));
	
	
	
	lists=criteria1.list();
	  return lists;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			if(session != null)
			{
				session.close();
			}
		}
		
		
		
		return lists;
	}

}

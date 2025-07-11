package com.example;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // 1. CREATE Region and Country
        Transaction tx = session.beginTransaction();

        Region region = new Region();
        region.setRegion_name("Asia");
        session.save(region);

        Country country = new Country();
        country.setCountry_id("IN");
        country.setCountry_name("India");
        country.setRegion(region);
        session.save(country);

        tx.commit();

        // 2. READ
        Country c = session.get(Country.class, "IN");
        System.out.println("Country: " + c.getCountry_name() + ", Region: " + c.getRegion().getRegion_name());

        // 3. UPDATE
        tx = session.beginTransaction();
        c.setCountry_name("Bharat");
        session.update(c);
        tx.commit();

        // 4. DELETE
        tx = session.beginTransaction();
        session.delete(c);
        tx.commit();

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}

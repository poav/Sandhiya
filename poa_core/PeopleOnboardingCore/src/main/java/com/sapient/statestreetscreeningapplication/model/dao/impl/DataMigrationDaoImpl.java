package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.DataMigrationDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.entity.PersonStaffing;
import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.model.entity.Rate;

@Component
public class DataMigrationDaoImpl implements DataMigrationDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void setPersonStaffingCategory() {
		String query = "from OnboardingCheckList ";
		TypedQuery<OnboardingCheckList> onboardingCheckListTQ = entityManager
				.createQuery(query, OnboardingCheckList.class);
		List<OnboardingCheckList> oResultList = onboardingCheckListTQ
				.getResultList();
		for (OnboardingCheckList o : oResultList) {
			PersonStaffing p = o.getPersonStaffing();
			p.setCategory(o.getPersonScreeningDetails().getCategory());
			entityManager.merge(p);
		}

	}

	@Override
	@Transactional
	public void setRate() {
		Rate rate1 = new Rate();

		String query1 = "from LocationNew ";
		String query2 = "from Category ";
		String query3 = "from Position ";
		TypedQuery<LocationNew> locationNewTQ = entityManager.createQuery(
				query1, LocationNew.class);
		TypedQuery<Category> categoryTQ = entityManager.createQuery(query2,
				Category.class);
		TypedQuery<Position> positionTQ = entityManager.createQuery(query3,
				Position.class);
		List<LocationNew> oResultList1 = locationNewTQ.getResultList();
		List<Category> oResultList2 = categoryTQ.getResultList();
		List<Position> oResultList3 = positionTQ.getResultList();

		for (LocationNew o : oResultList1) {
			
			String country = o.getCountry();
			
			if (country.equalsIgnoreCase("US")) {
				for (Category category : oResultList2) {
					
					// rate1.setProductionSupport(false);

					for (Position p : oResultList3) {
						String positionName = p.getPositionName();

						if (positionName.equalsIgnoreCase("Senior Manager PM") && category.getCategoryName().equalsIgnoreCase("PM")) {
							rate1.setLocation(o);
							rate1.setCategory(category);
							rate1.setRateCategory("Onshore");
							rate1.setRateType("MONTHLY");
							rate1.setUsed(true);
							rate1.setRate(36000);
							rate1.setPosition(p);
							entityManager.merge(rate1);
						} /*else if (positionName.startsWith("Senior Associate")) {

							rate1.setRate(25000);
							rate1.setPosition(p);
							entityManager.merge(rate1);
						} else if (positionName.startsWith("Manager")) {

							rate1.setRate(30000);
							rate1.setPosition(p);
							entityManager.merge(rate1);
						} else if (positionName.startsWith("Senior Manager")) {

							rate1.setRate(36000);
							rate1.setPosition(p);
							entityManager.merge(rate1);
						} else if (positionName.startsWith("Director")) {

							rate1.setRate(40000);
							rate1.setPosition(p);
							entityManager.merge(rate1);
						}*/

					}
				}

			} else if (country.equalsIgnoreCase("India")) {

				rate1.setLocation(o);
				rate1.setRateCategory("Offshore");
				rate1.setRateType("MONTHLY");
				rate1.setUsed(true);

				for (Category c : oResultList2) {
					for (Position position : oResultList3) {
						String categoryName = c.getCategoryName();
						if (categoryName.equalsIgnoreCase("PM") && position.getPositionName().equalsIgnoreCase("Senior Manager PM")) {
							rate1.setRate(7000);
							rate1.setCategory(c);
							rate1.setPosition(position);

							entityManager.merge(rate1);

						}/*else if(categoryName.equalsIgnoreCase("Production Support")){
							
						}
						
						else {
							rate1.setRate(7000);
							rate1.setCategory(c);
							rate1.setPosition(position);

							entityManager.merge(rate1);

						}*/
					}
				}

				/*for (Position c : oResultList3) {
					for (Category category : oResultList2) {
						if (c.getPositionName().startsWith("Associate")) {
							rate1.setRate(4676);
							rate1.setPosition(c);
							rate1.setCategory(category);

							entityManager.merge(rate1);
						} else if (c.getPositionName().startsWith(
								"Junior Associate")) {
							rate1.setRate(4676);
							rate1.setPosition(c);
							rate1.setCategory(category);

							entityManager.merge(rate1);

						} else if (c.getPositionName().startsWith(
								"Senior Associate")) {
							rate1.setRate(6960);
							rate1.setPosition(c);
							rate1.setCategory(category);

							entityManager.merge(rate1);
						} else if (c.getPositionName().startsWith("Manager")) {
							rate1.setRate(8920);
							rate1.setPosition(c);
							rate1.setCategory(category);

							entityManager.merge(rate1);
						} else if (c.getPositionName().startsWith("Director")) {

							rate1.setCategory(category);
							rate1.setRate(19870);
							rate1.setPosition(c);

							entityManager.merge(rate1);
						}
					}
				}*/
			}
		}

	}

	/*
	 * public void setRate() { String query1 = "from Category";
	 * TypedQuery<Category> categories = entityManager.createQuery(query1,
	 * Category.class); List<Category> categoryRL = categories.getResultList();
	 * 
	 * String query2 = "from LocationNew"; TypedQuery<LocationNew> locations =
	 * entityManager.createQuery(query2, LocationNew.class); List<LocationNew>
	 * locationRL = locations.getResultList();
	 * 
	 * String query3 = "from Position"; TypedQuery<Position> positions =
	 * entityManager.createQuery(query3, Position.class); List<Position>
	 * positionRL = positions.getResultList();
	 * 
	 * Rate rate = new Rate();
	 * 
	 * for (LocationNew locationNew : locationRL) { if
	 * (locationNew.getCountry().equalsIgnoreCase("India")) {
	 * 
	 * rate.setUsed(true); rate.setRateCategory("LOCAL");
	 * rate.setRateType("MONTHLY"); rate.setLocation(locationNew);
	 * 
	 * for(Position position:positionRL){ for (Category category : categoryRL) {
	 * 
	 * if (category.getCategoryName().equalsIgnoreCase( "Technology") ||
	 * category.getCategoryName() .equalsIgnoreCase("BA") ||
	 * category.getCategoryName() .equalsIgnoreCase("PM")) {
	 * 
	 * rate.setRate(7000); rate.setCategory(category);
	 * rate.setPosition(position); rate.setProductionSupport(false);
	 * entityManager.merge(rate); }
	 * 
	 * else if (category.getCategoryName().equalsIgnoreCase("QA")) {
	 * 
	 * rate.setRate(6500); rate.setCategory(category);
	 * rate.setPosition(position); rate.setProductionSupport(false);
	 * entityManager.merge(rate); }
	 * 
	 * } }
	 * 
	 * } else if (locationNew.getCountry().equalsIgnoreCase("US")) {
	 * 
	 * rate.setUsed(true); rate.setRateCategory("ONSITE");
	 * rate.setRateType("MONTHLY");
	 * 
	 * rate.setLocation(locationNew); for(Category category: categoryRL){ for
	 * (Position position : positionRL) {
	 * 
	 * if (position.getPositionName().startsWith("Associate")) {
	 * 
	 * rate.setRate(20000); rate.setPosition(position);
	 * rate.setCategory(category); rate.setProductionSupport(false);
	 * entityManager.merge(rate);
	 * 
	 * } else if (position.getPositionName().startsWith( "Senior Associate")) {
	 * 
	 * rate.setRate(25000); rate.setPosition(position);
	 * rate.setCategory(category); rate.setProductionSupport(false);
	 * entityManager.merge(rate); } else if
	 * (position.getPositionName().startsWith("Manager")) { rate.setRate(30000);
	 * rate.setPosition(position); rate.setCategory(category);
	 * rate.setProductionSupport(false); entityManager.merge(rate);
	 * 
	 * } else if (position.getPositionName().startsWith( "Senior Manager")) {
	 * 
	 * rate.setRate(36000); rate.setPosition(position);
	 * rate.setCategory(category); rate.setProductionSupport(false);
	 * entityManager.merge(rate);
	 * 
	 * } else if (position.getPositionName() .startsWith("Director")) {
	 * 
	 * rate.setRate(40000); rate.setPosition(position);
	 * rate.setCategory(category); rate.setProductionSupport(false);
	 * entityManager.merge(rate);
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * } }
	 * 
	 * for (LocationNew locationNew : locationRL) { if
	 * (locationNew.getCountry().equalsIgnoreCase("India")) {
	 * 
	 * rate.setUsed(true); rate.setRateCategory("LOCAL");
	 * rate.setRateType("MONTHLY"); rate.setLocation(locationNew);
	 * //rate.setCategory(null); for(Category category:categoryRL){ for
	 * (Position position : positionRL) {
	 * 
	 * if (position.getPositionName().startsWith("Associate")) {
	 * 
	 * rate.setRate(4676); rate.setPosition(position);
	 * rate.setCategory(category); rate.setProductionSupport(true);
	 * entityManager.merge(rate);
	 * 
	 * } else if (position.getPositionName().startsWith( "Senior Associate")) {
	 * 
	 * rate.setRate(6960); rate.setPosition(position);
	 * rate.setCategory(category); rate.setProductionSupport(true);
	 * entityManager.merge(rate);
	 * 
	 * } else if (position.getPositionName().startsWith("Manager")) {
	 * 
	 * rate.setRate(8920); rate.setPosition(position);
	 * rate.setCategory(category); rate.setProductionSupport(true);
	 * entityManager.merge(rate);
	 * 
	 * } else if (position.getPositionName() .startsWith("Director")) {
	 * 
	 * rate.setRate(19870); rate.setPosition(position);
	 * rate.setCategory(category); rate.setProductionSupport(true);
	 * entityManager.merge(rate);
	 * 
	 * }
	 * 
	 * } } } }
	 * 
	 * }
	 */

	@Override
	@Transactional
	public void setRateId() {

		// PersonStaffing personStaffingObj = new PersonStaffing();
		String query1 = "from PersonStaffing";
		TypedQuery<PersonStaffing> personStaffings = entityManager.createQuery(
				query1, PersonStaffing.class);
		List<PersonStaffing> personStaffingRL = personStaffings.getResultList();

		String query2 = "from Rate";
		TypedQuery<Rate> rates = entityManager.createQuery(query2, Rate.class);
		List<Rate> ratesRL = rates.getResultList();

		for (PersonStaffing personStaffing : personStaffingRL) {
			for (Rate rate : ratesRL) {

				if (personStaffing.getLocation().getLocationId() == rate
						.getLocation().getLocationId()
						&& personStaffing.getPosition().getPositionId() == rate
								.getPosition().getPositionId()
						&& personStaffing.getCategory().getCategoryId() == rate
								.getCategory().getCategoryId()) {
					// Rate r=personStaffing.getRate();

					personStaffing.setRate(rate);
					entityManager.merge(personStaffing);
				}

			}
		}

	}

}

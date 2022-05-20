package CRUD.Controller.servicios;

import CRUD.Controller.Person.Persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PersonaJpaData {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Persona> getData(HashMap<String, Object> conditions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);
        int maxResults = 10;


        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field) {
                case "user":
                case "name":
                case "surname":
                    predicates.add(cb.equal(root.get(field), value));
                    break;
                case "created_date":
                    String dateCondition = (String) conditions.get("dateCondition");
                    switch (dateCondition) {
                        case "greaterThan":
                            try {
                                predicates.add(cb.greaterThan(root.get(field), (Date) new SimpleDateFormat("yyyyMMdd").parse((String) value)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "lessThan":
                            try {
                                predicates.add(cb.lessThan(root.get(field), (Date) new SimpleDateFormat("yyyyMMdd").parse((String) value)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "equal":
                            try {
                                predicates.add(cb.equal(root.get(field), new SimpleDateFormat("yyyyMMdd").parse((String) value)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                case "orderBy":
                    query.orderBy(cb.desc(root.get((String) value)));
                    break;
            }
        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        int firstResult = Integer.parseInt((String) conditions.get("pageIndex"));
        if (conditions.containsKey("pageSize")) {
            maxResults = Integer.parseInt((String) conditions.get("pageSize"));
        }
        return entityManager.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}

package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Barco;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class BarcoCustomJPARepository implements IBarcoCustomJPARepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Barco> findAll(String search) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT b FROM Barco b JOIN FETCH b.tripulantes t ");

        List<String> conditions = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        this.generateQueryParams(search, conditions, params);

        if (!conditions.isEmpty())
            queryBuilder.append(" WHERE " + StringUtils.join(conditions, " and "));

        Query jpaQuery = entityManager.createQuery(queryBuilder.toString());

        for (String key : params.keySet())
            jpaQuery.setParameter(key, params.get(key));

        @SuppressWarnings("unchecked")
        List<Barco> barcos = jpaQuery.getResultList();

        return barcos;
    }

    @Override
    public List<Barco> findAllWithTripulacion(String search) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT b FROM Barco b JOIN b.tripulantes t ");

        List<String> conditions = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        this.generateQueryParams(search, conditions, params);

        if (!conditions.isEmpty())
            queryBuilder.append(" WHERE " + StringUtils.join(conditions, " and "));

        Query jpaQuery = entityManager.createQuery(queryBuilder.toString());

        for (String key : params.keySet())
            jpaQuery.setParameter(key, params.get(key));

        @SuppressWarnings("unchecked")
        List<Barco> barcos = jpaQuery.getResultList();

        return barcos;
    } 

    private void generateQueryParams(String search, List<String> conditions, Map<String, Object> params) {
        Pattern pattern = Pattern.compile("([\\w\\d]+)\\.([\\w\\d]+)(:|<|<=|>=|>)([\\w\\d]+)");
        Matcher matcher = pattern.matcher(search);

        while(matcher.find()) {
            String entity = matcher.group(1);
            String atribute = matcher.group(2);
            String condition = matcher.group(3);
            String value = matcher.group(4);

            this.buildCondition(conditions, params, entity, atribute, condition, value);
        }
    }

    private void buildCondition(List<String> conditions, Map<String, Object> params, String entity, String atribute,
                                String condition, String value) {
        switch (entity) {
            case "barco":
                switch (atribute) {

                    case "id":
                        switch(condition) {
                            case ":":
                                conditions.add(" b.id = :bid ");
                                params.put("bid", value);
                                break;

                            case "<":
                            case ">":
                            case ">=":
                            case "<=":
                                conditions.add("b.id " + condition + ":bid");
                                params.put("bid", value);
                                break;
                            
                            default:
                                throw new IllegalArgumentException(
                                        "Condicion no soportada para barco.id: " + condition);
                        }
                        break;

                    case "nombre":
                        switch (condition){
                            case ":":
                                conditions.add("b.nombre like :bnombre ");
                                params.put("bnombre", "%" + value + "%");
                                break;
                            default:
                                throw new IllegalArgumentException(
                                    "Condicion no soportada para barco.nombre: " + condition);        
                        }
                        break;

                    case "eslora":
                        switch(condition) {
                            case ":":
                                conditions.add(" b.eslora = :beslora ");
                                params.put("beslora", value);
                                break;

                            case "<":
                            case ">":
                            case ">=":
                            case "<=":
                                conditions.add("b.eslora " + condition + ":beslora");
                                params.put("beslora", value);
                                break;
                            
                            default:
                                throw new IllegalArgumentException(
                                        "Condicion no soportada para barco.eslora: " + condition);
                        }
                        break;
                        
                    case "potenciaCv":
                        switch(condition) {
                            case ":":
                                conditions.add(" b.potenciaCv = :bpotenciaCv ");
                                params.put("bpotenciaCv", value);
                                break;

                            case "<":
                            case ">":
                            case ">=":
                            case "<=":
                                conditions.add("b.potenciaCv " + condition + ":bpotenciaCv");
                                params.put("bpotenciaCv", value);
                                break;
                            
                            default:
                                throw new IllegalArgumentException(
                                        "Condicion no soportada para barco.potenciaCv: " + condition);
                        }
                        break;
                        
                    case "potenciaKw":
                        switch(condition) {
                            case ":":
                                conditions.add(" b.potenciaKw = :bpotenciaKw ");
                                params.put("bpotenciaKw", value);
                                break;

                            case "<":
                            case ">":
                            case ">=":
                            case "<=":
                                conditions.add("b.potenciaKw " + condition + ":bpotenciaKw");
                                params.put("bpotenciaKw", value);
                                break;
                            
                            default:
                                throw new IllegalArgumentException(
                                        "Condicion no soportada para barco.potenciaKw: " + condition);
                        }
                        break;     

                    case "extrangero":
                        switch (condition){
                            case ":":
                                conditions.add("b.extrangero = :bextrangero ");
                                boolean boolValue = Boolean.valueOf(value);
                                params.put("bextrangero", boolValue);
                                break;
                            default:
                                throw new IllegalArgumentException(
                                    "Condicion no soportada para barco.extrangero: " + condition);        
                        }
                        break;
                    
                    default:
                        throw new IllegalArgumentException(
                            "Atributo no soportado en la entidad barco: " + atribute);
                }
                break;

            default:
                throw new IllegalArgumentException(
                    "Entidad no soportada: " + entity);
        }
    }  
}

 
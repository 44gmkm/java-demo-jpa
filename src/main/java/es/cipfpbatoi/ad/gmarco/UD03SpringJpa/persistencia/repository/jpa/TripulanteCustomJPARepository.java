package es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.repository.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import es.cipfpbatoi.ad.gmarco.UD03SpringJpa.persistencia.modelo.Tripulante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class TripulanteCustomJPARepository implements ITripulanteCustomJPARepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tripulante> findAll(String search) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT t FROM Tripulante t JOIN FETCH t.barco b");

        List<String> conditions = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        this.generateQueryParams(search, conditions, params);

        if (!conditions.isEmpty()){
            queryBuilder.append(" WHERE " + StringUtils.join(conditions, " and "));
        }

        Query jpaQuery = entityManager.createQuery(queryBuilder.toString());

        for (String key : params.keySet()){
            jpaQuery.setParameter(key, params.get(key));
        }

        @SuppressWarnings("unchecked")
        List<Tripulante> tripulantes = jpaQuery.getResultList();
        return tripulantes;
    }

    private void generateQueryParams(String search, List<String> conditions, Map<String, Object> params) {
        
        Pattern pattern = Pattern.compile("([\\w\\d]+)\\.([\\w\\d]+)(:|<|<=|>=|>)([\\w\\d]+)");
        Matcher matcher = pattern.matcher(search);

        while(matcher.find()) {

            String entity = matcher.group(1);
            String atribute = matcher.group(2);
            String condition = matcher.group(3);
            String valor = matcher.group(4);

            this.buildCondition(conditions, params, entity, atribute, condition, valor);
        }
    }

    private void buildCondition(List<String> conditions, Map<String, Object> params, String entity, String atribute,
            String condition, String valor) {

        switch (entity) {
            case "tripulante":
                switch (atribute) {

                    case "nombre":
                        switch (condition) {
                            case ":":
                                conditions.add(" t.nombre like :tnombre ");
                                params.put("tnombre", "%" + valor + "%");
                                break;

                            default:
                                throw new IllegalArgumentException(
                                            "Condicion no soportada para tripulante.nombre: " + condition);
                        }
                        break;
                        
                    case "apellido1":
                        switch (condition) {
                            case ":":
                                conditions.add(" t.apellido1 like :tapellido1 ");
                                params.put("tapellido1", "%" + valor + "%");
                                break;

                            default:
                                throw new IllegalArgumentException(
                                            "Condicion no soportada para tripulante.apellido1: " + condition);
                        }
                        break;
                        
                    case "apellido2":
                        switch (condition) {
                            case ":":
                                conditions.add(" t.apellido2 like :tapellido2 ");
                                params.put("tapellido2", "%" + valor + "%");
                                break;

                            default:
                                throw new IllegalArgumentException(
                                            "Condicion no soportada para tripulante.apellido2: " + condition);
                        }
                        break;
                        
                    case "rol":
                        switch (condition) {
                            case ":":
                                conditions.add(" t.rol like :rol ");
                                params.put("rol", "%" + valor + "%");
                                break;

                            default:
                                throw new IllegalArgumentException(
                                            "Condicion no soportada para tripulante.rol: " + condition);
                        }
                        break;    

                    case "id":
                        switch (condition) {
                            case ":":
                                conditions.add(" t.id = :tid ");
                                params.put("tid", valor);
                                break;

                            case "<":
                            case ">":
                            case ">=":
                            case "<=":
                                conditions.add(" t.id " + condition + ":tid ");
                                params.put("tid", valor);
                                break;
                                
                            default:
                                throw new IllegalArgumentException(
                                        "Condicion no soportada para tripulante.id: " + condition);    
                        }
                        break;
                    
                    default:
                        throw new IllegalArgumentException(
                                "Atributo no soportado en la entidad tripulante: " + atribute);
                }
                break;

            default:
                throw new IllegalArgumentException(
                            "Entidad no soportada: " + entity);    
        }
    }

    @Override
    public List<Tripulante> findAllWithBarco(String search) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT t FROM Tripulante t JOIN t.barco b");

        List<String> conditions = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        this.generateQueryParams(search, conditions, params);

        if (!conditions.isEmpty()){
            queryBuilder.append(" WHERE " + StringUtils.join(conditions, " and "));
        }

        Query jpaQuery = entityManager.createQuery(queryBuilder.toString());

        for (String key : params.keySet()){
            jpaQuery.setParameter(key, params.get(key));
        }

        @SuppressWarnings("unchecked")
        List<Tripulante> tripulantes = jpaQuery.getResultList();
        return tripulantes;
    }
}

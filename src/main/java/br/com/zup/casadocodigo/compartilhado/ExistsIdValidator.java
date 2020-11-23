package br.com.zup.casadocodigo.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private String domainAttribute;
    private Class<?> clazz;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsId params) {
        domainAttribute = params.fieldName();
        clazz = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        Query query = manager.createQuery("select 1 from "+ clazz.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", value);


        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <=1, "aconteceu algo bizarro e você tem mais de um "+ clazz +" com o atributo "+domainAttribute+" com o valor = "+value);

        return !list.isEmpty();
    }
}

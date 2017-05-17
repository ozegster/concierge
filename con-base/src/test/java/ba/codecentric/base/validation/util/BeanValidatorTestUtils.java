package ba.codecentric.base.validation.util;


import org.hibernate.validator.internal.engine.ValidationContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorManager;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintValidator;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;

public class BeanValidatorTestUtils {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <A extends Annotation, E> void replaceValidatorInContext(Validator validator,
                                                                           final ConstraintValidator<A, ?> validatorInstance,
                                                                           E instanceToBeValidated) {

        Class<A> annotationToBeValidated = getAnnotation(validatorInstance);
        ConstraintValidatorManager constraintValidatorManager = getConstraintValidatorManager(validator, instanceToBeValidated);
        ConcurrentHashMap constraintValidatorCashe = getConstraintValidatorCashe(annotationToBeValidated, validatorInstance);
        ReflectionTestUtils.setField(constraintValidatorManager, "constraintValidatorCache", constraintValidatorCashe);

    }

    @SuppressWarnings("unchecked")
    private static <A extends Annotation, E> Class<A> getAnnotation(final ConstraintValidator<A, ?> validatorInstance) {
        return (Class<A>) ((ParameterizedType) validatorInstance.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    private static <E> ConstraintValidatorManager getConstraintValidatorManager(Validator validator, E instanceToBeValidated) {
        ValidationContext.ValidationContextBuilder
                valCtxBuilder = ReflectionTestUtils.<ValidationContext.ValidationContextBuilder>invokeMethod(validator,
                "getValidationContext");
        ValidationContext<E> validationContext = valCtxBuilder.forValidate(instanceToBeValidated);
        return validationContext.getConstraintValidatorManager();
    }

    private static <A extends Annotation, E> ConcurrentHashMap getConstraintValidatorCashe(Class<A> annotationToBeValidated,
                                                                                           final ConstraintValidator<A, ?> validatorInstance) {
        ConcurrentHashMap nonSpyHashMap = new ConcurrentHashMap();
        ConcurrentHashMap spyHashMap = spy(nonSpyHashMap);
        doAnswer((InvocationOnMock invocation) -> {
            Object key = invocation.getArguments()[0];
            Object keyAnnotation = ReflectionTestUtils.getField(key, "annotation");
            if (annotationToBeValidated.isInstance(keyAnnotation)) {
                return validatorInstance;
            }
            return nonSpyHashMap.get(key);

        }).when(spyHashMap).get(any());
        return spyHashMap;
    }
}

package com.redhat.example.type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.redhat.example.entity.DepositAllocationDataEntity;

// Test POJO class:入金充当試算応答
public class DepositAllocationResponseTypeTest {

    // Target POJO Class
    private DepositAllocationResponseType obj;

    // Expected Type Map
    private Map<String, String> expected_type_map;

    @BeforeEach
    public void beforeEach() {
        expected_type_map = new HashMap<String, String>();
        expected_type_map.put("service_request", "DepositAllocationRequestType");
        expected_type_map.put("response_result", "String");
        expected_type_map.put("err_code", "String");
        expected_type_map.put("err_context", "String");
        expected_type_map.put("deposit_allocation_data", "DepositAllocationDataEntity");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDepositAllocationResponseType(){
        try {
            obj = (DepositAllocationResponseType.class).getDeclaredConstructor().newInstance();
            Field[] fields = DepositAllocationResponseType.class.getDeclaredFields();
            for(Field field : fields){

                String fieldName = field.getName();
                String name = StringUtils.capitalize(fieldName);
                String getter = "get" + name;
                String setter = "set" + name;

                Object valueToSet = null;
                Class<?> dataType = field.getType();
                if (dataType.isAssignableFrom(String.class)) {
                    valueToSet = "str";
                } else if (dataType.isAssignableFrom(Long.class) || dataType.isAssignableFrom(long.class)) {
                    valueToSet = Long.valueOf("1");
                } else if (dataType.isAssignableFrom(Integer.class) || dataType.isAssignableFrom(int.class)) {
                    valueToSet = Integer.valueOf("1");
                } else if (dataType.isAssignableFrom(Double.class) || dataType.isAssignableFrom(double.class)) {
                    valueToSet = Double.valueOf("1.0");
                } else if (dataType.isAssignableFrom(Date.class)) {
                    valueToSet = new Date();
                } else if (dataType.isAssignableFrom(Boolean.class)) {
                    valueToSet = Boolean.FALSE;
                } else if (dataType.isAssignableFrom(BigDecimal.class)) {
                    valueToSet = new BigDecimal("1");
                } else if (dataType.isAssignableFrom(Map.class)) {
                    Map<String, String> map = new HashMap<String, String>();
                    valueToSet = map;
                } else if (dataType.isAssignableFrom(List.class)) {
                    List<String> list = new ArrayList<String>();
                    valueToSet = list;
                } else if (dataType.isAssignableFrom(DepositAllocationRequestType.class)) {
                    valueToSet = new DepositAllocationRequestType();
                } else if (dataType.isAssignableFrom(DepositAllocationDataEntity.class)) {
                    valueToSet = new DepositAllocationDataEntity();
                } 

                Method getterMethod = DepositAllocationResponseType.class.getMethod(getter);
                Method setterMethod = DepositAllocationResponseType.class.getMethod(setter, getterMethod.getReturnType());
                setterMethod.invoke(obj, valueToSet);
                Object result = getterMethod.invoke(obj);

                // Assert Getter, Setter
                assertEquals(result, valueToSet);

                // Field Type
                assertEquals(expected_type_map.get(fieldName), dataType.getSimpleName());
            }
            // Field Count
            assertEquals(expected_type_map.size(),fields.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

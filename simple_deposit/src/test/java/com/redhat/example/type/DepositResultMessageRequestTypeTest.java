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
import com.redhat.example.entity.KijitsuNyukinRequestEntity;
import com.redhat.example.entity.DepositDataEntity;

// Test POJO class:入金結果メッセージ要求
public class DepositResultMessageRequestTypeTest {

    // Target POJO Class
    private DepositResultMessageRequestType obj;

    // Expected Type Map
    private Map<String, String> expected_type_map;

    @BeforeEach
    public void beforeEach() {
        expected_type_map = new HashMap<String, String>();
        expected_type_map.put("deposit_request", "KijitsuNyukinRequestEntity");
        expected_type_map.put("deposit_result", "String");
        expected_type_map.put("err_code", "String");
        expected_type_map.put("err_context", "String");
        expected_type_map.put("deposit_data", "DepositDataEntity");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDepositResultMessageRequestType(){
        try {
            obj = (DepositResultMessageRequestType.class).getDeclaredConstructor().newInstance();
            Field[] fields = DepositResultMessageRequestType.class.getDeclaredFields();
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
                } else if (dataType.isAssignableFrom(KijitsuNyukinRequestEntity.class)) {
                    valueToSet = new KijitsuNyukinRequestEntity();
                } else if (dataType.isAssignableFrom(DepositDataEntity.class)) {
                    valueToSet = new DepositDataEntity();
                } 

                Method getterMethod = DepositResultMessageRequestType.class.getMethod(getter);
                Method setterMethod = DepositResultMessageRequestType.class.getMethod(setter, getterMethod.getReturnType());
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

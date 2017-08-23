package com.howard.www.core.hbatis.sql.sqltemplate.script;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName:  ExpressionEvaluator   
 * @Description:TODO 
 * @author: mayijie
 * @date:   2017年2月15日 下午9:27:12   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class ExpressionEvaluator {

	public boolean evaluateBoolean(String expression, Object parameterObject) {
		Object value = OgnlCache.getValue(expression, parameterObject);
		if (value instanceof Boolean)
			return (Boolean) value;
		if (value instanceof Number)
			return !new BigDecimal(String.valueOf(value))
					.equals(BigDecimal.ZERO);
		return value != null;
	}

	@SuppressWarnings("rawtypes")
	public Iterable<?> evaluateIterable(String expression,
			Object parameterObject) {
		Object value = OgnlCache.getValue(expression, parameterObject);
		if (value == null)
			throw new RuntimeException("The expression '" + expression
					+ "' evaluated to a null value.");
		if (value instanceof Iterable)
			return (Iterable<?>) value;
		if (value.getClass().isArray()) {
			int size = Array.getLength(value);
			List<Object> answer = new ArrayList<Object>();
			for (int i = 0; i < size; i++) {
				Object o = Array.get(value, i);
				answer.add(o);
			}
			return answer;
		}
		if (value instanceof Map) {
			return ((Map) value).entrySet();
		}
		throw new RuntimeException("Error evaluating expression '" + expression
				+ "'.  Return value (" + value + ") was not iterable.");
	}

}

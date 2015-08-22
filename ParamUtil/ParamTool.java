package com.wah.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Zero
 *
 */
public class ParamTool {

	/**
	 * 对参数进行值设置 利用Java Reflect
	 * @param from	数据来源的对象
	 * @param to	需要赋值的对象
	 * @param param	需要设置的属性字段名
	 */
	public static void  paramSet(Object from, Object to,  String[] param) {
		
		Field toField = null;
		Field fromField = null;
		List<String> args = null;
		/*如果没有传入相应的要传值的属性名 就按都存在的属性名进行传值*/
		if(param == null || param.length < 1) {
			Field[] toFields = to.getClass().getDeclaredFields();
			Field[] fromFields = from.getClass().getDeclaredFields();
			List<String> toFieldNames = new ArrayList<String>();
			List<String> fromFieldNames = new ArrayList<String>();

			for(Field field: toFields) {
				toFieldNames.add(field.getName());
			}
			for(Field field: fromFields) {
				fromFieldNames.add(field.getName());
			}
			args = getDistinctFieldName(toFieldNames, fromFieldNames);
		} else {
			args = Arrays.asList(param);
		}
		
		for(String name: args) {
			try {
				toField = to.getClass().getDeclaredField(name);
				fromField = from.getClass().getDeclaredField(name);
				
				/*不然没有权限对field的读取操作*/
				toField.setAccessible(true);
				fromField.setAccessible(true);
				/*基本数据类型的赋值*/
				if(isBasicDataType(toField)) {
				/*属性名*/
				//	System.out.println(toField.getName());
				//	System.out.println(fromField.getName());	
				/*属性的value*/
					Object value = fromField.get(from);
				//	System.out.println("value:" + value);
					toField.set(to, value);
				} else {
					try {
						toField.set(to, toField.getType().newInstance());
						
					} catch (InstantiationException e) {
						System.err.println("实例化失败!\n可能原因:"+toField.getType().getName()+"没有默认的构造函数");
						e.printStackTrace();
					}
					
					paramSet(fromField.get(from), toField.get(to), null);
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				System.err.println("没有["+name+"]字段!");
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	/**
	 * 得到不同的field name
	 * @param toFieldNames	
	 * @param fromFieldNames
	 * @return
	 */
	public static List<String> getDistinctFieldName(List<String> toFieldNames, List<String> fromFieldNames) {
		List<String> names = new ArrayList<String>();
		for(String toFieldName: toFieldNames) {
			for(String fromFieldName: fromFieldNames) {
				if(toFieldName.equals(fromFieldName)) {
					names.add(toFieldName);
				}
			}
		}
		return names;
	}
	
	/**
	 * 判断是否为基本的数据类型
	 * @param toField Field字段
	 * @return
	 */
	public static boolean isBasicDataType(Field toField) {
		if(toField.getType().equals(String.class)
				|| toField.getType().equals(Integer.class)
				|| toField.getType().equals(Double.class)
				|| toField.getType().equals(Float.class)
				|| toField.getType().equals(Long.class)
				|| toField.getType().equals(Boolean.class)
				|| toField.getType().equals(Byte.class)
				|| toField.getType().equals(Short.class)
				|| toField.getType().equals(Character.class)
				|| toField.getType().equals(Date.class)) {
			return true;
		}
		return false;
	}
	
	
	
	public static void main(String[] args) {
	}
}

package com.javaproject.admin.util;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class StringGeneratorUtil extends SequenceStyleGenerator {
	
//	private static String generateRandomString(int length) {
//		String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
//		String CHAR_UPPER = CHAR_LOWER.toUpperCase();
//		String NUMBER = "0123456789";
//
//		String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
//		SecureRandom random = new SecureRandom();
//
//		if (length < 1)
//			throw new IllegalArgumentException();
//
//		StringBuilder sb = new StringBuilder(length);
//
//		for (int i = 0; i < length; i++) {
//			// 0-62 (exclusive), random returns 0-61
//			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
//			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
//
//			sb.append(rndChar);
//		}
//
//		return sb.toString();
//	}
	
	public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
	public static final String VALUE_PREFIX_DEFAULT = "";
	
	private String prefix;
	
	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT = "%d";
	
	private String numberFormat;
	
	@Override
    public void configure(Type type, Properties properties, 
      ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, properties, serviceRegistry);
        prefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, properties, VALUE_PREFIX_DEFAULT);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, properties, NUMBER_FORMAT_DEFAULT);
    }

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
		return prefix + String.format(numberFormat, super.generate(session, obj));
	}
}

/* 
 * Copyright 2013 The Athena-Peacock Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * Bong-Jin Kwon	2013. 9. 27.		First Draft.
 */
package com.athena.peacock.controller.web.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.NumberUtils;

/**
 * <pre>
 *  화면(view)에서 전달된 number 값이 없을때(''일때) 0으로 변환해서 처리하도록함. 
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	public StringToNumberConverterFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
		// TODO Auto-generated method stub
		return new StringToNumber<T>(targetType);
	}
	
	private static final class StringToNumber<T extends Number> implements Converter<String, T> {

		private final Class<T> targetType;

		public StringToNumber(Class<T> targetType) {
			this.targetType = targetType;
		}

		public T convert(String source) {
			if (source.length() == 0) {
				source = "0";
			}
			return NumberUtils.parseNumber(source, this.targetType);
		}
	}

}
//end of StringToNumberConverterFactory.java
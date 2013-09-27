package com.athena.peacock.controller.web.common.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * <pre>
 * DTO Date type 을 json 문자열로 변환시 사용할 포맷 지정.
 *  - Dto getter 함수에서 @JsonSerialize(using = CustomDateSerializer.class) 처럼 사용할수 있음.
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
		String formattedDate = formatter.format(value);

		gen.writeString(formattedDate);

	}


}

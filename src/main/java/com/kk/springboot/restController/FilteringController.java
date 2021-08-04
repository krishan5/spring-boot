package com.kk.springboot.restController;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kk.springboot.entity.SomeBean;

/**
 * Here we are achieving dynamic filtering of class variables which we don't want to send back on response
 * on behalf of the different condition i.e., don't send var1, var2 in one request and don't send var2 only in another request.
 * 
 * Use @JsonIgnore or @JsonIgnoreProperties in case of static filtering of class variables which will be common for all cases.
 * That's why it is called static filtering.
 * Use @JsonFilter, MappingJacksonValue in case of dynamic filtering of class variables on behalf of specific condition.
 * That's why it is called dynamic filtering.
 */
@RestController
public class FilteringController {
	
	@GetMapping("/filtering-class-var")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		//Here we are mentioning that var1 and var2 variable's value will be returned on response.
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("var1", "var2");
		//Here we are mentioning that which class has above passed variables to be send in response and rest of variables will be filtered out.
		//Don't forget to mention same name in that class too in @JsonFilter annotation.
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		//Here we are adding filterProvider and class object whose variable going to be filtered out.
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}
	
	@GetMapping("/filtering-class-var-list")
	public MappingJacksonValue retrieveSomeBeans() {
		List<SomeBean> someBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value11", "value22", "value33"),
				new SomeBean("value111", "value222", "value333"));
		
		//All explanation is done in upper method. Here var1 and var2 variable's value will be returned on response.
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("var2", "var3");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		//Here the difference is that we are passing list of that object.
		MappingJacksonValue mapping = new MappingJacksonValue(someBeanList);
		mapping.setFilters(filterProvider);
		
		return mapping;
	}
	
}

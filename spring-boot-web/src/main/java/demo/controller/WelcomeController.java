package demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.bo.TestBO;
import demo.domain.CommCd;

@Controller
public class WelcomeController {
	final static Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@Autowired
	private TestBO testBO;
	
	/**
	 * Spring Boot auto-configures DeviceResolverHandlerInterceptor, DeviceWebArgumentResolver
	 * @param model
	 * @param device
	 * @return
	 */
	@RequestMapping("/")
	public String welcome(Map<String, Object> model, Device device) {
		model.put("time", new Date());
		model.put("message", this.message);
		model.put("device", device.toString());
		return "welcome";
	}

	@RequestMapping("/test")
	@ResponseBody
	public List<CommCd> test(Model model) {
		List<CommCd> commCdList = testBO.getCommCdList();

		return commCdList;
	}

}

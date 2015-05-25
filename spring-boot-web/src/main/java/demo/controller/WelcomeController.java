package demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
	@Autowired
	private StringRedisTemplate template;

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}

	@RequestMapping("/test")
	@ResponseBody
	public List<CommCd> test(Model model) {
		List<CommCd> commCdList = testBO.getCommCdList();

		return commCdList;
	}

	/**
	 * 예전버전 : https://github.com/dmajkic/redis/downloads, 현재는 더이상 windows 32bit
	 * 지원 안함 : https://github.com/MSOpenTech/redis
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/redis")
	@ResponseBody
	public String redis(Model model) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		String key = "spring.boot.redis.test";
		if (!this.template.hasKey(key)) {
			logger.info("There is no key : " + key);
			ops.set(key, "foo");
		}
		return "Found key " + key + ", value=" + ops.get(key);
	}
}

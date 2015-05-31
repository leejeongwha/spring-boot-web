package demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.domain.Notice;

@Controller
public class RedisController {
	final static Logger logger = LoggerFactory.getLogger(RedisController.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * redis 는 byte array 형태로 데이터를 저장함, 예전버전 :
	 * https://github.com/dmajkic/redis/downloads, 현재는 더이상 windows 32bit 지원 안함 :
	 * https://github.com/MSOpenTech/redis
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/redisObject")
	@ResponseBody
	public Notice redisObj(Model model) {
		redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

		Notice notice = new Notice();
		notice.setId(0);
		notice.setTitle("test title");
		notice.setContent("반갑습니다.");

		redisTemplate.opsForValue().set(notice.OBJECT_KEY, notice);

		return (Notice) redisTemplate.opsForValue().get(notice.OBJECT_KEY);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/redisString")
	@ResponseBody
	public String redisString(Model model) {
		String key = "test";
		if (!redisTemplate.hasKey(key)) {
			logger.info("redis already has " + key);
			redisTemplate.opsForValue().set(key, "leejeonwha");
		}

		return (String) redisTemplate.opsForValue().get(key);
	}
}

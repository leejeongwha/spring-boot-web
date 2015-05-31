package demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.domain.Notice;

@Controller
public class RedisController {
	final static Logger logger = LoggerFactory.getLogger(RedisController.class);

	@Resource(name = "redisTemplate")
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTempalte;

	/**
	 * redis 는 byte array 형태로 데이터를 저장함, 예전버전 :
	 * https://github.com/dmajkic/redis/downloads, 현재는 더이상 windows 32bit 지원 안함 :
	 * https://github.com/MSOpenTech/redis
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/redis-object")
	@ResponseBody
	public Notice redisObj(Model model) {
		Notice notice = new Notice();
		notice.setId(0);
		notice.setTitle("test title");
		notice.setContent("반갑습니다.");

		redisTemplate.opsForValue().set(Notice.OBJECT_KEY, notice);

		return (Notice) redisTemplate.opsForValue().get(Notice.OBJECT_KEY);
	}

	@RequestMapping("/redis-string")
	@ResponseBody
	public String redisString(Model model) {
		String key = "test";
		if (!stringRedisTempalte.hasKey(key)) {
			logger.info("redis has not key : " + key);
			stringRedisTempalte.opsForValue().set(key, "leejeongwha");
		} else {
			logger.info("redis already has key : " + key);
		}

		return stringRedisTempalte.opsForValue().get(key);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/redis-list")
	@ResponseBody
	public Notice redisList(Model model) {
		String key = "notice_list";

		Notice notice = new Notice();
		notice.setId(0);
		notice.setTitle("test title");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		notice.setContent(sdf.format(new Date()));

		redisTemplate.boundListOps(key).leftPush(notice);

		logger.info("redis list size is : " + redisTemplate.boundListOps(key).size());

		return (Notice) redisTemplate.boundListOps(key).index(0);

	}
}

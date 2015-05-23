package demo.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.CommCdRepository;
import demo.domain.CommCd;

@Service
public class TestBOImpl implements TestBO {
	@Autowired
	private CommCdRepository commCdRepository;

	@Override
	public List<CommCd> getCommCdList() {
		return commCdRepository.findAll();
	}

}

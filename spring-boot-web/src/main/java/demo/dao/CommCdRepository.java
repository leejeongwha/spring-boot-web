package demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.domain.CommCd;
import demo.domain.CommCdId;

public interface CommCdRepository extends JpaRepository<CommCd, CommCdId> {
}

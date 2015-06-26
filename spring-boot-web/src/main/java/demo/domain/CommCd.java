package demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "cc_comm_cd")
@IdClass(CommCdId.class)
public class CommCd implements Serializable {
	@Id
	@Column(name = "cd_cat_id")
	private String cdCatId;

	@Id
	@Column(name = "comm_cd")
	private String commCd;

	@Column(name = "upr_comm_cd")
	private String uprCommCd;

	public String getCdCatId() {
		return cdCatId;
	}

	public void setCdCatId(String cdCatId) {
		this.cdCatId = cdCatId;
	}

	public String getCommCd() {
		return commCd;
	}

	public void setCommCd(String commCd) {
		this.commCd = commCd;
	}

	public String getUprCommCd() {
		return uprCommCd;
	}

	public void setUprCommCd(String uprCommCd) {
		this.uprCommCd = uprCommCd;
	}
}

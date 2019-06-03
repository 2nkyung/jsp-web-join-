package com.osf.test.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberVO {
//	롬복이 깔려있고, 해당 프로젝트가 롬복을 바라보고 있기 때문에 @Data로 해결
	
	private Integer miNum;
	private String miName;
	private String miId;
	private String miPwd;
	private String miEmail;
	private Integer miTrans;
	private String miBirth;
	private String miZipcode;
	private String miAddr1;
	private String miAddr2;
	private String miCredat;
	private String miCretim;
	private String miModdat;
	private String miModtim;
	private String miMainImg;
	private String miNick;
	private String miEtc;
	
}

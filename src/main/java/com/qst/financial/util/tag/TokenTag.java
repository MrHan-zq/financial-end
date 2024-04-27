package com.qst.financial.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.UUID;

/**
 * 
 * @author qst
 * token生成
 */
public class TokenTag extends TagSupport {
	
	private static final long serialVersionUID = 7546213812316932961L;

	public int doStartTag() throws JspException {

		//得到uuid
		String uuid=UUID.randomUUID().toString().replace("-","");
		pageContext.getSession().setAttribute("token", uuid);
		
		String html="<input type='hidden' name='tokenName' readonly='readonly' id='tokenName' value="+uuid+">"; //readonly='readonly'
		
		try {
			pageContext.getOut().write(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	
}

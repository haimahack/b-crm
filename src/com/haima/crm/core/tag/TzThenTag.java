package com.haima.crm.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TzThenTag extends TagSupport{
	private static final long serialVersionUID = -2454822360456918650L;

	@Override
	public int doStartTag() throws JspException {
		TzIFTag parent = (TzIFTag)this.getParent();
		if(parent!=null && parent.getTest()){
			return EVAL_BODY_INCLUDE;//继续去执行标签提的内容
		}else{
			return SKIP_BODY;
		}
	}
	
//	tz:if
//	tz:elseif
//	tz:elseif
//	tz:else
}

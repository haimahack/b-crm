package com.haima.crm.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TzElseTag extends TagSupport{
	private static final long serialVersionUID = -2505201370194340967L;

	@Override
	public int doStartTag() throws JspException {
		TzIFTag parent = (TzIFTag)this.getParent();
		if(parent!=null && !parent.getTest()){
			return EVAL_BODY_INCLUDE;//继续去执行标签提的内容
		}else{
			return SKIP_BODY;
		}
	}
}

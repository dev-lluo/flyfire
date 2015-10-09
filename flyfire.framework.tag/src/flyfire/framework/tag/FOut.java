package flyfire.framework.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public abstract class FOut implements Tag {
	
	private final void $out() throws IOException {
		this.out(this.pageContext.getOut());
	}
	
	protected abstract void out(JspWriter writer) throws IOException;

	protected PageContext pageContext;

	protected Tag parent;

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return Tag.EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		try {
			this.$out();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	@Override
	public Tag getParent() {
		// TODO Auto-generated method stub
		return this.parent;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPageContext(PageContext arg0) {
		// TODO Auto-generated method stub
		this.pageContext = arg0;
	}

	@Override
	public void setParent(Tag arg0) {
		// TODO Auto-generated method stub
		this.parent = arg0;
	}
	
}

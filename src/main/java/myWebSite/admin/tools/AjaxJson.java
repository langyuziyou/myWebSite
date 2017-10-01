package myWebSite.admin.tools;

import java.io.Serializable;
import java.util.Map;

/**
 * $.ajax后需要接受的JSON
 * 
 * @author
 * 
 */
public class AjaxJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数
	private String errorCode;// 错误码
	private Integer totalSize;// 总记录数
	private Map<String, Object> user;// 当前操作用户

	public AjaxJson() {
	}

	public AjaxJson(String msg, boolean success) {
		this.msg = msg;
		this.success = success;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Map<String, Object> getUser() {
		return user;
	}

	public void setUser(Map<String, Object> user) {
		this.user = user;
	}

	// public String getJsonStr(){
	// obj.put("success", this.isSuccess());
	// obj.put("msg", this.getMsg());
	// obj.put("obj", this.obj);
	// obj.put("attributes", this.attributes);
	// return obj.toJSONString();
	// }
}

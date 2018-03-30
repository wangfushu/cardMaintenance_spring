package gmms.domain;

import gmms.util.JsonMapper;

/**
 * Created by wangfs on 2017/6/29. 返回标准的ajax响应格式
 */
public final class AjaxResponseBodyFactory {
	private AjaxResponseBodyFactory() {
	}

	public static <T> String createSuccessBody(boolean hasData, T t) {
		ResponseBody responseBody = new ResponseBody();
		DataBody<T> tDataBody = new DataBody<T>(hasData, t);
		responseBody.setDataBody(tDataBody);
		responseBody.setSuccess(true);
		return JsonMapper.nonEmptyMapper().toJson(responseBody);
	}

	public static String createFailBody(String errorMsg) {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setSuccess(false);
		responseBody.setMsg(errorMsg);
		return JsonMapper.nonEmptyMapper().toJson(responseBody);
	}

	private static class ResponseBody {
		private boolean success;
		private DataBody dataBody;
		private String msg;

		public ResponseBody() {
		}

		public ResponseBody(boolean success, DataBody dataBody, String msg) {
			this.success = success;
			this.dataBody = dataBody;
			this.msg = msg;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public DataBody getDataBody() {
			return dataBody;
		}

		public void setDataBody(DataBody dataBody) {
			this.dataBody = dataBody;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	private static class DataBody<T> {
		private boolean hasData;
		private T data;

		public DataBody() {
		}

		public DataBody(boolean hasData, T data) {
			this.hasData = hasData;
			this.data = data;
		}

		public boolean isHasData() {
			return hasData;
		}

		public void setHasData(boolean hasData) {
			this.hasData = hasData;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}
	}
}

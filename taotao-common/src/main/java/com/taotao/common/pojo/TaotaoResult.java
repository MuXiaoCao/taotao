package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * Description:淘淘商城自定义响应结构
 * </p>
 * <p>
 * TaotaoResult.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年5月24日 下午10:08:38
 * @version: 1.0
 */
public class TaotaoResult implements Serializable{

	/**
	 * dubbo传输必须支持序列化
	 */
	private static final long serialVersionUID = 1L;

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 响应业务状态
	private int status;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object data;

	public static TaotaoResult build(Integer status, String msg, Object data) {
		return new TaotaoResult(status, msg, data);
	}

	public static TaotaoResult build(Integer status, String msg) {
		return new TaotaoResult(status, msg, null);
	}

	public static TaotaoResult ok(Object data) {
		return new TaotaoResult(data);
	}

	public static TaotaoResult ok() {
		return new TaotaoResult(null);
	}

	public TaotaoResult() {

	}

	public TaotaoResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public TaotaoResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 将json结果集转化为taotaoresult对象
	 * 
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static TaotaoResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, TaotaoResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				} else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将json数据转换为taotaoresult对象
	 * 
	 * @param json
	 * @return
	 */
	public static TaotaoResult format(String json) {
		try {
			return MAPPER.readValue(json, TaotaoResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static TaotaoResult formaToList(String jsonData, Class<?> clazz) {
		try {

			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}
}

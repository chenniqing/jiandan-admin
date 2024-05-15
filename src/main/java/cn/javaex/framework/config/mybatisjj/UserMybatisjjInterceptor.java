package cn.javaex.framework.config.mybatisjj;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import cn.javaex.framework.util.UserUtils;
import cn.javaex.mybatisjj.config.interceptor.BeforeSaveEntityInterceptor;

@Component
public class UserMybatisjjInterceptor implements BeforeSaveEntityInterceptor {

	@Override
	public void insertFill(Object entity) {
		this.fieldFillWhenNull(entity, "createTime", LocalDateTime.now());
		this.fieldFillWhenNull(entity, "updateTime", LocalDateTime.now());
		
		try {
			String userId = UserUtils.getCurUser().getId();
			this.fieldFillWhenNull(entity, "createBy", userId);
			this.fieldFillWhenNull(entity, "updateBy", userId);
		} catch (Exception e) {
			
		}
	}

	@Override
	public void updateFill(Object entity) {
		this.fieldFill(entity, "updateTime", LocalDateTime.now());
		try {
			String userId = UserUtils.getCurUser().getId();
			this.fieldFill(entity, "updateBy", userId);
		} catch (Exception e) {
			
		}
		
	}
	
}

package com.yanda.service;

import com.yanda.common.ServerResponse;

public interface IUserService {
	public ServerResponse loginLogic(String username,String password);

}

package com.apress.timesheets.hessian;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.service.UserAccountService;

public class HessianUserAccountServiceImpl
	implements HessianUserAccountService
{
	private UserAccountService service;

	public List<String> listUserNames() {
		final List<UserAccount> list = service.listUsers();
		final List<String> names = new ArrayList<String>();
		for( final UserAccount account : list ) {
			names.add(account.getAccountName());
		}
		return names;
	}
	
	@Required
	public void setUserAccountService(final UserAccountService service) {
		this.service = service;
	}
}

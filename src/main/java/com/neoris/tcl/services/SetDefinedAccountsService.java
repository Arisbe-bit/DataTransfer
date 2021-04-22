package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetDefinedAccountsDao;
import com.neoris.tcl.models.SetDefinedAccounts;
import com.neoris.tcl.models.SetDefinedAccountsPK;
import com.neoris.tcl.models.SetHfmCodes;



@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetDefinedAccountsService  implements ISetDefinedAccountsService{

	@Autowired
	private ISetDefinedAccountsDao data;

	

		@Override
	public Optional<SetDefinedAccounts> findById(SetDefinedAccountsPK id) {
		return data.findById(id);
	}
		
		@Override
		public List<SetDefinedAccounts> findAll() {
			return (List<SetDefinedAccounts>) data.findAll();
		}

		@Override
		public SetDefinedAccounts save(SetDefinedAccounts entity) {
			return data.save(entity);
		}

		@Override
		public List<SetDefinedAccounts> saveAll(List<SetDefinedAccounts> entityList) {
			return (List<SetDefinedAccounts>) data.saveAll(entityList);
		}

		@Override
		public void delete(SetDefinedAccounts entity) {
			data.delete(entity);
		}

		@Override
		public void deleteAll(List<SetDefinedAccounts> entityList) {
			data.deleteAll(entityList);		
		}

		

}

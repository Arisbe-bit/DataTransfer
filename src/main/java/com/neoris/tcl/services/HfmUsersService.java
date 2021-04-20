package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmUsersDao;
import com.neoris.tcl.models.HfmUsers;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmUsersService implements IHfmUSersService{


	     
		@Autowired
		private IHfmUsersDao data;

		@Override
		public Optional<HfmUsers> findById(String id) {
			return data.findById(id);
		}

		@Override
		public List<HfmUsers> findAll() {
			return (List<HfmUsers>) data.findAll();
		}

		@Override
		public HfmUsers save(HfmUsers entity) {
			return data.save(entity);
		}

		@Override
		public List<HfmUsers> saveAll(List<HfmUsers> entityList) {
			return (List<HfmUsers>) data.saveAll(entityList);
		}

		@Override
		public void delete(HfmUsers entity) {
			data.delete(entity);
		}

		@Override
		public void deleteAll(List<HfmUsers> entityList) {
			data.deleteAll(entityList);
		}
		
		

}

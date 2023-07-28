package com.mmsystem.property.service;

import java.util.List;

import com.mmsystem.property.model.MmsUser;



public interface IMmspService {  
	      
    public boolean saveUser(MmsUser mmsUser);  
    public List<MmsUser> getUsers();  
    public boolean deleteUser(MmsUser mmsUser);  
    public MmsUser getUserByID(MmsUser mmsUser);  
    public boolean updateUser(MmsUser mmsUser);  
}

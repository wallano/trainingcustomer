package co.com.bancolombia.clientes.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import co.com.bancolombia.clientes.entities.UserInfo;


public interface UserInfoRepository extends CrudRepository<UserInfo, String>{
	
	public UserInfo findByUserNameAndEnabled(String userName, short enabled);

	public List<UserInfo> findAllByEnabled(short enabled);

	public UserInfo findById(Long id);
//
//	@Override
//	public UserInfo save(UserInfo userInfo);

	public void deleteById(Long id);

}

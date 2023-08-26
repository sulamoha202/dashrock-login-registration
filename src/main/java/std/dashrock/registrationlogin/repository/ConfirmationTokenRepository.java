package std.dashrock.registrationlogin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import std.dashrock.registrationlogin.entity.ConfirmationToken;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken,Long>{
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    
}

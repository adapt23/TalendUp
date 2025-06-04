package com.sesame.TalentUp.repository;

import com.sesame.TalentUp.entity.Certification;
import com.sesame.TalentUp.entity.Employee;
import com.sesame.TalentUp.entity.Formation;
import org.springframework.data.repository.CrudRepository;

public interface ICertifactionRepository extends CrudRepository<Certification,Integer> {
    boolean existsByEmployerAndFormation(Employee employer, Formation formation);

}

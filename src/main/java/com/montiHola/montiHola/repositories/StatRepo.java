package com.montiHola.montiHola.repositories;

import com.montiHola.montiHola.domains.Statistic;
import org.springframework.data.repository.CrudRepository;

public interface StatRepo extends CrudRepository<Statistic, Integer> {

}

package com.springboot.boilerplate.dao;

import com.springboot.boilerplate.entity.Sample;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SampleDao extends CrudRepository<Sample, Long>  {

  @Query(
      value =
          "SELECT "
              + "sa "
              + "FROM Sample AS sa "
              + "ORDER BY sa.id DESC")
  List<Sample> getRecords(Pageable pageable);

}

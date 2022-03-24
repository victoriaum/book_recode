package com.book.record.domain;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends JpaRepository<Record, String> {

  @Query("SELECT r FROM Record r WHERE r.record_date LIKE :date%")
  Collection<Record> getList(@Param("date") String date);

  @Query("SELECT r FROM Record r WHERE r.reader=:member AND r.record_date LIKE :date%")
  Collection<Record> getListWithMember(@Param("date") String date, @Param("member") String member);

  @Query("SELECT COUNT(DISTINCT r.ISBN) FROM Record r WHERE r.record_date LIKE :date%")
  int totalBooks(@Param("date") String date);

  @Query("SELECT COUNT(DISTINCT r.reader) FROM Record r WHERE r.record_date LIKE :date%")
  int memberCount(@Param("date") String date);
}


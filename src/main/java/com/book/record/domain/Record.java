package com.book.record.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Record {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long record_no;

  @Column(columnDefinition = "TEXT", length=100, nullable = false, unique = true)
  private String reader;

  @Column(columnDefinition = "TEXT", length=100, nullable = false)
  private String ISBN;
  private String record_date;

  @Builder
  public Record(Long record_no, String reader, String ISBN, String record_date){
    this.record_no = record_no;
    this.reader = reader;
    this.ISBN = ISBN;
    this.record_date = record_date;
  }





}

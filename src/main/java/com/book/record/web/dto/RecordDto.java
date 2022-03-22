package com.book.record.web.dto;

import com.book.record.domain.Record;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RecordDto {
  private Long record_no;
  private String reader;
  private String ISBN;
  private String record_date;

  public RecordDto(Record entity){
    this.record_no = entity.getRecord_no();
    this.reader = entity.getReader();
    this.ISBN = entity.getISBN();
    this.record_date = entity.getRecord_date();
  }

  public Record toEntity(){
    return Record.builder()
        .record_no(record_no)
        .reader(reader)
        .ISBN(ISBN)
        .record_date(record_date)
        .build();
  }

}

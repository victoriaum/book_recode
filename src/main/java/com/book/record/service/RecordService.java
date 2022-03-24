package com.book.record.service;

import com.book.record.domain.RecordRepository;
import com.book.record.web.dto.RecordDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecordService {
  public final RecordRepository recordRepository;

  public List<RecordDto> bookCount(String date, String member) {
    if("all".equals(member)){
      return recordRepository.getList(date).stream()
          .map(RecordDto::new)
          .collect(Collectors.toList());
    }
    else {
      return recordRepository.getListWithMember(date,member).stream()
          .map(RecordDto::new)
          .collect(Collectors.toList());
    }
  }

  public int totalBooks(String date) {
    return recordRepository.totalBooks(date);
  }

  public int memberCount(String date) {
    return recordRepository.memberCount(date);
  }

  public List<String> bookList(String date) {
    return recordRepository.bookList(date);
  }
}
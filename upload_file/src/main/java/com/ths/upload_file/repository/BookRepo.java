package com.ths.upload_file.repository;

import com.ths.upload_file.entity.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookModel, Long> {
}

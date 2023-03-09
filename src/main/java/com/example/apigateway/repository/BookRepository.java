package com.example.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apigateway.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
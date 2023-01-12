package ru.netology.model;

import lombok.Data;

@Data
public class Post {

  private long id;
  private String content;
  private boolean removed;
}

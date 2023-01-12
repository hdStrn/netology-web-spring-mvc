package ru.netology.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {

  private long id;
  private String content;
  @JsonIgnore
  private boolean removed;
}

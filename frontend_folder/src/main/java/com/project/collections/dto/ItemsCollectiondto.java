package com.project.collections.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemsCollectiondto {
    @Column(name = "id")
    private long id;
    private String name_collection;
    private String user_id;
}
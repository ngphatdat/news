package com.news.news.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity  {
    @Column(name = "id")
    private long id;

}

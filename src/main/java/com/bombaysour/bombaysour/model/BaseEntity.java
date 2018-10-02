package com.bombaysour.bombaysour.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This is the base entity with basic field (id, available)
 * @param <T>
 */
@MappedSuperclass
public class BaseEntity <T extends BaseEntity>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Boolean available;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T)this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", available=" + available +
                '}';
    }
}

package com.manzurola.prodigy.common;

import java.time.LocalDateTime;

public class Entity<T> {

    private final Id<T> id;
    private final LocalDateTime creationTime;
    private final LocalDateTime lastUpdateTime;

    public Entity(Id<T> id, LocalDateTime creationTime, LocalDateTime lastUpdateTime) {
        this.id = id;
        this.creationTime = creationTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Entity() {
        this(Id.next(), LocalDateTime.now(), LocalDateTime.now());
    }

    public Id<T> getId() {
        return id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }
}

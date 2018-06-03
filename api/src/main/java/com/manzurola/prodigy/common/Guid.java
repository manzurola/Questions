package com.manzurola.prodigy.common;

import java.io.Serializable;
import java.util.UUID;

public class Guid<T> implements Serializable, Comparable<Guid<T>> {
    public final String id;

    public Guid(String id) {
        this.id = id;
        UUID.fromString(id.toLowerCase());
    }

    public Guid(UUID id) {
        this(id.toString());
    }

    public static <T> Guid<T> of(String id) {
        return new Guid(id);
    }

    public static <T> Guid<T> of(UUID id) {
        return new Guid(id);
    }

    public static <T> Guid<T> random() {
        return of(UUID.randomUUID());
    }

    public <R> Guid<R> asIdOf(Class<R> type) {
        return of(this.getId());
    }

    public String getId() {
        return id;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && o instanceof Guid) {
            Guid guid1 = (Guid) o;
            if (this.id != null) {
                if (!this.id.equals(guid1.id)) {
                    return false;
                }
            } else if (guid1.id != null) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

    public int compareTo(Guid<T> o) {
        return this.id.compareTo(o.id);
    }

    public String toString() {
        return this.id;
    }

}
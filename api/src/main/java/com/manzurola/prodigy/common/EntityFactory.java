package com.manzurola.prodigy.common;

import java.util.UUID;

public abstract class EntityFactory {

    public String nextId() {
        return UUID.randomUUID().toString();
    }
}

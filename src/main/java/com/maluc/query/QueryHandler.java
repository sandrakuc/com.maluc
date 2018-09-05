package com.maluc.query;

public interface QueryHandler <T extends Query, V> {
    V handle(T query) throws Exception;
}

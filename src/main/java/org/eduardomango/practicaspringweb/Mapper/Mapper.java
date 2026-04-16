package org.eduardomango.practicaspringweb.Mapper;

public interface Mapper <T,D>{
D convertToDto (T entity);
T convertToEntity (D dto);
}

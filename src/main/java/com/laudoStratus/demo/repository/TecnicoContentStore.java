package com.laudoStratus.demo.repository;

import com.laudoStratus.demo.models.Tecnico;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoContentStore extends ContentStore<Tecnico, String> {
}

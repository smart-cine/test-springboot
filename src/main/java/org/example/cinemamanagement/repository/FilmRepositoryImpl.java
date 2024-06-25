//package org.example.cinemamanagement.repository;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.Query;
//import org.example.cinemamanagement.model.Film;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public class FilmRepositoryImpl implements FilmRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Film save(Film film) {
//        entityManager.persist(film);
//        return film;
//    }
//
//
//    @Override
//    public void update() {
//
//    }
//
//    @Override
//    public List<Film> findAll(String sqlQuery) {
//        Query query = entityManager.createQuery(sqlQuery, Film.class);
//        return query.getResultList();
//    }
//
//    public void deleteById(UUID id) {
//        Film film = entityManager.find(Film.class, id);
//        entityManager.remove(film);
//    }
//
//    @Override
//    public Optional<Film> findById(UUID id) {
//        Film film = entityManager.find(Film.class, id);
//        return Optional.ofNullable(film);
//    }
//
//    @Override
//    public Optional<Film> findFilmByTitle(String name) {
//        Query query = entityManager.createQuery("select f from Film f where f.title = ?1", Film.class);
//        query.setParameter(1, name);
//        return Optional.ofNullable((Film) query.getSingleResult());
//    }
//
//
//}

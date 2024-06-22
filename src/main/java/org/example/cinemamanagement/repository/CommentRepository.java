package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query("SELECT c FROM Comment c WHERE c.film.id = ?1 AND c.user.id != ?2")
    List<Comment> findAllByFilmIdWithoutOfUser(UUID filmID, UUID userID);

    @Query("SELECT c FROM Comment c WHERE c.film.id = ?1 AND c.user.id = ?2")
    List<Comment> findCommentByFilmIdAndUserId(UUID filmID, UUID userID);
}

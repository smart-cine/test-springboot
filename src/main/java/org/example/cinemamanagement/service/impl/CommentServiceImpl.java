package org.example.cinemamanagement.service.impl;

import org.example.cinemamanagement.dto.CommentDTO;
import org.example.cinemamanagement.model.Comment;
import org.example.cinemamanagement.model.Film;
import org.example.cinemamanagement.model.User;
import org.example.cinemamanagement.payload.request.AddCommentRequest;
import org.example.cinemamanagement.payload.response.CommentResponse;
import org.example.cinemamanagement.repository.CommentRepository;
import org.example.cinemamanagement.repository.FilmRepository;
import org.example.cinemamanagement.repository.UserRepository;
import org.example.cinemamanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserRepository userRepository;


    // Fix.
    @Override
    public List<CommentResponse> getAllCommentsOfFilmWithoutCommentOfCurrentUser(UUID filmID) {
//        List<Comment> comments = commentRepository
//                .findAllCommentByFilmIdAndExceptForUserId(commentDTO.getFilmId(), commentDTO.getUserId());
//        return comments.stream()
//                .map(comment -> CommentResponse.builder()
//                        // Khong can tra ve commentId va userId
//                        .fullName(comment.getUser().getLastName() + " " + comment.getUser().getFirstName())
//                        .comment(comment.getBody())
//                        .build()
//                ).collect(Collectors.toList());

        // Fix

        User userTemp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userTemp.getId()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        List<Comment> comments = commentRepository.findAllByFilmIdWithoutOfUser(filmID, user.getId());
        return comments.stream().map(
                comment -> CommentResponse.builder()
                        .commentId(comment.getId())
                        .fullName(comment.getUser().getLastName() + " " + comment.getUser().getFirstName())
                        .comment(comment.getBody())
                        .build()
        ).toList();
    }

    @Override
    public List<CommentResponse> getCommentByFilmIdAndUserId(CommentDTO commentDTO) {
        List<Comment> comments = commentRepository.findCommentByFilmIdAndUserId(commentDTO.getFilmId(), commentDTO.getUserId());
        return comments.stream()
                .map(comment -> CommentResponse.builder()
                        .commentId(comment.getId()) // Tra them ve commentId de sua va xoa
                        .fullName(comment.getUser().getLastName() + " " + comment.getUser().getFirstName())
                        .comment(comment.getBody())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public String addComment(AddCommentRequest addCommentRequest) {
        User userTemp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userTemp.getId()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        Film film = filmRepository.findById(addCommentRequest.getDestId())
                .orElseThrow(() -> new RuntimeException("Film not found"));
        if (addCommentRequest.getBody() == null || addCommentRequest.getBody().isBlank()) {
            throw new RuntimeException("Comment not allow null or empty");
        }

        commentRepository.save(Comment.builder()
                .user(user)
                .body(addCommentRequest.getBody())
                .film(film)
                .build()
        );

        return "Display: " + user.getLastName() + " " + user.getFirstName() + " / " + film.getTitle() + " / " + addCommentRequest.getBody();
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        if (commentDTO.getBody() == null)
            throw new RuntimeException("Comment not allow NULL");
        comment.setBody(commentDTO.getBody());
        commentRepository.save(comment);
        return CommentDTO.builder()
                .commentId(comment.getId())  // test tra ve 2 tham so can kiem tra
                .body(comment.getBody())
                .build();
    }

    @Override
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}

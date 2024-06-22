package org.example.cinemamanagement.service;

import org.example.cinemamanagement.dto.CommentDTO;
import org.example.cinemamanagement.payload.request.AddCommentRequest;
import org.example.cinemamanagement.payload.response.CommentResponse;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    public List<CommentResponse> getAllCommentsOfFilmWithoutCommentOfCurrentUser(UUID filmID);

    public List<CommentResponse> getCommentByFilmIdAndUserId(CommentDTO commentDTO);

    public String addComment(AddCommentRequest addCommentRequest);

    public CommentDTO updateComment(CommentDTO commentDTO);

    public void deleteComment(UUID id);

}

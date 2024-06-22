package org.example.cinemamanagement.controller;

import org.example.cinemamanagement.dto.CommentDTO;
import org.example.cinemamanagement.payload.request.AddCommentRequest;
import org.example.cinemamanagement.payload.response.CommentResponse;
import org.example.cinemamanagement.payload.response.DataResponse;
import org.example.cinemamanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest addCommentRequest) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Add comment successfully");
        dataResponse.setData(commentService.addComment(addCommentRequest));

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<?> getAllCommentByFilmIdWithoutCommentsOfCurrentUserWhoIsLoggin(@PathVariable UUID filmId)
    {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get all comment without current user successfully");
        dataResponse.setData(commentService
                .getAllCommentsOfFilmWithoutCommentOfCurrentUser(filmId));

        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCommentByFilmIdAndUserId(@RequestBody CommentDTO commentDTO)
    {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Get comment successfully");
        dataResponse.setData(commentService
                .getCommentByFilmIdAndUserId(commentDTO));

        return ResponseEntity.ok(dataResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Update comment successfully");
        dataResponse.setData(commentService.updateComment(commentDTO));

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage("Comment deleted successfully");
        return ResponseEntity.ok(dataResponse);
    }
}

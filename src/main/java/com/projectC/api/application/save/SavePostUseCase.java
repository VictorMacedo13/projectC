package com.projectC.api.application.save;

import com.projectC.api.domain.model.Post;
import com.projectC.api.domain.model.Save;
import com.projectC.api.domain.model.User;
import com.projectC.api.domain.repository.PostRepository;
import com.projectC.api.domain.repository.SaveRepository;
import org.springframework.stereotype.Component;

@Component
public class SavePostUseCase {

    private final PostRepository postRepository;
    private final SaveRepository saveRepository;

    public SavePostUseCase(PostRepository postRepository, SaveRepository saveRepository) {
        this.postRepository = postRepository;
        this.saveRepository = saveRepository;
    }

    public Save execute(User user, String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post não encontrado"));

        if (post.getUser().getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("Você não pode salvar o seu próprio post");
        }

        if (saveRepository.existsByPostIdAndUserId(postId, user.getId())) {
            throw new IllegalArgumentException("Post já salvo");
        }

        Save save = new Save();
        save.setPostId(postId);
        save.setUserId(user.getId());
        save.setCoordinateX(post.getCoordinateX());
        save.setCoordinateY(post.getCoordinateY());
        save.setCoordinateZ(post.getCoordinateZ());
        save.setUserPictureUrl(user.getProfilePictureUrl());
        save.setPostImageUrl(post.getPostImageUrl());
        save.setPostTitle(post.getReviewTitle());
        save.setPostLocalName(post.getLocalName());

        post.setSavesCount(post.getSavesCount() + 1);
        postRepository.save(post);

        return saveRepository.save(save);
    }
}


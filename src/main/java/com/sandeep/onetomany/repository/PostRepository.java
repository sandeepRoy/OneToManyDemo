//package com.sandeep.onetomany.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.sandeep.onetomany.model.Post;
//
//@Repository
//public interface PostRepository extends JpaRepository<Post, Long>{
//	Page<Post> findByUserId(Long userId, Pageable pageable);
//	Optional<Post> findByIdAndPostId(Long id, Long userId);
//}

package kr.co.greendae.service;


import kr.co.greendae.dto.community.CommentDTO;
import kr.co.greendae.entity.community.comment.BasicComment;
import kr.co.greendae.entity.user.User;
import kr.co.greendae.repository.community.comment.BasicCommentRepository;
import kr.co.greendae.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final BasicCommentRepository basicCommentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<CommentDTO> findByParent(int parent){

        List<BasicComment> basicCommentList = basicCommentRepository.findByParent(parent);

        List<CommentDTO> commentDTOList = basicCommentList.stream().map(entity -> {
            CommentDTO commentDTO = modelMapper.map(entity, CommentDTO.class);
            return commentDTO;
        }).toList();

        return commentDTOList;
    }

    public CommentDTO save(CommentDTO commentDTO){

        User user = userRepository.findById(commentDTO.getWriter()).get();

        BasicComment basicComment = modelMapper.map(commentDTO, BasicComment.class);
        basicComment.setUser(user);

        BasicComment savedComment = basicCommentRepository.save(basicComment);
        log.info("savedComment : {}", savedComment);

        return modelMapper.map(savedComment, CommentDTO.class);
    }
}

package com.example.slack.service;


import com.example.slack.dto.MemberWorkDto;
import com.example.slack.dto.MembersRequestDto;
import com.example.slack.dto.WorkspacesRequestDto;
import com.example.slack.model.Members;
import com.example.slack.model.User;
import com.example.slack.model.Workspaces;
import com.example.slack.repository.MembersRepository;
import com.example.slack.repository.WorkspacesRepository;
import com.example.slack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service

public class WorkspacesService {
    private final WorkspacesRepository workspacesRepository;
    private final MembersRepository membersRepository;

    //워크스페이스 생성
    @Transactional
    public Optional<Workspaces> createWs(
            WorkspacesRequestDto workspacesRequestDto, User user) {

        String workName = workspacesRequestDto.getWorkName();
        if(workName == null){
            throw new IllegalArgumentException("워크스페이스 이름을 입력해주세요.");
        }
        Workspaces workspaces = new Workspaces(workspacesRequestDto, user);
        Workspaces workspacesTmp = workspacesRepository.save(workspaces);
        String memberName = user.getUsername();
        MemberWorkDto memberWorkDto = new MemberWorkDto(memberName);
        Members members = new Members(memberWorkDto,workspacesTmp, user);
        membersRepository.save(members);

        return workspacesRepository.findById(workspacesTmp.getWorkId());

    }

    //워크스페이스 삭제
    @Transactional
    public Long deleteWs(Long workId, UserDetailsImpl userDetails){
        Workspaces workspaces = workspacesRepository.findById(workId).orElseThrow(
                () -> new IllegalArgumentException("워크스페이스가 존재하지 않습니다.")
        );
        User user = workspaces.getUser();
        Long deleteId = user.getUserid();
        if(!Objects.equals(userDetails.getUser().getUserid(),deleteId)){
            throw new IllegalArgumentException("개설자만 삭제할 수 있습니다.");
        }

        workspacesRepository.deleteById(workId);
        return workId;
    }
}

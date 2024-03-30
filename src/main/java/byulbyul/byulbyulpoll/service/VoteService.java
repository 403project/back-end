package byulbyul.byulbyulpoll.service;

import byulbyul.byulbyulpoll.entity.Member;
import byulbyul.byulbyulpoll.entity.Project;
import byulbyul.byulbyulpoll.entity.Vote;
import byulbyul.byulbyulpoll.repository.MemberRepository;
import byulbyul.byulbyulpoll.repository.ProjectRepository;
import byulbyul.byulbyulpoll.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final VoteRepository voteRepository;

    public long voteByMember(long projectId, String memberEmail){
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 회원이 존재하지 않습니다."));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        List<Vote> voteToCancel = voteRepository.findByProject_IdAndMember_Email(projectId, memberEmail);
        voteRepository.deleteAll(voteToCancel);

        Vote vote = Vote.voteByMember(project, member);
        voteRepository.save(vote);


        return vote.getId();
    }


}

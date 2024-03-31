package byulbyul.byulbyulpoll.service;

import byulbyul.byulbyulpoll.entity.Member;
import byulbyul.byulbyulpoll.entity.Project;
import byulbyul.byulbyulpoll.entity.Vote;
import byulbyul.byulbyulpoll.repository.MemberRepository;
import byulbyul.byulbyulpoll.repository.ProjectRepository;
import byulbyul.byulbyulpoll.repository.VoteRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final VoteRepository voteRepository;
    private final EntityManager em;

    @Transactional
    public long voteByMember(long projectId, String memberEmail){
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 회원이 존재하지 않습니다."));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        if(!project.getPoll().isOngoing()){
            throw new IllegalArgumentException("투표 기간이 아닙니다.");
        }

        List<Vote> voteToCancel = voteRepository.findByPoll_IdAndMember_Email(project.getPoll().getId(), memberEmail);
        for (Vote vote : voteToCancel) {
            var voteProject = vote.getProject();
            voteProject.cancleUpvote();
        }
        voteRepository.deleteAll(voteToCancel);
        log.info("voteCount before: {}", project.getVoteCount());
        log.info("is project managed: {}", em.contains(project));
        project.upvote();
        log.info("voteCount after: {}", project.getVoteCount());
        Vote vote = Vote.voteByMember(project, member);
        voteRepository.save(vote);


        return vote.getId();
    }




}

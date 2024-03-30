package byulbyul.byulbyulpoll.service;

import byulbyul.byulbyulpoll.entity.Poll;
import byulbyul.byulbyulpoll.repository.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PollService {
    private final PollRepository pollRepository;

    @Transactional
    public long createPoll(String title, LocalDateTime startDate, LocalDateTime endDate) {
        Poll poll = new Poll(title, startDate, endDate);
        pollRepository.save(poll);
        return poll.getId();
    }

    public List<Poll> getPolls() {
        return pollRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }


}

package criteria;

import lombok.AllArgsConstructor;
import model.Tweet;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CriteriaTweetOlderThan implements Criteria<Tweet> {
    private final Instant instant;

    @Override
    public List<Tweet> meetsCriteria(List<Tweet> list) {
        return list.stream()
                .filter(tweet -> tweet.getCreatedAt().compareTo(instant) > 0)
                .collect(Collectors.toList());
    }
}

package criteria;

import lombok.AllArgsConstructor;
import model.Tweet;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CriteriaTweetAuthor implements Criteria<Tweet> {
    private final String authorUserId;
    @Override
    public List<Tweet> meetsCriteria(List<Tweet> list) {
        return list.stream()
                .filter(tweet -> tweet.getAuthorUserId().equals(authorUserId))
                .collect(Collectors.toList());
    }
}

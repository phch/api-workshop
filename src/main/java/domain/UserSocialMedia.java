package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.Tweet;
import model.User;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserSocialMedia extends User {
    private List<Tweet> tweets;

    // Hack for now
    public static UserSocialMedia from(User user) {
        UserSocialMedia userSocialMedia = new UserSocialMedia();
        userSocialMedia.setId(user.getId());
        userSocialMedia.setCreatedAt(user.getCreatedAt());
        userSocialMedia.setFirstName(user.getFirstName());
        userSocialMedia.setLastName(user.getLastName());
        userSocialMedia.setEmail(user.getEmail());
        return userSocialMedia;
    }
}

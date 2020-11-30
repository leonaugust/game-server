package platform.session;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import platform.domain.IUser;
import platform.service.UserProfileRegistry;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class SessionMap {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserProfileRegistry userProfileRegistry;

    private final ConcurrentMap<Integer, Session> activeSessions = new ConcurrentHashMap<>();

    public Session getSessionByProfileId(int profileId) {
        return activeSessions.get(profileId);
    }

    public Session createNewSession(IUser profile, Channel channel) {
        return activeSessions.computeIfAbsent(profile.id(), key -> {
            log.info("Create new Session for profile: {}", key);
            return new Session(profile, channel);
        });
    }

    public Session closeSession(int profileId) {
        log.info("Close Session for profile: {}", profileId);
        var session = activeSessions.remove(profileId);
        if (session != null && session.profile != null) {
            userProfileRegistry.updateUserProfile(session.profile);
        }
        return session;
    }

}

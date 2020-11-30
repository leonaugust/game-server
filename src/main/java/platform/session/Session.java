package platform.session;

import io.netty.channel.Channel;
import platform.domain.IUser;

import java.time.LocalDateTime;

public final class Session {

    public static final Session EMPTY_SESSION = new Session(null, null);

    public final IUser profile;

    public final Channel channel;

    public final LocalDateTime createdAt = LocalDateTime.now();

    public Session(IUser profile, Channel channel) {
        this.profile = profile;
        this.channel = channel;
    }

}

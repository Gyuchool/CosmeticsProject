package cosmetics.demo.Domain.Repository;

import cosmetics.demo.Domain.Entity.PersistentLogins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

public class RememberTokenRepository implements PersistentTokenRepository {

    @Autowired
    RememberMeRepository rememberMeRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogins persistentLogins = new PersistentLogins();
        persistentLogins.setToken(persistentLogins.getToken());
        rememberMeRepository.save(persistentLogins);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        ;
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        rememberMeRepository.deleteByName(username);
    }
}

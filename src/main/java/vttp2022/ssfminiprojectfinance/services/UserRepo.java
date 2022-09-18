package vttp2022.ssfminiprojectfinance.services;

import vttp2022.ssfminiprojectfinance.models.User;

public interface UserRepo {

    public int save(final User userinfo);

    public User findByEmail(final String email);
}

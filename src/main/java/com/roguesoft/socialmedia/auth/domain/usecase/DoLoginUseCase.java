package com.roguesoft.socialmedia.auth.domain.usecase;

import com.roguesoft.socialmedia.auth.application.dto.login.LoginDTO;

public interface DoLoginUseCase {

    String execute(LoginDTO request);

}

package com.magdy.challenge.tenpo.core.message.port;

import com.magdy.challenge.tenpo.core.history.model.History;

public interface MessageRepository {

    void createdMessage(History history);
}

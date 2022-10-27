package com.magdy.challenge.tenpo.core.percentage.port;

import java.io.IOException;
import java.util.Optional;

public interface PercentageClient {

    Optional<Integer> getPercentage() throws IOException;

}

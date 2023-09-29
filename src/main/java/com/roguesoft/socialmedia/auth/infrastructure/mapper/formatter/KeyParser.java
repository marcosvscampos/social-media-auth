package com.roguesoft.socialmedia.auth.infrastructure.mapper.formatter;

import java.security.Key;

public interface KeyParser {

    Key parse(final String keyStr);

}

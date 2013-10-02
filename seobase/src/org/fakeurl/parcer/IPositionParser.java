package org.fakeurl.parcer;

import org.fakeurl.exeptions.CaptchaException;

import java.io.IOException;

/**
 */
public interface IPositionParser {

    public Integer currentPosition(String domain, String word, String query) throws CaptchaException, IOException;

}

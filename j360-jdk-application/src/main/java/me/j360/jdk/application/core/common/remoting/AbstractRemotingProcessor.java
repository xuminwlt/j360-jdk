package me.j360.jdk.application.core.common.remoting;

import me.j360.jdk.application.core.common.support.Application;
import me.j360.jdk.application.remote.RemotingProcessor;

/**
 * @author Robert HG (254963746@qq.com) on 8/16/14.
 */
public abstract class AbstractRemotingProcessor implements RemotingProcessor {

    protected Application application;

    public AbstractRemotingProcessor(Application application) {
        this.application = application;
    }

}

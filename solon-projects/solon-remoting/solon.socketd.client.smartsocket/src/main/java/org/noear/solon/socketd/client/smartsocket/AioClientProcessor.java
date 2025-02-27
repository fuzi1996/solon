package org.noear.solon.socketd.client.smartsocket;

import org.noear.solon.Solon;
import org.noear.solon.core.message.Message;
import org.noear.solon.core.message.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

class AioClientProcessor implements MessageProcessor<Message> {
    static final Logger log = LoggerFactory.getLogger(AioClientProcessor.class);

    private Session session;
    AioClientProcessor(Session session){
        this.session = session;
    }

    @Override
    public void process(AioSession s, Message message) {
        try {
            Solon.app().listener().onMessage(session, message);
        } catch (Throwable e) {
            log.warn(e.getMessage(), e);
        }
    }

    @Override
    public void stateEvent(AioSession s, StateMachineEnum state, Throwable throwable) {

        switch (state) {
            case NEW_SESSION:
                Solon.app().listener().onOpen(session);
                break;

            case SESSION_CLOSED:
                Solon.app().listener().onClose(session);
                AioSocketSession.remove(s);
                break;

            case PROCESS_EXCEPTION:
            case DECODE_EXCEPTION:
            case INPUT_EXCEPTION:
            case ACCEPT_EXCEPTION:
            case OUTPUT_EXCEPTION:
                Solon.app().listener().onError(session, throwable);
                break;
        }

    }
}